package com.spring.trip_booking.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Passengers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passengerId;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String passportNumber;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private FlightBookings booking;

    // Constructor
    public Passengers() {}

    public Passengers(String firstName, String lastName, LocalDate dateOfBirth, String passportNumber, FlightBookings booking) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.passportNumber = passportNumber;
        this.booking = booking;
    }

    // Getters and Setters
    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public FlightBookings getBooking() {
        return booking;
    }

    public void setBooking(FlightBookings booking) {
        this.booking = booking;
    }
}
