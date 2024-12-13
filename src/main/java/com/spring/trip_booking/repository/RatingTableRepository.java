package com.spring.trip_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.trip_booking.model.RatingTable;

public interface RatingTableRepository extends JpaRepository<RatingTable, Integer> {

	@Query("select r "
			+ "from HotelBooking b "
			+ "join b.hotel h "
			+ "join b.rating r "
			+ "where h.id = ?1")
	List<RatingTable> getAllRatingsByHotel(int hid);

}
