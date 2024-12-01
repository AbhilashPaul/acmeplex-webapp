package com.acmeplex.api.controller;

import com.acmeplex.api.dto.AnnualFeePaymentRequestDto;
import com.acmeplex.api.dto.PaymentReceiptDto;
import com.acmeplex.api.dto.TicketDto;
import com.acmeplex.api.dto.TicketPaymentRequestDto;
import com.acmeplex.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    public static final double MEMBERSHIP_ANNUAL_FEE = 20.0;
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    /**
     * Processes payment for a ticket.
     */
    @PostMapping("/ticket")
    public TicketDto processTicketPayment(@RequestBody TicketPaymentRequestDto ticketPaymentRequestDto) {
        return paymentService.processTicketPayment(
                ticketPaymentRequestDto.getTicketId(),
                ticketPaymentRequestDto.getAmount(),
                ticketPaymentRequestDto.getPaymentCard()
        );
    }

    /**
     * Processes annual fee payment for a registered user.
     */
    @PostMapping("/annualFee")
    public PaymentReceiptDto processAnnualFeePayment(@RequestBody AnnualFeePaymentRequestDto annualFeePaymentRequestDto) {
        return paymentService.processAnnualFeePayment(
                annualFeePaymentRequestDto.getUserId(), MEMBERSHIP_ANNUAL_FEE,
                annualFeePaymentRequestDto.getPaymentCard());
    }
}
