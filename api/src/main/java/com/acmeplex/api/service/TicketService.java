package com.acmeplex.api.service;

import com.acmeplex.api.dto.CreateTicketRequestDto;
import com.acmeplex.api.model.*;
import com.acmeplex.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    public static final int CANCELLATION_CUTOFF_HOURS = 72;
    public static final double DEDUCTION_PERCENT_GUEST_USERS = 0.15;
    public static final int CREDIT_VOUCHER_VALIDITY_PERIOD_IN_YEARS = 1;
    private final TicketRepository ticketRepository;
    private final ShowtimeRepository showtimeRepository;
    private final CreditVoucherRepository creditVoucherRepository;
    private final RegisteredUserRepository registeredUserRepository;
    private final ShowtimeSeatRepository showtimeSeatRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository,
                         ShowtimeRepository showtimeRepository,
                         CreditVoucherRepository creditVoucherRepository,
                         RegisteredUserRepository registeredUserRepository,
                         ShowtimeSeatRepository showtimeSeatRepository) {
        this.ticketRepository = ticketRepository;
        this.showtimeRepository = showtimeRepository;
        this.creditVoucherRepository = creditVoucherRepository;
        this.registeredUserRepository = registeredUserRepository;
        this.showtimeSeatRepository = showtimeSeatRepository;
    }

    // Create a new ticket
    public Ticket createTicket(CreateTicketRequestDto createTicketRequestDto) {
        Long showtimeId = createTicketRequestDto.getShowtimeId();
        Long seatId = createTicketRequestDto.getSeatId();
        String customerName = createTicketRequestDto.getCustomerName();
        String customerEmail = createTicketRequestDto.getCustomerEmail();

        ShowtimeSeat showtimeSeat = showtimeSeatRepository.findByShowtimeIdAndSeatId(showtimeId, seatId)
                .orElseThrow(() -> new RuntimeException("ShowtimeSeat not found for showtimeId: " + showtimeId + " and seatId: " + seatId));
        if (showtimeSeat.getIsReserved()) {
            throw new RuntimeException("Seat is already reserved");
        }

        Showtime showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new RuntimeException("Showtime not found"));

        Double ticketPrice = calculateTicketPrice(customerEmail, showtimeSeat.getSeat());

        // Mark seat as reserved
        showtimeSeat.setIsReserved(true);
        showtimeSeatRepository.save(showtimeSeat);

        // Create and save Ticket
        Ticket ticket = new Ticket(customerName, customerEmail, ticketPrice,showtimeSeat.getSeat(), showtime, PaymentStatus.PENDING);
        return ticketRepository.save(ticket);
    }

    private Double calculateTicketPrice(String customerEmail, Seat seat) {
        // Calculate initial ticket price based on seat price
        Double ticketPrice = seat.getPrice();
        // Apply unused credit vouchers
        List<CreditVoucher> vouchers = creditVoucherRepository.findByCustomerEmailAndIsUsedFalse(customerEmail);
        for (CreditVoucher voucher : vouchers) {
            if (ticketPrice <= 0) break; // No need to apply more vouchers if price is zero

            double voucherAmount = voucher.getAmount();
            if (voucherAmount >= ticketPrice) {
                // Fully cover the ticket price with this voucher
                voucher.setAmount(voucherAmount - ticketPrice);
                ticketPrice = 0.0;
            } else {
                // Partially cover the ticket price with this voucher
                ticketPrice -= voucherAmount;
                voucher.setAmount(0.0);
            }

            // Mark voucher as used if fully utilized
            if (voucher.getAmount() == 0) {
                voucher.setIsUsed(true);
            }
            creditVoucherRepository.save(voucher);
        }
        return ticketPrice;
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
                Boolean.FALSE,
                ticket
        );

        creditVoucherRepository.save(creditVoucher);

        // Release the seat back to available status
        Long showtimeId = ticket.getShowtime().getId();
        Long seatId = ticket.getSeat().getId();
        ShowtimeSeat showtimeSeat = showtimeSeatRepository.findByShowtimeIdAndSeatId(showtimeId, seatId)
                .orElseThrow(() -> new RuntimeException("ShowtimeSeat not found for showtimeId: " + showtimeId + " and seatId: " + seatId));

        showtimeSeat.setIsReserved(false);
        showtimeSeatRepository.save(showtimeSeat);

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