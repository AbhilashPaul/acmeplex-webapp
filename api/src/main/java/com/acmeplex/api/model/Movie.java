package com.acmeplex.api.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String duration;

    private String genre;

    @Enumerated(EnumType.STRING)
    private MovieRating movieRating;

    private String imageUrl;
    @OneToMany(mappedBy = "movie")
    private Set<Showtime> showtimes;

    public Movie() {
    }

    public Movie(Long id, String title, String description, String duration, String genre, MovieRating movieRating, String imageUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.genre = genre;
        this.movieRating = movieRating;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public MovieRating getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(MovieRating movieRating) {
        this.movieRating = movieRating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Showtime> getShowtimes() {
        return showtimes;
    }

    public void setShowtimes(Set<Showtime> showtimes) {
        this.showtimes = showtimes;
    }

    /**
     * Method to add a Showtime to the Movie.
     * Ensures bidirectional relationship is maintained.
     *
     * @param showtime The Showtime object to be added.
     */
    public void addShowtime(Showtime showtime) {
        if (showtime != null) {
            showtime.setMovie(this); // Maintain bidirectional relationship
            this.showtimes.add(showtime);
        }
    }
}