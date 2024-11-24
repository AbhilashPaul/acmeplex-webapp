package com.acmeplex.api.service;

import com.acmeplex.api.model.Seat;
import com.acmeplex.api.model.Showtime;
import com.acmeplex.api.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class SeatService {
    @Autowired
    private SeatRepository seatRepository;

    public List<Seat> getAllSeats() { return seatRepository.findAll(); }

    public Seat createSeat(@RequestBody Seat seat) { return seatRepository.save(seat); }

    public void deleteSeat(@PathVariable Long id) { seatRepository.deleteById(id); }

}
