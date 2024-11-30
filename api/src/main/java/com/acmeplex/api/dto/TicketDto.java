package com.acmeplex.api.dto;

import com.acmeplex.api.model.Seat;
import com.acmeplex.api.model.TicketStatus;

public class TicketDto {
    private Long id;

    private String customerName;
    private String customerEmail;

    private Double price;

    private Seat seat;

    private MovieDto movie;
    private ShowtimeDto showtime;
    private TicketStatus status;
    private PaymentReceiptDto paymentReceipt;


    public TicketDto(Long id, String customerName, String customerEmail,
                     Double price, Seat seat, MovieDto movie, ShowtimeDto showtime,
                     TicketStatus status, PaymentReceiptDto paymentReceipt) {
        this.id = id;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.price = price;
        this.seat = seat;
        this.movie = movie;
        this.showtime = showtime;
        this.status = status;
        this.paymentReceipt = paymentReceipt;
    }

    public TicketDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public MovieDto getMovie() {
        return movie;
    }

    public void setMovie(MovieDto movie) {
        this.movie = movie;
    }

    public ShowtimeDto getShowtime() {
        return showtime;
    }

    public void setShowtime(ShowtimeDto showtime) {
        this.showtime = showtime;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public PaymentReceiptDto getPaymentReceipt() {
        return paymentReceipt;
    }

    public void setPaymentReceipt(PaymentReceiptDto paymentReceipt) {
        this.paymentReceipt = paymentReceipt;
    }
}
