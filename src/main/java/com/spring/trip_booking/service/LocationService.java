package com.spring.trip_booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.Location;
import com.spring.trip_booking.repository.LocationRepository;

@Service
public class LocationService {

	@Autowired
	private LocationRepository locationRepository;

	public Location getLocationById(int lid) throws ResourceNotFoundException {
		Optional<Location> optional = locationRepository.findById(lid);
		if (optional.isEmpty())
			throw new ResourceNotFoundException("Invalid Location");
		
		return optional.get();
	}

	public Location addLocation(Location location) {
		return locationRepository.save(location);
	}

	public List<Location> getLocationByName(String keyword) {
		return locationRepository.getLocationByName(keyword);
	}
}
