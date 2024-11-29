package com.acmeplex.api.dto;

public class AnnualFeePaymentRequestDto {
    private Long userId;

    private Double amount;

    private PaymentCardDto paymentCardDto;

    public AnnualFeePaymentRequestDto(Long userId, Double amount, PaymentCardDto paymentCardDto) {
        this.userId = userId;
        this.amount = amount;
        this.paymentCardDto = paymentCardDto;
    }

    public AnnualFeePaymentRequestDto() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
