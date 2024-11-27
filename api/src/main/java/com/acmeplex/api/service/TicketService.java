package com.acmeplex.api.service;

import com.acmeplex.api.model.Seat;
import com.acmeplex.api.model.Showtime;
import com.acmeplex.api.model.Ticket;
import com.acmeplex.api.repository.SeatRepository;
import com.acmeplex.api.repository.ShowtimeRepository;
import com.acmeplex.api.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;
    private final ShowtimeRepository showtimeRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository,
                         SeatRepository seatRepository,
                         ShowtimeRepository showtimeRepository) {
        this.ticketRepository = ticketRepository;
        this.seatRepository = seatRepository;
        this.showtimeRepository = showtimeRepository;
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
}