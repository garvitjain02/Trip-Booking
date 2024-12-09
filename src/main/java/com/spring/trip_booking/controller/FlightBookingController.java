package com.spring.trip_booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.trip_booking.model.Flight;
import com.spring.trip_booking.model.FlightBooking;
import com.spring.trip_booking.service.FlightBookingService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/flightbooking")
@CrossOrigin(origins = {"http://localhost:4200"})
public class FlightBookingController {
	
	@Autowired
	FlightBookingService flightBookingService;
	
	//using
	@PostMapping("/add")
	public FlightBooking addFlightBooking(@RequestBody FlightBooking flightBooking) {
		return flightBookingService.addFlightBooking(flightBooking);
	}
	
	//using
	@GetMapping("/all")
	public List<FlightBooking> showAllFlightBooking() {
		return flightBookingService.findAll();
	}
	
	//using
	@GetMapping("/getbyusername/{username}")
	public List<FlightBooking> getByUsername(@PathVariable String username) {
		return flightBookingService.getByUsername(username);
		
	}
	
	//using
	@PostMapping("/addFlights")
	public Flight addFlight(@RequestBody Flight flight) {
		return flightBookingService.addFlight(flight);
	}
	
	//using
	@GetMapping("/getAllFlights")
	public List<Flight> getAllFlights() {
		return flightBookingService.getAllFlights();
	}	
	
	//using
	@GetMapping("/getFlightsByVendor/{username}")
	public List<Flight> getFlightsByVendor(@PathVariable String username){
		return flightBookingService.getFlightsByVendor(username);
	}
	
	//using
	@GetMapping("/getFlightBookingByFlightId/{id}")
	public List<FlightBooking> getFlightBookingByFlightId(@PathVariable int id) {
		return flightBookingService.getFlightBookingByFlightId(id);
		}
	
	@GetMapping("/getById/{id}")
	public Flight getFlightById(@PathVariable int id) {
		return flightBookingService.getFlightById(id);
		}
	
	@DeleteMapping("/deleteFlightById/{id}")
	public void deleteFlightById(@PathVariable int id) {
		 flightBookingService.deleteFlightById(id);
	}
	
}
