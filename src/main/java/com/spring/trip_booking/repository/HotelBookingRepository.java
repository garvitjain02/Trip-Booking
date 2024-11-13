package com.spring.trip_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.trip_booking.model.HotelBooking;

public interface HotelBookingRepository extends JpaRepository<HotelBooking, Integer> {

}
