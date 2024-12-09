package com.spring.trip_booking.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RatingTable {  

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne // Added cascade for owner
    private Hotel hotel;
    
    public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

    @ManyToOne // Added cascade for owner
    private Flight flight;

    @Column()
    private float ratingValue;

    @Column()
    private LocalDateTime ratingDate;

    @Column(length = 1000)
    private String feedbackText;

    @ManyToOne  // Added cascade for owner
    private UserInfo user;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(float ratingValue) {
        this.ratingValue = ratingValue;
    }

    public LocalDateTime getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(LocalDateTime ratingDate) {
        this.ratingDate = ratingDate;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}
