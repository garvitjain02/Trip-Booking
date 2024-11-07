package com.spring.trip_booking.model;

import jakarta.persistence.*;

@Entity
public class EntityPerformance {  // EntityPerformance M:1 EntityTable

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Integer totalBookings;

    @Column(nullable = false)
    private Double totalRevenue;

    @Column(nullable = false)
    private Double averageRating;

    @OneToOne
    private EntityTable entity;

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

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public EntityTable getEntity() {
        return entity;
    }

    public void setEntity(EntityTable entity) {
        this.entity = entity;
    }
}
