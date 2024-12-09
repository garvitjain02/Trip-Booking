package com.spring.trip_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.trip_booking.model.Flight;
import com.spring.trip_booking.model.FlightBooking;

@Repository
public interface FlightBookingRepository extends JpaRepository<FlightBooking, Integer>{

	@Query("Select h from FlightBooking h where h.user.username=?1")
	List<FlightBooking> getByUsername(String username);

	@Query("Select f from FlightBooking f join f.flight h where h.id=?1")
	List<FlightBooking> getFlightBookingByFlightId(int id);

}

