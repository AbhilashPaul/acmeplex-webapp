package com.acmeplex.api.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private LocalTime time;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movieId", nullable = false)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theatreId", nullable = false)
    private Theatre theatre;

    @OneToMany(mappedBy = "showtime")
    private Set<ShowtimeSeat> showtimeSeats;

    public Showtime() {
    }

    public Showtime(Long id, LocalDate date, LocalTime time, Movie movie, Theatre theatre) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.movie = movie;
        this.theatre = theatre;
    }

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

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public Set<ShowtimeSeat> getShowtimeSeats() {
        return showtimeSeats;
    }

    public void setShowtimeSeats(Set<ShowtimeSeat> showtimeSeats) {
        this.showtimeSeats = showtimeSeats;
    }
}
