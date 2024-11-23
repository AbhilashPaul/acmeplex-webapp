package com.acmeplex.api.service;

import com.acmeplex.api.model.Movie;
import com.acmeplex.api.model.Theatre;
import com.acmeplex.api.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TheatreService {
    @Autowired
    private TheatreRepository theatreRepository;

    public List<Theatre> getAllTheatres() { return theatreRepository.findAll(); }

    public Theatre createTheatre(@RequestBody Theatre theatre) {
        return theatreRepository.save(theatre);
    }

    public void deleteTheatre(@PathVariable Long id) {
        theatreRepository.deleteById(id);
    }
}
