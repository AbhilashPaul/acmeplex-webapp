package com.acmeplex.api.controller;

import com.acmeplex.api.model.PaymentReceipt;
import com.acmeplex.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    /**
     * Process payment for a ticket.
     *
     * @param ticketId      ID of the ticket for which payment is being made
     * @param amount        Payment amount
     * @return The PaymentReceipt object for the processed payment
     */
    @PostMapping("/ticket/{ticketId}")
    public PaymentReceipt processTicketPayment(
            @PathVariable Long ticketId,
            @RequestParam Double amount) {
        return paymentService.processTicketPayment(ticketId, amount);
    }

    /**
     * Process annual fee payment for a registered user.
     *
     * @param userId        ID of the user paying the annual fee
     * @param amount        Payment amount (e.g., $20.00)
     * @return The PaymentReceipt object for the processed annual fee payment
     */
    @PostMapping("/annualFee/{userId}")
    public PaymentReceipt processAnnualFeePayment(
            @PathVariable Long userId,
            @RequestParam Double amount) {
        return paymentService.processAnnualFeePayment(userId, amount);
    }
}
