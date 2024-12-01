package com.acmeplex.api.service;

import com.acmeplex.api.dto.CreditVoucherDto;
import com.acmeplex.api.mappers.CreditVoucherMapper;
import com.acmeplex.api.model.CreditVoucher;
import com.acmeplex.api.repository.CreditVoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreditVoucherService {

    @Autowired
    private final CreditVoucherRepository creditVoucherRepository;

    public CreditVoucherService(CreditVoucherRepository creditVoucherRepository) {
        this.creditVoucherRepository = creditVoucherRepository;
    }

    public List<CreditVoucherDto> getUnusedCreditVouchersByCustomerEmail(String email) {
        List<CreditVoucher> voucherList = creditVoucherRepository.findByCustomerEmailAndIsUsedFalse(email);
        List<CreditVoucherDto> vouchers = new ArrayList<>();
        for (CreditVoucher voucher : voucherList) {
            vouchers.add(CreditVoucherMapper.toCreditVoucherDto(voucher));
        }
        return vouchers;
    }
}
