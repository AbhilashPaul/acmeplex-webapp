package com.acmeplex.api.service;

import com.acmeplex.api.model.*;
import com.acmeplex.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TicketService {

    public static final int CANCELLATION_CUTOFF_HOURS = 72;
    public static final double DEDUCTION_PERCENT_GUEST_USERS = 0.15;
    public static final int CREDIT_VOUCHER_VALIDITY_PERIOD_IN_YEARS = 1;
    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;
    private final ShowtimeRepository showtimeRepository;
    private final CreditVoucherRepository creditVoucherRepository;
    private final RegisteredUserRepository registeredUserRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository,
                         SeatRepository seatRepository,
                         ShowtimeRepository showtimeRepository,
                         CreditVoucherRepository creditVoucherRepository,
                         RegisteredUserRepository registeredUserRepository) {
        this.ticketRepository = ticketRepository;
        this.seatRepository = seatRepository;
        this.showtimeRepository = showtimeRepository;
        this.creditVoucherRepository = creditVoucherRepository;
        this.registeredUserRepository = registeredUserRepository;
    }

    // Create a new ticket
    public Ticket createTicket(String customerName, String customerEmail, Long seatId, Long showtimeId) {
        // Fetch Seat and Showtime from DB
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));
        if (seat.isReserved()) {
            throw new RuntimeException("Seat is already reserved");
        }

        Showtime showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new RuntimeException("Showtime not found"));

        // Mark seat as reserved
        seat.setReserved(true);
        seatRepository.save(seat);

        // Create and save Ticket
        Ticket ticket = new Ticket(customerName, customerEmail, seat, showtime);
        return ticketRepository.save(ticket);
    }

    // Retrieve ticket details by ID
    public Ticket getTicketDetails(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    public CreditVoucher cancelTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        // Check if cancellation is allowed (72 hours before showtime)
        LocalDateTime now = LocalDateTime.now();
        isCancellationAllowed(ticket, now);


        double refundAmount = calculateRefundAmount(ticket);

        // Create a Credit Voucher
        CreditVoucher creditVoucher = new CreditVoucher(
                refundAmount,
                now,
                now.plusYears(CREDIT_VOUCHER_VALIDITY_PERIOD_IN_YEARS),
                ticket.getCustomerEmail(),
                generateCouponCode(),
                ticket
        );

        creditVoucherRepository.save(creditVoucher);

        // Release the seat back to available status
        Seat seat = ticket.getSeat();
        seat.setReserved(false);
        seatRepository.save(seat);

        return creditVoucher;
    }

    private void isCancellationAllowed(Ticket ticket, LocalDateTime current_time) {
        LocalDateTime showtimeStart = LocalDateTime.of(ticket.getShowtime().getDate(), ticket.getShowtime().getTime());
        long hoursUntilShowtime = Duration.between(current_time, showtimeStart).toHours();

        if (hoursUntilShowtime < CANCELLATION_CUTOFF_HOURS) {
            throw new RuntimeException("Cancellation is not allowed within 72 hours of the showtime.");
        }
    }

    private double calculateRefundAmount(Ticket ticket) {
        PaymentReceipt paymentReceipt = ticket.getPaymentReceipt();
        if (paymentReceipt == null || paymentReceipt.getStatus() != PaymentStatus.SUCCESS) {
            throw new RuntimeException("No successful payment found for this ticket.");
        }
        // Retrieve the user based on customer email
        String customerEmail = ticket.getCustomerEmail();
        RegisteredUser user = registeredUserRepository.findByEmail(customerEmail).orElse(null);
        double originalAmount = paymentReceipt.getAmount();
        if (user != null && !user.isMembershipExpired()) {
            return originalAmount;
        } else {
            double cancellationFee = originalAmount * DEDUCTION_PERCENT_GUEST_USERS;
            return originalAmount - cancellationFee;
        }
    }
    private static String generateCouponCode() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();
    }
}