package com.spring.trip_booking.model;

import jakarta.persistence.*;

@Entity
public class VendorPerformance {  // VendorPerformance M:1 UserInfo (Vendor)

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column()
    private int totalBookings;

    @Column()
    private double averageRating;

    @OneToOne // Added cascade for owner
    private UserInfo vendor;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(int totalBookings) {
        this.totalBookings = totalBookings;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public UserInfo getVendor() {
        return vendor;
    }

    public void setVendor(UserInfo vendor) {
        this.vendor = vendor;
    }
}
