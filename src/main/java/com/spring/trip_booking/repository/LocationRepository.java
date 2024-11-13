package com.spring.trip_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.trip_booking.model.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {

	@Query("select l from Location l "
			+ "where l.city like CONCAT('%', ?1, '%') "
			+ "or l.state like CONCAT('%', ?1, '%') "
			+ "or l.country like CONCAT('%', ?1, '%')")
	List<Location> getLocationByName(String keyword);

}
