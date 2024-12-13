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
	
	@GetMapping("/api/location/all")
	public List<Location> getAllLocations () {
		return locationService.getAllLocations();
	}
	
	@GetMapping("/location/find")
	public List<Location> getLocationByName (@RequestParam String keyword) {
		return locationService.getLocationByName (keyword);
	}
	
	@PostMapping("/location/batch/add")
	public List<Location> addBatchLocations (@RequestBody List<Location> locations) {
		return locationService.addBatchLocations(locations);
	}
}
