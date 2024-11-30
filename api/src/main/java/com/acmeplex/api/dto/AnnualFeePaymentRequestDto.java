package com.acmeplex.api.dto;

public class AnnualFeePaymentRequestDto {
    private Long userId;

    private PaymentCardDto paymentCard;

    public AnnualFeePaymentRequestDto(Long userId, PaymentCardDto paymentCard) {
        this.userId = userId;
        this.paymentCard = paymentCard;
    }

    public AnnualFeePaymentRequestDto() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public PaymentCardDto getPaymentCard() {
        return paymentCard;
    }

    public void setPaymentCard(PaymentCardDto paymentCard) {
        this.paymentCard = paymentCard;
    }
}
