package com.acmeplex.api.mappers;

import com.acmeplex.api.dto.SeatDto;
import com.acmeplex.api.dto.ShowtimeDto;
import com.acmeplex.api.model.Seat;
import com.acmeplex.api.model.Showtime;
import com.acmeplex.api.model.ShowtimeSeat;

import java.util.stream.Collectors;

public class ShowtimeMapper {
    public static ShowtimeDto toShowtimeDto(Showtime showtime) {
        ShowtimeDto dto = new ShowtimeDto();
        dto.setId(showtime.getId());
        dto.setDate(showtime.getDate());
        dto.setTime(showtime.getTime());
        dto.setSeats(showtime.getShowtimeSeats().stream()
                .map(ShowtimeMapper::toSeatDto)
                .collect(Collectors.toList()));

        return dto;
    }

    private static SeatDto toSeatDto(ShowtimeSeat showtimeSeat) {
        Seat seat = showtimeSeat.getSeat();
        SeatDto dto = new SeatDto();
        dto.setId(seat.getId());
        dto.setRowLabel(seat.getRowLabel());
        dto.setSeatNumber(seat.getSeatNumber());
        dto.setPrice(seat.getPrice());
        dto.setReserved(showtimeSeat.getIsReserved());
        return dto;
    }
}
