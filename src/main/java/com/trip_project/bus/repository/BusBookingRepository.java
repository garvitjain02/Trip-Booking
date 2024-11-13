package com.trip_project.bus.repository;

import com.trip_project.bus.model.BusBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusBookingRepository extends JpaRepository<BusBooking, Integer> {

    // Custom query methods

    // Find bookings by user ID
    List<BusBooking> findByUserId(int userId);

    // Find bookings by bus ID
    List<BusBooking> findByBusId(int busId);
}
