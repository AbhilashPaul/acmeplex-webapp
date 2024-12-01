package com.acmeplex.api.service;

import com.acmeplex.api.dto.PaymentCardDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentGateway {
    Logger logger = LoggerFactory.getLogger(PaymentGateway.class);

    public String processPayment(PaymentCardDto paymentCardDto, Double amount){
        // Simulate transaction processing (e.g., via a third-party gateway)
        logger.info("Simulated payment processing for $" + amount + " using card "
                + paymentCardDto.getCardNumber() + "from card holder "+ paymentCardDto.getCardHolderName());
        return UUID.randomUUID().toString(); // Simulated transaction ID
    }
}
