package com.spring.trip_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.trip_booking.model.RatingTable;
import java.util.List;

public interface RatingTableRepository extends JpaRepository<RatingTable, Integer> {
	
	@Query("Select r from RatingTable r where r.hotel.id=?1")
	List<RatingTable> getByHotelId(int id);

	@Query("Select r from RatingTable r where r.flight.id=?1")
	List<RatingTable> getByFlightId(int id);

}
