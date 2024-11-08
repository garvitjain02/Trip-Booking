package com.spring.trip_booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.service.HotelService;

@RestController
public class HotelController {

	@Autowired
	HotelService hotelService;
	
	@PostMapping("/hotel/add")
	public Hotel addHotel (@RequestBody Hotel hotel) {
		return hotelService.addHotel (hotel);
	}
	
	
}
