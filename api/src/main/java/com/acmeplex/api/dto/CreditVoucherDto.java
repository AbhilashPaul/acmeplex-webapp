package com.acmeplex.api.dto;


import java.time.LocalDateTime;

public class CreditVoucherDto {
    private Long id;

    private Double amount;

    private LocalDateTime issuedAt;

    private LocalDateTime expiryDate;

    private String customerEmail;

    private String code;

    private Boolean isUsed;

    private Long ticketId;

    public CreditVoucherDto() {
    }

    public CreditVoucherDto(Long id, Double amount, LocalDateTime issuedAt, LocalDateTime expiryDate, String customerEmail, String code, Boolean isUsed, Long ticketId) {
        this.id = id;
        this.amount = amount;
        this.issuedAt = issuedAt;
        this.expiryDate = expiryDate;
        this.customerEmail = customerEmail;
        this.code = code;
        this.isUsed = isUsed;
        this.ticketId = ticketId;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }
}
