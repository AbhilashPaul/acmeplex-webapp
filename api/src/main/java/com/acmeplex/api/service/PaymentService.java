package com.acmeplex.api.service;

import com.acmeplex.api.dto.PaymentCardDto;
import com.acmeplex.api.dto.TicketPaymentResponseDto;
import com.acmeplex.api.mappers.PaymentReceiptMapper;
import com.acmeplex.api.mappers.TicketMapper;
import com.acmeplex.api.model.PaymentReceipt;
import com.acmeplex.api.model.PaymentStatus;
import com.acmeplex.api.model.RegisteredUser;
import com.acmeplex.api.model.Ticket;
import com.acmeplex.api.repository.PaymentReceiptRepository;
import com.acmeplex.api.repository.RegisteredUserRepository;
import com.acmeplex.api.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

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

    public TicketPaymentResponseDto processTicketPayment(Long ticketId, Double amount, PaymentCardDto paymentCardDto) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Simulate transaction processing (e.g., via a third-party gateway)
        String transactionId = paymentGateway.processPayment(paymentCardDto);

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
        Ticket updatedTicket = ticketRepository.save(ticket);

        return new TicketPaymentResponseDto(TicketMapper.toTicketDto(ticket), PaymentReceiptMapper.toPaymentReceiptDto(receipt));
    }

    public PaymentReceipt processAnnualFeePayment(Long userId, Double amount, PaymentCardDto paymentCardDto) {
        // Fetch the user by ID
        RegisteredUser user = registeredUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Registered user not found"));

        String transactionId = paymentGateway.processPayment(paymentCardDto);

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
