package com.spring.trip_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.trip_booking.model.UserPerformance;
import java.util.List;

public interface UserPerformanceRepository extends JpaRepository<UserPerformance, Integer> {
    // Custom method to find UserPerformance by User ID
    List<UserPerformance> findByUserId(int userId);

    @Query("SELECT up, r.ratingValue, r.feedbackText, r.ratingDate " +
            "FROM UserPerformance up " +
            "JOIN up.user u " +
            "JOIN RatingTable r ON r.user.id = u.id " +
            "WHERE u.id = :userId")
     List<Object[]> findUserPerformanceWithRatings(int userId);
}
