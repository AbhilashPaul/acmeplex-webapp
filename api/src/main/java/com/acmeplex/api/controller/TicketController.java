package com.acmeplex.api.controller;

import com.acmeplex.api.dto.CreateTicketRequestDto;
import com.acmeplex.api.dto.TicketDto;
import com.acmeplex.api.model.CreditVoucher;
import com.acmeplex.api.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "http://localhost:4200") // Allow Angular access
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @PostMapping
    public TicketDto createTicket(@RequestBody CreateTicketRequestDto createTicketRequestDto) {
        return ticketService.createTicket(createTicketRequestDto);
    }

    @GetMapping
    public List<TicketDto> getAllTickets() {
        return ticketService.getAllTickets();
    }

    /**
     * Retrieve details of a specific ticket by ID.
     *
     * @param ticketId ID of the ticket to retrieve
     * @return The Ticket object with associated details
     */
    @GetMapping("/{ticketId}")
    public TicketDto getTicketDetails(@PathVariable Long ticketId) {
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
