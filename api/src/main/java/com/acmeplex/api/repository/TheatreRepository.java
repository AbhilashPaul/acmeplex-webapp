package com.acmeplex.api.repository;

import com.acmeplex.api.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {
    @Query("SELECT DISTINCT t FROM Theatre t JOIN t.showtimes s WHERE s.movie.id = :movieId")
    List<Theatre> findTheatresByMovieId(@Param("movieId") Long movieId);
}
