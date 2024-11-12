package com.spring.trip_booking.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Flight_Pricing")
public class FlightPricing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pricing_id")
    private Integer pricingId;

    @ManyToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "flight_id")
    private Flight flight;

    @Enumerated(EnumType.STRING)
    @Column(name = "class", length = 10)
    private SeatClass seatClass;

    @Column(name = "base_price", precision = 10, scale = 2)
    private Double basePrice;

    @Column(name = "baggage_allowance")
    private Integer baggageAllowance;

    @Column(name = "seat_availability")
    private Integer seatAvailability;

    // Enum for Seat Class
    public enum SeatClass {
        ECONOMY, BUSINESS, FIRST
    }

    // Constructors, Getters, and Setters

    public FlightPricing() {
    }

    public Integer getPricingId() {
        return pricingId;
    }

    public void setPricingId(Integer pricingId) {
        this.pricingId = pricingId;
    }

    public SeatClass getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(SeatClass seatClass) {
        this.seatClass = seatClass;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public Integer getBaggageAllowance() {
        return baggageAllowance;
    }

    public void setBaggageAllowance(Integer baggageAllowance) {
        this.baggageAllowance = baggageAllowance;
    }

    public Integer getSeatAvailability() {
        return seatAvailability;
    }

    public void setSeatAvailability(Integer seatAvailability) {
        this.seatAvailability = seatAvailability;
    }
}
