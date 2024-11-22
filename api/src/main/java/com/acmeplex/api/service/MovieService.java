package com.acmeplex.api.service;

import com.acmeplex.api.model.Movie;
import com.acmeplex.api.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;


    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie createMovie(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteMovie(@PathVariable Long id) {
        movieRepository.deleteById(id);
    }
}
