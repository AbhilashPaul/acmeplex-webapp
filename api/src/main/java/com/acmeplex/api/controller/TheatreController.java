package com.acmeplex.api.controller;

import com.acmeplex.api.dto.TheatreDto;
import com.acmeplex.api.model.Theatre;
import com.acmeplex.api.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theatres")
@CrossOrigin(origins = "http://localhost:4200") // Allow Angular access
public class TheatreController {
    @Autowired
    private TheatreService theatreService;

    @GetMapping("/all")
    public List<TheatreDto> getAllTheatres() {
        return theatreService.getAllTheatres();
    }

    @GetMapping("/by-movie")
    public List<TheatreDto> getAllTheatresByMovieId(@RequestParam Long movieId) {
        return theatreService.getAllTheatresByMovieId(movieId);
    }

    @PostMapping
    public Theatre createTheatre(@RequestBody Theatre theatre) {
        return theatreService.createTheatre(theatre);
    }

    @DeleteMapping("/{id}")
    public void deleteTheatre(@PathVariable Long id) {
        theatreService.deleteTheatre(id);
    }
}
