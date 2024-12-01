package com.acmeplex.api.dto;

import com.acmeplex.api.model.PaymentStatus;

import java.time.LocalDateTime;

public class PaymentReceiptDto {
    private Long id;
    private Double amount;
    private LocalDateTime paymentDate;
    private String transactionId;
    private PaymentStatus status;

    public PaymentReceiptDto(Long id, Double amount, LocalDateTime paymentDate, String transactionId, PaymentStatus status) {
        this.id = id;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.transactionId = transactionId;
        this.status = status;
    }

    public PaymentReceiptDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
