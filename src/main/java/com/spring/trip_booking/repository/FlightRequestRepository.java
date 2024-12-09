package com.spring.trip_booking.repository;

import com.spring.trip_booking.model.Flight;
import com.spring.trip_booking.model.FlightRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightRequestRepository extends JpaRepository<FlightRequest, Integer> {

	@Query("Select f from FlightRequest f where f.id=?1")
	FlightRequest getByIdn(int id);

}
