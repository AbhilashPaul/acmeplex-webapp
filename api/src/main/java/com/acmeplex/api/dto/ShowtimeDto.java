package com.acmeplex.api.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import  com.acmeplex.api.dto.SeatDto;

public class ShowtimeDto {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private List<SeatDto> seats;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public List<SeatDto> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDto> seats) {
        this.seats = seats;
    }
}