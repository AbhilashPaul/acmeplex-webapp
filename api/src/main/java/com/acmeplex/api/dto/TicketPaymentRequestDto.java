package com.acmeplex.api.dto;


public class TicketPaymentRequestDto {

    private Long ticketId;

    private Double amount;

    private PaymentCardDto paymentCard;

    public TicketPaymentRequestDto(Long ticketId, Double amount, PaymentCardDto paymentCard) {
        this.ticketId = ticketId;
        this.amount = amount;
        this.paymentCard = paymentCard;
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

    public PaymentCardDto getPaymentCard() {
        return paymentCard;
    }

    public void setPaymentCard(PaymentCardDto paymentCard) {
        this.paymentCard = paymentCard;
    }
}
