package com.acmeplex.api.repository;

import com.acmeplex.api.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
}
