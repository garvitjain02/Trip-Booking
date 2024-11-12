package com.spring.trip_booking.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class UserPerformance {  // UserPerformance M:1 UserInfo

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Integer totalLogins;

    @Column(nullable = false)
    private Integer totalBookings;

    @Column(nullable = false)
    private LocalDate lastActivity;

    @OneToOne
    private UserInfo user;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getTotalLogins() {
        return totalLogins;
    }

    public void setTotalLogins(Integer totalLogins) {
        this.totalLogins = totalLogins;
    }

    public Integer getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(Integer totalBookings) {
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

