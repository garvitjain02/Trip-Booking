package com.spring.trip_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.trip_booking.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

}
