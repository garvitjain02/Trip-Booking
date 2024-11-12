package com.spring.trip_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.Location;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

	List<Hotel> findByLocation(Location location);

}
