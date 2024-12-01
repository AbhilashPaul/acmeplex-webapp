package com.acmeplex.api.service;

import com.acmeplex.api.dto.TheatreDto;
import com.acmeplex.api.mappers.TheatreMapper;
import com.acmeplex.api.model.Showtime;
import com.acmeplex.api.model.Theatre;
import com.acmeplex.api.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TheatreService {
    @Autowired
    private TheatreRepository theatreRepository;

    public List<TheatreDto> getAllTheatres() {
        return getTheaterDtos(theatreRepository.findAll());
    }

    public Theatre createTheatre(Theatre theatre) {
        return theatreRepository.save(theatre);
    }

    public void deleteTheatre(Long id) {
        theatreRepository.deleteById(id);
    }

    public List<TheatreDto> getAllTheatresByMovieId(Long movieId) {
        for (Theatre theatre : theatreRepository.findTheatresByMovieId(movieId)) {
            List<Showtime> showtimes = new ArrayList<>();
            for (Showtime showtime : theatre.getShowtimes()) {
                if (Objects.equals(showtime.getMovie().getId(), movieId)) {
                    showtimes.add(showtime);
                }
            }
            theatre.setShowtimes(showtimes);
        }
        return getTheaterDtos(theatreRepository.findTheatresByMovieId(movieId));
    }

    private List<TheatreDto> getTheaterDtos(List<Theatre> theatreList) {
        List<TheatreDto> theatres = new ArrayList<>();
        for (Theatre item : theatreList) {
            theatres.add(TheatreMapper.toTheatreDto(item));
        }
        return theatres;
    }
}
