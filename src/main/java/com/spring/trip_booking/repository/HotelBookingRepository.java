package com.spring.trip_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.HotelBooking;

public interface HotelBookingRepository extends JpaRepository<HotelBooking, Integer> {

	List<HotelBooking> findAllByHotel(Hotel hotel);

	@Query("select hb "
			+ "from HotelBooking hb "
			+ "join hb.hotel h "
			+ "join h.owner u "
			+ "where u.id = ?1")
	List<HotelBooking> getHotelBookingByVendor(int i);

}
