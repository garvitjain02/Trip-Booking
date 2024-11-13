package com.spring.trip_booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.spring.trip_booking.service.BookingHasGuestService;

@RestController
public class BookingHasGuestController {

	@Autowired
	private BookingHasGuestService bookingHasGuestService;
}
