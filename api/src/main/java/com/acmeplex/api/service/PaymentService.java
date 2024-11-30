package com.acmeplex.api.service;

import com.acmeplex.api.dto.PaymentCardDto;
import com.acmeplex.api.dto.TicketDto;
import com.acmeplex.api.mappers.TicketMapper;
import com.acmeplex.api.model.PaymentReceipt;
import com.acmeplex.api.model.PaymentStatus;
import com.acmeplex.api.model.RegisteredUser;
import com.acmeplex.api.model.Ticket;
import com.acmeplex.api.repository.PaymentReceiptRepository;
import com.acmeplex.api.repository.RegisteredUserRepository;
import com.acmeplex.api.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class PaymentService {

    private final TicketRepository ticketRepository;
    private final RegisteredUserRepository registeredUserRepository;
    private final PaymentReceiptRepository paymentReceiptRepository;
    private final PaymentGateway paymentGateway;

    @Autowired
    public PaymentService(TicketRepository ticketRepository,
                          PaymentReceiptRepository paymentReceiptRepository,
                          RegisteredUserRepository registeredUserRepository,
                          PaymentGateway paymentGateway) {
        this.ticketRepository = ticketRepository;
        this.paymentReceiptRepository = paymentReceiptRepository;
        this.registeredUserRepository = registeredUserRepository;
        this.paymentGateway = paymentGateway;
    }

    @Transactional
    public TicketDto processTicketPayment(Long ticketId, Double amount, PaymentCardDto paymentCardDto) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Ticket %d not found", ticketId)));

        // Simulate transaction processing (e.g., via a third-party gateway)
        String transactionId = paymentGateway.processPayment(paymentCardDto, amount);

        PaymentReceipt receipt = new PaymentReceipt(
                amount,
                LocalDateTime.now(),
                transactionId,
                PaymentStatus.SUCCESS,
                ticket
        );
        PaymentReceipt paymentReceipt = paymentReceiptRepository.save(receipt);

        ticket.setPaymentStatus(PaymentStatus.SUCCESS);
        ticket.setPaymentReceipt(paymentReceipt);
        TicketDto updatedTicket = TicketMapper.toTicketDto(ticketRepository.save(ticket));
        updatedTicket.getShowtime().setSeats(Collections.emptyList());
        return updatedTicket;
    }

    public PaymentReceipt processAnnualFeePayment(Long userId, Double amount, PaymentCardDto paymentCardDto) {
        // Fetch the user by ID
        RegisteredUser user = registeredUserRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Registered User %d not found", userId)));

        String transactionId = paymentGateway.processPayment(paymentCardDto, amount);

        user.setAnnualFeePaid(true);
        user.setAnnualFeePaidDate(LocalDateTime.now());
        registeredUserRepository.save(user);

        // Create and save the receipt (no associated Booking for annual fees)
        PaymentReceipt receipt = new PaymentReceipt(
                amount,
                LocalDateTime.now(),
                transactionId,
                PaymentStatus.SUCCESS,
                null
        );

        return paymentReceiptRepository.save(receipt);
    }
}
