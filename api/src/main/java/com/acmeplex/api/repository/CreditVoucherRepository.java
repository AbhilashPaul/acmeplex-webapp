package com.acmeplex.api.repository;

import com.acmeplex.api.model.CreditVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditVoucherRepository extends JpaRepository<CreditVoucher, Long> {
    List<CreditVoucher> findByCustomerEmailAndIsUsedFalse(String customerEmail);
}