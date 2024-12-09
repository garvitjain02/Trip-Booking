package com.spring.trip_booking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.trip_booking.enums.ActivityType;
import com.spring.trip_booking.model.Flight;
import com.spring.trip_booking.model.FlightBooking;
import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.LogTable;
import com.spring.trip_booking.repository.FlightBookingRepository;
import com.spring.trip_booking.repository.FlightRepository;
import com.spring.trip_booking.repository.LogTableRepository;

@Service
public class FlightBookingService {
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	FlightBookingRepository flightBookingRepository;
	
	@Autowired 
	LogTableService logTableService;
	
	@Autowired 
	LogTableRepository logTableRepository;
	
	public FlightBooking addFlightBooking(FlightBooking flightBooking) {
		// TODO Auto-generated method stub
		
		LogTable logTable=new LogTable();
		logTable.setTimestamp(LocalDateTime.now());
		logTable.setActivityType(ActivityType.FLIGHT_BOOKING);
		logTable.setActivityDesc("Flight was booked for Rs."+flightBooking.getAmount());
		logTable.setFlight(flightBooking.getFlight());
		logTable.setUser(flightBooking.getUser());
		logTableService.insert(logTable);
		
		return flightBookingRepository.save(flightBooking);
	}

	public List<FlightBooking> getByUsername(String username) {
		// TODO Auto-generated method stub
		return flightBookingRepository.getByUsername(username);
	}

	public List<FlightBooking> findAll() {
		// TODO Auto-generated method stub
		return flightBookingRepository.findAll();
	}
	
	public Flight addFlight(Flight flight) {
		return flightRepository.save(flight);
	}

	public List<Flight> getAllFlights() {
		// TODO Auto-generated method stub
		return flightRepository.findAll();
	}

	public List<Flight> getFlightsByVendor(String username) {
		// TODO Auto-generated method stub
		return flightRepository.getFlightsByVendor(username);
	}

	public Flight getFlightById(int id) {
		// TODO Auto-generated method stub
		return flightRepository.getByIdn(id);
	}

	public List<FlightBooking> getFlightBookingByFlightId(int id) {
		// TODO Auto-generated method stub
		return flightBookingRepository.getFlightBookingByFlightId(id);
	}

	public void deleteFlightById(int id) {
		
		Flight flight=flightRepository.getByIdn(id);
		LogTable logTable=new LogTable();
		logTable.setTimestamp(LocalDateTime.now());
		logTable.setActivityType(ActivityType.DELETE_FLIGHT);
		logTable.setUser(flight.getVendor());
		logTable.setActivityDesc("Flight Deleted. Flight number: "+flight.getFlightNumber()+" and Airline "+flight.getAirline());
		logTableRepository.save(logTable);
		// TODO Auto-generated method stub
	    // Delete associated FlightBooking entries
	    List<FlightBooking> flightBookings = flightBookingRepository.getFlightBookingByFlightId(id);
	    flightBookingRepository.deleteAll(flightBookings);

		 flightRepository.deleteById(id);;
	}

}
