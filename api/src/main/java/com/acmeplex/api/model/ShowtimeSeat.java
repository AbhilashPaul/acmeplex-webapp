package com.acmeplex.api.model;

import jakarta.persistence.*;

@Entity
public class ShowtimeSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "seatId", nullable = false)
    private Seat seat;

    @ManyToOne()
    @JoinColumn(name = "showtimeId", nullable = false)
    private Showtime showtime;

    private Boolean isReserved;

    // Constructors
    public ShowtimeSeat() {
    }

    public ShowtimeSeat(Seat seat, Showtime showtime, Boolean isReserved) {
        this.seat = seat;
        this.showtime = showtime;
        this.isReserved = isReserved;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

    public Boolean getIsReserved() {
        return isReserved;
    }

    public void setIsReserved(Boolean isReserved) {
        this.isReserved = isReserved;
    }
}
