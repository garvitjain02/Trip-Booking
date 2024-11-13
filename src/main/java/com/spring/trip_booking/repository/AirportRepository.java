package com.spring.trip_booking.repository;

import com.spring.trip_booking.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, String> {
    // JpaRepository provides all basic CRUD operations.
    
}
