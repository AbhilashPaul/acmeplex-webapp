package com.acmeplex.api.mappers;

import com.acmeplex.api.dto.MovieDto;
import com.acmeplex.api.model.Movie;

public class MovieMapper {
    public static MovieDto toMovieDto(Movie movie) {
        MovieDto dto = new MovieDto();
        dto.setId(movie.getId());
        dto.setDescription(movie.getDescription());
        dto.setMovieRating(movie.getMovieRating());
        dto.setDuration(movie.getDuration());
        dto.setGenre(movie.getGenre());
        dto.setTitle(movie.getTitle());
        dto.setImageUrl(movie.getImageUrl());
        return dto;
    }
}
