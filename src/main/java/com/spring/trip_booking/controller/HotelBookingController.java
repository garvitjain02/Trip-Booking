package com.spring.trip_booking.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.HotelBooking;
import com.spring.trip_booking.service.HotelBookingService;
import java.util.List;

@RestController
@RequestMapping("/hotelbooking")
@CrossOrigin(origins = {"http://localhost:4200"})
public class HotelBookingController {
	
	@Autowired
	HotelBookingService hotelBookingService;
	
	//using
	@PostMapping("/add")
	public HotelBooking add(@RequestBody HotelBooking hotelBooking) {
		return hotelBookingService.add(hotelBooking);
	}
	
	@GetMapping("/getbyid/{id}")
	public Optional<HotelBooking> getById(@PathVariable int id) {
		return hotelBookingService.getById(id);
	}
	
	//using
	@GetMapping("/getbyusername/{username}")
	public List<HotelBooking> getByUsername(@PathVariable String username) {
		return hotelBookingService.getByUsername(username);	
	}
	
	//using
	@GetMapping("/getHotelById/{id}")
	public Hotel getHotelById(@PathVariable int id) {
		return hotelBookingService.getHotelById(id);
	}
	
	//using
	@GetMapping("/getHotelBookingByHotelId/{id}")
	public List<HotelBooking> getHotelBookingByHotelId(@PathVariable int id){
		return hotelBookingService.getHotelBookingByHotelId(id);
	}
	
	//using
	@DeleteMapping("/deleteHotelById/{id}")
	public void deleteHotelById(@PathVariable int id) {
		hotelBookingService.deleteHotelById(id);
	}
	
	@PostMapping("/addingHotel")
	public Hotel addHotel(@RequestBody Hotel hotel) {
		return hotelBookingService.addHotel(hotel);
	}
	

}
