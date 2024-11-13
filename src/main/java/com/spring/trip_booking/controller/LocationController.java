package com.spring.trip_booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.trip_booking.model.Location;
import com.spring.trip_booking.service.LocationService;

@RestController
public class LocationController {

	@Autowired
	LocationService locationService;
	
	@PostMapping("/location/add")
	public Location addLocation (@RequestBody Location location) {
		return locationService.addLocation(location);
	}
	
	@GetMapping("/location/find")
	public List<Location> getLocationByName (@RequestParam String keyword) {
		return locationService.getLocationByName (keyword);
	}
}
