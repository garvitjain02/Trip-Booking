package com.spring.trip_booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.Location;
import com.spring.trip_booking.service.HotelService;
import com.spring.trip_booking.service.LocationService;

@RestController
public class HotelController {

	@Autowired
	HotelService hotelService;
	
	@Autowired
	LocationService locationService;
	
	@PostMapping("/hotel/add")
	public Hotel addHotel (@RequestBody Hotel hotel) {
		return hotelService.addHotel (hotel);
	}
	
	@GetMapping("/hotel/all")
	public List<Hotel> getAllHotels () {
		return hotelService.getAllHotels ();
	}
	
	@GetMapping("/hotel/all/{id}")
	public ResponseEntity<?> getHotelByLocation (@PathVariable int lid) {
		Location location;
		try {
			location = locationService.getLocationById(lid);
			return ResponseEntity.ok(hotelService.getHotelByLocation(location));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
