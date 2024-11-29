package com.acmeplex.api.dto;

public class TicketPaymentResponseDto {
    private TicketDto ticketDto;
    private PaymentReceiptDto paymentReceiptDto;

    public TicketPaymentResponseDto() {
    }

    public TicketPaymentResponseDto(TicketDto ticketDto, PaymentReceiptDto paymentReceiptDto) {
        this.ticketDto = ticketDto;
        this.paymentReceiptDto = paymentReceiptDto;
    }

    public TicketDto getTicketDto() {
        return ticketDto;
    }

    public void setTicketDto(TicketDto ticketDto) {
        this.ticketDto = ticketDto;
    }

    public PaymentReceiptDto getPaymentReceiptDto() {
        return paymentReceiptDto;
    }

    public void setPaymentReceiptDto(PaymentReceiptDto paymentReceiptDto) {
        this.paymentReceiptDto = paymentReceiptDto;
    }
}
