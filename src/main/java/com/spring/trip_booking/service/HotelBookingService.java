package com.spring.trip_booking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.HotelBooking;
import com.spring.trip_booking.repository.HotelBookingRepository;

@Service
public class HotelBookingService {

	@Autowired
	private HotelBookingRepository hotelBookingRepository;
	
	public HotelBooking validate (int bid) throws ResourceNotFoundException {
		Optional<HotelBooking> optional = hotelBookingRepository.findById(bid);
		if (optional.isEmpty())
			throw new ResourceNotFoundException("Invalid Booking Id");
		return optional.get();
	}
}
