package com.acmeplex.api.mappers;

import com.acmeplex.api.dto.CreditVoucherDto;
import com.acmeplex.api.model.CreditVoucher;

public class CreditVoucherMapper {
    public static CreditVoucherDto toCreditVoucherDto(CreditVoucher voucher) {
        CreditVoucherDto dto = new CreditVoucherDto();
        dto.setId(voucher.getId());
        dto.setAmount(voucher.getAmount());
        dto.setCode(voucher.getCode());
        dto.setCustomerEmail(voucher.getCustomerEmail());
        dto.setExpiryDate(voucher.getExpiryDate());
        dto.setIssuedAt(voucher.getIssuedAt());
        dto.setTicketId(voucher.getTicket().getId());
        dto.setUsed(voucher.getIsUsed());
        return dto;
    }
}
