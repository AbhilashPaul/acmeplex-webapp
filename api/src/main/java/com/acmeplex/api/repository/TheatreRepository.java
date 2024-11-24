package com.acmeplex.api.repository;

import com.acmeplex.api.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepository  extends JpaRepository<Theatre, Long> {
}
