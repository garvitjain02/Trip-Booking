package com.spring.trip_booking.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
	    name = "RatingTable",
	    uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "entity_id"})}
	)
public class RatingTable {  // RatingTable M:1 UserInfo, M:1 EntityTable

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private EntityTable entity;

    @Column(nullable = false)
    private float ratingValue;

    @Column(nullable = false)
    private LocalDateTime ratingDate;

    @Column(length = 1000)
    private String feedbackText;

    @ManyToOne
    private UserInfo user;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EntityTable getEntity() {
        return entity;
    }

    public void setEntity(EntityTable entity) {
        this.entity = entity;
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
