package com.acmeplex.api.dto;

import com.acmeplex.api.model.MovieRating;

public class MovieDto {
    private Long id;

    private String title;

    private String description;

    private Integer durationInMinute;

    private String genre;

    private MovieRating movieRating;

    private String imageUrl;

    public MovieDto(Long id, String title, String description, Integer durationInMinute, String genre, MovieRating movieRating, String imageUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.durationInMinute = durationInMinute;
        this.genre = genre;
        this.movieRating = movieRating;
        this.imageUrl = imageUrl;
    }

    public MovieDto() {
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

    public Integer getDurationInMinute() {
        return durationInMinute;
    }

    public void setDurationInMinute(Integer durationInMinute) {
        this.durationInMinute = durationInMinute;
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
}
