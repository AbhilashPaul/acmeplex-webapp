package com.acmeplex.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PaymentReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private LocalDateTime paymentDate;
    private String transactionId;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @OneToOne(mappedBy = "paymentReceipt", cascade = CascadeType.ALL)
    private Ticket ticket;

    public PaymentReceipt() {
    }

    public PaymentReceipt(Double amount, LocalDateTime paymentDate, String transactionId, PaymentStatus status, Ticket ticket) {
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.transactionId = transactionId;
        this.status = status;
        this.ticket = ticket;
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

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
