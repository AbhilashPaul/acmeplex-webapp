package com.acmeplex.api.controller;

import com.acmeplex.api.dto.AnnualFeePaymentRequestDto;
import com.acmeplex.api.dto.TicketPaymentRequestDto;
import com.acmeplex.api.dto.TicketPaymentResponseDto;
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
     */
    @PostMapping("/ticket")
    public TicketPaymentResponseDto processTicketPayment(
            @RequestBody TicketPaymentRequestDto ticketPaymentRequestDto) {
        return paymentService.processTicketPayment(
                ticketPaymentRequestDto.getTicketId(),
                ticketPaymentRequestDto.getAmount(),
                ticketPaymentRequestDto.getPaymentCardDto()
        );
    }

    /**
     * Process annual fee payment for a registered user.
     */
    @PostMapping("/annualFee")
    public PaymentReceipt processAnnualFeePayment(@RequestBody AnnualFeePaymentRequestDto annualFeePaymentRequestDto) {
        return paymentService.processAnnualFeePayment(
                annualFeePaymentRequestDto.getUserId(),
                annualFeePaymentRequestDto.getAmount(),
                annualFeePaymentRequestDto.getPaymentCardDto());
    }
}
