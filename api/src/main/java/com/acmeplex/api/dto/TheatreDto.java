package com.acmeplex.api.dto;

import java.util.List;

public class TheatreDto {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private List<ShowtimeDto> showtimes;

    public TheatreDto() {
    }

    public TheatreDto(Long id, String name, String email, String address, String phoneNumber, List<ShowtimeDto> showtimes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.showtimes = showtimes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<ShowtimeDto> getShowtimes() {
        return showtimes;
    }

    public void setShowtimes(List<ShowtimeDto> showtimes) {
        this.showtimes = showtimes;
    }
}
