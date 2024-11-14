package com.spring.trip_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.trip_booking.model.Guest;

public interface GuestRepository extends JpaRepository<Guest, Integer> {

	@Query("select g "
			+ "from BookingHasGuest bg "
			+ "join bg.hotelBooking hb "
			+ "join bg.guest g "
			+ "join hb.user u "
			+ "where u.id = ?1")
	List<Guest> userGuests(int uid);

}
