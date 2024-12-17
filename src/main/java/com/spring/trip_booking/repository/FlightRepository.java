package com.spring.trip_booking.repository;

import com.spring.trip_booking.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
   
    @Query("SELECT f FROM Flight f WHERE f.origin.city = :origin AND f.destination.city = :destination")
    List<Flight> findFlightsByOriginAndDestination(String origin, String destination);
}
