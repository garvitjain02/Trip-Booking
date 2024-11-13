package com.spring.trip_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.trip_booking.model.RoomHasBooking;

public interface RoomHasBookingRepository extends JpaRepository<RoomHasBooking, Integer> {

}
