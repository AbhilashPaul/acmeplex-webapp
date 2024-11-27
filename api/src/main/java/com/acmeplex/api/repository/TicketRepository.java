package com.acmeplex.api.repository;

import com.acmeplex.api.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
