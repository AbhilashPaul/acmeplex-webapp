package com.acmeplex.api.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String customerEmail;

    @ManyToOne
    @JoinColumn(name = "seatId", nullable = false)
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "showtimeId", nullable = false)
    private Showtime showtime;

    @CreationTimestamp
    private Date bookingTime;

    private String paymentStatus;


    @OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL)
    private PaymentReceipt paymentReceipt;

    public Ticket() {
    }

    public Ticket(String customerName, String customerEmail, Seat seat, Showtime showtime) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.seat = seat;
        this.showtime = showtime;
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

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public PaymentReceipt getPaymentReceipt() {
        return paymentReceipt;
    }

    public void setPaymentReceipt(PaymentReceipt paymentReceipt) {
        this.paymentReceipt = paymentReceipt;
    }
}
