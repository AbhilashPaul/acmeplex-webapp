package com.acmeplex.api.repository;

import com.acmeplex.api.model.CreditVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditVoucherRepository extends JpaRepository<CreditVoucher, Long> {}