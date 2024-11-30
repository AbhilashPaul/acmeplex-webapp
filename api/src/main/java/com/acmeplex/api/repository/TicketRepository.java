package com.acmeplex.api.repository;

import com.acmeplex.api.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByCustomerEmail(String customerEmail);
}
