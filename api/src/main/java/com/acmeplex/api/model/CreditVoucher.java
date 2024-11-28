package com.acmeplex.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CreditVoucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private LocalDateTime issuedAt;

    private LocalDateTime expiryDate;

    private String customerEmail;

    private String code;

    private Boolean isUsed;

    @OneToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket; // The ticket associated with this credit voucher

    public CreditVoucher() {
    }

    public CreditVoucher(Double amount, LocalDateTime issuedAt, LocalDateTime expiryDate,
                         String customerEmail, String code, Boolean used, Ticket ticket) {
        this.amount = amount;
        this.issuedAt = issuedAt;
        this.expiryDate = expiryDate;
        this.customerEmail = customerEmail;
        this.code =code;
        this.isUsed = used;
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

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean used) {
        isUsed = used;
    }
}
