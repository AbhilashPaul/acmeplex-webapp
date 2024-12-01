package com.acmeplex.api.service;

import com.acmeplex.api.dto.PaymentCardDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentGateway {

    public String processPayment(PaymentCardDto paymentCardDto, Double amount){
        // Simulate transaction processing (e.g., via a third-party gateway)
        return UUID.randomUUID().toString(); // Simulated transaction ID
    }
}
