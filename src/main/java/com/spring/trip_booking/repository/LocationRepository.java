package com.spring.trip_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.trip_booking.model.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {

}
