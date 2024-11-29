package com.acmeplex.api.dto;

public class CreateTicketRequestDto {
    private String customerName;
    private String customerEmail;
    private Long seatId;
    private Long showtimeId;

    public CreateTicketRequestDto(String customerName, String customerEmail, Long seatId, Long showtimeId) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.seatId = seatId;
        this.showtimeId = showtimeId;
    }

    public CreateTicketRequestDto() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public Long getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(Long showtimeId) {
        this.showtimeId = showtimeId;
    }
}
