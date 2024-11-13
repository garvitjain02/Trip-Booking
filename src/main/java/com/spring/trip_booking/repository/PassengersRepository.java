package com.spring.trip_booking.repository;

import com.spring.trip_booking.model.Passengers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengersRepository extends JpaRepository<Passengers, Long> {
    // JpaRepository now uses Long for the ID type
}
