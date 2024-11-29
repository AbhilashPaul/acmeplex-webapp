package com.acmeplex.api.mappers;

import com.acmeplex.api.dto.MovieDto;
import com.acmeplex.api.dto.PaymentReceiptDto;
import com.acmeplex.api.model.Movie;
import com.acmeplex.api.model.PaymentReceipt;

public class PaymentReceiptMapper {
    public static PaymentReceiptDto toPaymentReceiptDto(PaymentReceipt receipt) {
        PaymentReceiptDto dto = new PaymentReceiptDto();
        dto.setId(receipt.getId());
        dto.setAmount(receipt.getAmount());
        dto.setPaymentDate(receipt.getPaymentDate());
        dto.setStatus(receipt.getStatus());
        dto.setTransactionId(receipt.getTransactionId());
        return dto;
    }
}
