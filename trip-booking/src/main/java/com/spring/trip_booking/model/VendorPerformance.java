package com.spring.trip_booking.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vendor_performance")
public class VendorPerformance {  // VendorPerformance M:1 UserInfo (Vendor)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Integer totalBookings;

    @Column(nullable = false)
    private Double averageRating;

    @OneToOne
    private UserInfo vendor;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(Integer totalBookings) {
        this.totalBookings = totalBookings;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public UserInfo getVendor() {
        return vendor;
    }

    public void setVendor(UserInfo vendor) {
        this.vendor = vendor;
    }
}
