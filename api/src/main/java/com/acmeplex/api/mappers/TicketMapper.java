package com.acmeplex.api.mappers;

import com.acmeplex.api.dto.TicketDto;
import com.acmeplex.api.model.Seat;
import com.acmeplex.api.model.Ticket;

public class TicketMapper {

    public static TicketDto toTicketDto(Ticket ticket) {
        if (ticket == null) {
            return null;
        }

        TicketDto ticketDto = new TicketDto();
        ticketDto.setId(ticket.getId());
        ticketDto.setCustomerName(ticket.getCustomerName());
        ticketDto.setCustomerEmail(ticket.getCustomerEmail());
        ticketDto.setPrice(ticket.getPrice());

        Seat seat = ticket.getSeat();
        ticketDto.setSeat(seat);

        if (seat != null && ticket.getShowtime() != null) {
            ticketDto.setShowtime(ShowtimeMapper.toShowtimeDto(ticket.getShowtime()));
            ticketDto.setMovie(MovieMapper.toMovieDto(ticket.getShowtime().getMovie()));
        }

        return ticketDto;
    }
}