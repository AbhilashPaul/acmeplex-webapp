package com.acmeplex.api.controller;

import com.acmeplex.api.dto.MovieDto;
import com.acmeplex.api.model.Movie;
import com.acmeplex.api.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "http://localhost:4200") // Allow Angular access
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<MovieDto> getAllMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }

    @GetMapping("/search")
    public List<MovieDto> searchMovies(@RequestParam String title) {
        return movieService.searchMovies(title);
    }
}