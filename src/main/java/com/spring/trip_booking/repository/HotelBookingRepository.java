package com.spring.trip_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.trip_booking.model.HotelBooking;

@Repository
public interface HotelBookingRepository extends JpaRepository<HotelBooking,Integer>{

	@Query("Select h from HotelBooking h where h.user.username=?1")
	List<HotelBooking> getByUsername(String username);

	@Query("Select h from HotelBooking h where h.hotel.id=?1")
	List<HotelBooking> getHotelBookingByHotelId(int id);

}
