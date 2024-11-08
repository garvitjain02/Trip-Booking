package com.spring.trip_booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.trip_booking.model.Amenity;
import com.spring.trip_booking.service.AmenityService;

@RestController
public class AmenityController {

	@Autowired
	AmenityService amenityService;
	
	@PostMapping("/amenity/batch/add")
	public List<Amenity> addAmenitites (@RequestBody List<Amenity> amenities) {
		return amenityService.addAmenitites (amenities);
	}
}
