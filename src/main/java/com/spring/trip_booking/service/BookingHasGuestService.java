package com.spring.trip_booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.trip_booking.repository.BookingHasGuestRepository;

@Service
public class BookingHasGuestService {

	@Autowired
	private BookingHasGuestRepository bookingHasGuestRepository;
}
