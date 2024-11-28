package com.acmeplex.api.service;

import com.acmeplex.api.dto.ShowtimeDto;
import com.acmeplex.api.mappers.ShowtimeMapper;
import com.acmeplex.api.model.Showtime;
import com.acmeplex.api.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ShowtimeService {
    @Autowired
    private ShowtimeRepository showtimeRepository;

    public List<ShowtimeDto> getAllShowtimes() {
        List<ShowtimeDto> showtimes = new ArrayList<>();
        for (Showtime item : showtimeRepository.findAll()) {
            showtimes.add(ShowtimeMapper.toShowtimeDto(item));
        }
        return showtimes;
    }

    public Showtime createShowtime(@RequestBody Showtime showtime) {
        return showtimeRepository.save(showtime);
    }

    public void deleteShowtime(@PathVariable Long id) {
        showtimeRepository.deleteById(id);
    }

}
