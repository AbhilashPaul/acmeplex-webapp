package com.acmeplex.api.service;

import com.acmeplex.api.dto.MovieDto;
import com.acmeplex.api.model.Movie;
import com.acmeplex.api.repository.MovieRepository;
import com.acmeplex.api.mappers.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;


    public List<MovieDto> getAllMovies() {
        return getMovieDtos(movieRepository.findAll());
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public List<MovieDto> searchMovies(String title) {
        return getMovieDtos(movieRepository.findByTitleContaining(title));
    }

    private List<MovieDto> getMovieDtos(List<Movie> movieList) {
        List<MovieDto> movies = new ArrayList<>();
        for (Movie item : movieList) {
            movies.add(MovieMapper.toMovieDto(item));
        }
        return movies;
    }
}
