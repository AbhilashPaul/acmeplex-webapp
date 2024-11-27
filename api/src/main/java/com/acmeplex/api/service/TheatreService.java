package com.acmeplex.api.service;

import com.acmeplex.api.model.Theatre;
import com.acmeplex.api.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreService {
    @Autowired
    private TheatreRepository theatreRepository;

    public List<Theatre> getAllTheatres() { return theatreRepository.findAll(); }

    public Theatre createTheatre(Theatre theatre) {
        return theatreRepository.save(theatre);
    }

    public void deleteTheatre(Long id) {
        theatreRepository.deleteById(id);
    }

    public List<Theatre> getTheatresByMovieId(Long movieId) {
        return theatreRepository.findTheatresByMovieId(movieId);
    }
}
