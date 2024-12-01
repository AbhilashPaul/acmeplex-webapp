package com.acmeplex.api.notifier;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailNotifier implements NotificationObserver {
    Logger logger = LoggerFactory.getLogger(EmailNotifier.class);
    @Override
    public void update(String message) {
        logger.info("Sending email with message: " + message);
        // Logic to send actual email
    }
}
