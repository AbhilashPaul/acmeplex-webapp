package com.acmeplex.api.controller;

import com.acmeplex.api.model.Seat;
import com.acmeplex.api.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@CrossOrigin(origins = "http://localhost:4200") // Allow Angular access
public class SeatController {
    @Autowired
    private SeatService seatService;

    @GetMapping
    public List<Seat> getAllSeats() {
        return seatService.getAllSeats();
    }

    @PostMapping
    public Seat createSeat(@RequestBody Seat seat) {
        return seatService.createSeat(seat);
    }

    @DeleteMapping("/{id}")
    public void deleteSeat(@PathVariable Long id) {
        seatService.deleteSeat(id);
    }
}
