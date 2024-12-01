package com.acmeplex.api.service;

import com.acmeplex.api.model.CreditVoucher;
import com.acmeplex.api.model.PaymentReceipt;
import com.acmeplex.api.model.Ticket;
import com.acmeplex.api.notifier.NotificationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserNotificationService {
    private final NotificationManager notificationManager;

    @Autowired
    public UserNotificationService(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }

    public void sendTicketAndReceiptDetails(Ticket ticket) {
        notificationManager.sendNotification(ticket.toString());
    }

    public void sendReceiptDetails(PaymentReceipt receipt) {
        notificationManager.sendNotification(receipt.toString());
    }

    public void sendCreditVoucherDetails(CreditVoucher voucher) {
        notificationManager.sendNotification(voucher.toString());
    }
}
