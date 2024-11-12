package com.spring.trip_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.trip_booking.model.RatingTable;
import java.util.List;

public interface RatingTableRepository extends JpaRepository<RatingTable, Integer> {
    // Custom method to find ratings by entity ID
    List<RatingTable> findByEntityId(int entityId);

    // Custom method to find ratings by user ID
    List<RatingTable> findByUserId(int userId);
}
