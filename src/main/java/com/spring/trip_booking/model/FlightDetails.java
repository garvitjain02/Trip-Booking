package com.spring.trip_booking.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Flight_Details")
public class FlightDetails implements Serializable {

    @Id
    @Column(name = "flight_id")
    private Integer flightId;

    @OneToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "flight_id", insertable = false, updatable = false)
    private Flight flight;

    @Column(name = "aircraft_type", length = 50)
    private String aircraftType;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "wifi")
    private Boolean wifi;

    @Column(name = "in_flight_meal")
    private Boolean inFlightMeal;

    // Constructors, Getters, and Setters

    public FlightDetails() {
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean getInFlightMeal() {
        return inFlightMeal;
    }

    public void setInFlightMeal(Boolean inFlightMeal) {
        this.inFlightMeal = inFlightMeal;
    }
}
