package com.acmeplex.api.controller;

import com.acmeplex.api.model.CreditVoucher;
import com.acmeplex.api.model.Ticket;
import com.acmeplex.api.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "http://localhost:4200") // Allow Angular access
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * Create a new ticket.
     *
     * @param customerName  Name of the customer
     * @param customerEmail Email of the customer
     * @param seatId        ID of the seat being booked
     * @param showtimeId    ID of the showtime for the ticket
     * @return The created Ticket object
     */
    @PostMapping
    public Ticket createTicket(
            @RequestParam String customerName,
            @RequestParam String customerEmail,
            @RequestParam Long seatId,
            @RequestParam Long showtimeId) {
        return ticketService.createTicket(customerName, customerEmail, seatId, showtimeId);
    }

    /**
     * Retrieve details of a specific ticket by ID.
     *
     * @param ticketId ID of the ticket to retrieve
     * @return The Ticket object with associated details
     */
    @GetMapping("/{ticketId}")
    public Ticket getTicketDetails(@PathVariable Long ticketId) {
        return ticketService.getTicketDetails(ticketId);
    }

    /**
     * Cancel a specific ticket and issue a credit voucher.
     *
     * @param ticketId ID of the ticket to cancel
     * @return The created Credit Voucher object
     */
    @PostMapping("/{ticketId}/cancel")
    public CreditVoucher cancelTicket(@PathVariable Long ticketId) {
        return ticketService.cancelTicket(ticketId);
    }
}
