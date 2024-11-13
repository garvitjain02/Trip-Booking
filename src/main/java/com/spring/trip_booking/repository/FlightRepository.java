package com.spring.trip_booking.repository;

import com.spring.trip_booking.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    // JpaRepository provides all basic CRUD operations.
    // Additional custom queries can be added if needed.
}
