package com.spring.trip_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.trip_booking.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer>{

}
