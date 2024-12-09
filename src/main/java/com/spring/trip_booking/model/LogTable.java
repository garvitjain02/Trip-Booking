package com.spring.trip_booking.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.spring.trip_booking.enums.ActivityType;

@Entity
public class LogTable {  // LogTable M:1 UserInfo, M:1 EntityTable

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Enumerated(EnumType.STRING)
    private ActivityType activityType;

    @Column()
    private LocalDateTime timestamp;

    public ActivityType getActivityType() {
		return activityType;
	}

	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}

    @ManyToOne// Added cascade for owner
    private UserInfo user;

    @Column(length = 500)
    private String activityDesc;
    
    @ManyToOne // Added cascade for owner
    private Flight flight;
    
    @ManyToOne // Added cascade for owner
    private Hotel hotel;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public String getActivityDesc() {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }
}
