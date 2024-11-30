package com.acmeplex.api.dto;


public class TicketPaymentRequestDto {

    private Long ticketId;

    private Double amount;

    private PaymentCardDto paymentCardDto;

    public TicketPaymentRequestDto(Long ticketId, Double amount, PaymentCardDto paymentCardDto) {
        this.ticketId = ticketId;
        this.amount = amount;
        this.paymentCardDto = paymentCardDto;
    }

    public TicketPaymentRequestDto() {
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentCardDto getPaymentCardDto() {
        return paymentCardDto;
    }

    public void setPaymentCardDto(PaymentCardDto paymentCardDto) {
        this.paymentCardDto = paymentCardDto;
    }
}
