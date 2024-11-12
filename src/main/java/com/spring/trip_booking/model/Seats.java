package com.spring.trip_booking.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Seats")
public class Seats implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Integer seatId;

    @ManyToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "flight_id")
    private Flight flight;

    @Column(name = "seat_number", length = 5)
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "class", length = 10)
    private SeatClass seatClass;

    @Column(name = "is_available")
    private Boolean isAvailable;

    // Enum for Seat Class
    public enum SeatClass {
        ECONOMY, BUSINESS, FIRST
    }

    // Constructors, Getters, and Setters

    public Seats() {
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public SeatClass getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(SeatClass seatClass) {
        this.seatClass = seatClass;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
