package com.spring.trip_booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.Amenity;
import com.spring.trip_booking.repository.AmenityRepository;

@Service
public class AmenityService {

	@Autowired
	AmenityRepository amenityRepository;

	public List<Amenity> addAmenitites(List<Amenity> amenities) {
		return amenityRepository.saveAll(amenities);
	}

	public Amenity validate(int i) throws ResourceNotFoundException {
		Optional<Amenity> optional = amenityRepository.findById(i);
		if (optional.isEmpty())
			throw new ResourceNotFoundException("There is No Such Amenity");
		return optional.get();
	}
	
	
}
