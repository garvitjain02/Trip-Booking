package com.spring.trip_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.trip_booking.model.UserPerformance;
import java.util.List;

public interface UserPerformanceRepository extends JpaRepository<UserPerformance, Integer> {
    // Custom method to find UserPerformance by User ID
    List<UserPerformance> findByUserId(int userId);
}
