package com.spring.trip_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.trip_booking.model.LogTable;

public interface LogTableRepository extends JpaRepository<LogTable, Integer> {

    // Custom query to fetch logs by entity ID
    @Query("SELECT l FROM LogTable l WHERE l.entity.id = :entityId")
    List<LogTable> findLogsByEntity(@Param("entityId") int entityId);
}
