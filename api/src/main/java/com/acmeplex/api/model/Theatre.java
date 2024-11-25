package com.acmeplex.api.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    @OneToMany(mappedBy = "theatre")
    private Set<Showtime> showtimes;

    public Theatre() {
    }

    public Theatre(Long id, String name, String email, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
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

    public Set<Showtime> getShowtimes() {
        return showtimes;
    }

    public void setShowtimes(Set<Showtime> showtimes) {
        this.showtimes = showtimes;
    }

    /**
     * Method to add a Showtime to the Theatre.
     * Ensures bidirectional relationship is maintained.
     *
     * @param showtime The Showtime object to be added.
     */
    public void addShowtime(Showtime showtime) {
        if (showtime != null) {
            showtime.setTheatre(this); // Maintain bidirectional relationship
            this.showtimes.add(showtime);
        }
    }
}
