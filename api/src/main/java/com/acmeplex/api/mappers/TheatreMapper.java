package com.acmeplex.api.mappers;

import com.acmeplex.api.dto.TheatreDto;
import com.acmeplex.api.model.Theatre;

import java.util.stream.Collectors;

public class TheatreMapper {
    public static TheatreDto toTheatreDto(Theatre theatre) {
        TheatreDto dto = new TheatreDto();
        dto.setId(theatre.getId());
        dto.setAddress(theatre.getAddress());
        dto.setEmail(theatre.getEmail());
        dto.setName(theatre.getName());
        dto.setPhoneNumber(theatre.getPhoneNumber());
        dto.setShowtimes(theatre.getShowtimes().stream()
                .map(ShowtimeMapper::toShowtimeDto)
                .collect(Collectors.toList()));
        return dto;
    }
}
