package com.acmeplex.api.model;

import jakarta.persistence.*;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String row;
    private Integer seatNumber;
    @ManyToOne
    @JoinColumn(name="showtimeId", nullable=false)
    private Showtime showtime;
}
