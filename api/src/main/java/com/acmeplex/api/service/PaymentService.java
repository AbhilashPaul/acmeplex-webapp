package com.acmeplex.api.service;

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

    @Autowired
    public PaymentService(TicketRepository ticketRepository, PaymentReceiptRepository paymentReceiptRepository, RegisteredUserRepository registeredUserRepository) {
        this.ticketRepository = ticketRepository;
        this.paymentReceiptRepository = paymentReceiptRepository;
        this.registeredUserRepository = registeredUserRepository;
    }

    public PaymentReceipt processTicketPayment(Long ticketId, Double amount) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Simulate transaction processing (e.g., via a third-party gateway)
        String transactionId = UUID.randomUUID().toString(); // Simulated transaction ID

        PaymentReceipt receipt = new PaymentReceipt(
                amount,
                LocalDateTime.now(),
                transactionId,
                PaymentStatus.SUCCESS,
                ticket
        );

        return paymentReceiptRepository.save(receipt);
    }

    public PaymentReceipt processAnnualFeePayment(Long userId, Double amount) {
        // Fetch the user by ID
        RegisteredUser user = registeredUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Registered user not found"));

        // Simulate transaction processing (e.g., via a third-party gateway)
        String transactionId = UUID.randomUUID().toString();

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
