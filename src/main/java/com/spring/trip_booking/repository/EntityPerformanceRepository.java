package com.spring.trip_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.trip_booking.model.EntityPerformance;
import java.util.List;

public interface EntityPerformanceRepository extends JpaRepository<EntityPerformance, Integer> {
    // Custom method to find EntityPerformance by Entity ID
    List<EntityPerformance> findByEntityId(int entityId);
}
