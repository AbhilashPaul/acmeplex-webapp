package com.acmeplex.api.repository;

import com.acmeplex.api.model.PaymentReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentReceiptRepository extends JpaRepository<PaymentReceipt, Long> {
}