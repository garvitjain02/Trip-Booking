package com.spring.trip_booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.trip_booking.model.Flight;
import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.service.EntityService;

@RestController
@RequestMapping("/entity")
@CrossOrigin(origins = {"http://localhost:4200"})
public class EntityController {
	
	@Autowired
	EntityService entityService;
	
	@GetMapping("/getflight")
	public List<Flight> getFlight() {
		return entityService.getFlight();
	}
	
	@GetMapping("/gethotel")
	public List<Hotel> getHotel() {
		return entityService.getHotel();
	}

}
