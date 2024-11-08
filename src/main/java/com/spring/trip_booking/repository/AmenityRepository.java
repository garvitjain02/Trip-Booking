package com.spring.trip_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.trip_booking.model.Amenity;

public interface AmenityRepository extends JpaRepository<Amenity, Integer> {

}
