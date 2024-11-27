package com.acmeplex.api.controller;

import com.acmeplex.api.model.PaymentReceipt;
import com.acmeplex.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public PaymentReceipt processPayment(
            @RequestParam String paymentType, // "BOOKING" or "ANNUAL_FEE"
            @RequestParam(required = false) Long ticketId,
            @RequestParam(required = false) Long userId,
            @RequestParam Double amount) {

        if ("BOOKING".equalsIgnoreCase(paymentType)) {
            if (ticketId == null) {
                throw new IllegalArgumentException("Booking ID is required for ticket booking payments.");
            }
            return paymentService.processTicketPayment(ticketId, amount);
        } else if ("ANNUAL_FEE".equalsIgnoreCase(paymentType)) {
            if (userId == null) {
                throw new IllegalArgumentException("User ID is required for annual fee payments.");
            }
            return paymentService.processAnnualFeePayment(userId, amount);
        } else {
            throw new IllegalArgumentException("Invalid payment type. Use 'BOOKING' or 'ANNUAL_FEE'.");
        }
    }
}
