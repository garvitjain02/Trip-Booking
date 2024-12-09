package com.spring.trip_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.trip_booking.model.Flight;
import com.spring.trip_booking.model.Hotel;

public interface FlightRepository extends JpaRepository<Flight,Integer>{
	
	@Query("Select f from Flight f join f.vendor v where v.username=?1")
	List<Flight> getFlightsByVendor(String username);

	@Query("Select f from Flight f where f.id=?1")
	Flight getByIdn(int id);

}
