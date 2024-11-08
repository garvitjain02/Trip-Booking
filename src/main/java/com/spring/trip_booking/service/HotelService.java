package com.spring.trip_booking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.repository.HotelRepository;

@Service
public class HotelService {

	@Autowired
	HotelRepository hotelRepository;

	public Hotel addHotel(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	public Hotel validate(int hotelId) throws ResourceNotFoundException {
		Optional<Hotel> optional = hotelRepository.findById(hotelId);
		if (optional.isEmpty())
			throw new ResourceNotFoundException("There is No Hotel with this Id");
		
		return optional.get();
	}
}
