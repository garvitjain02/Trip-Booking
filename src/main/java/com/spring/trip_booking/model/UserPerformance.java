package com.spring.trip_booking.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class UserPerformance {  // UserPerformance M:1 UserInfo

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column()
    private int totalLogins;

    @Column()
    private int totalBookings;

    @Column()
    private LocalDate lastActivity;

    @OneToOne // Added cascade for owner
    private UserInfo user;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalLogins() {
        return totalLogins;
    }

    public void setTotalLogins(int totalLogins) {
        this.totalLogins = totalLogins;
    }

    public Integer getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(int totalBookings) {
        this.totalBookings = totalBookings;
    }

    public LocalDate getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(LocalDate lastActivity) {
        this.lastActivity = lastActivity;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}

