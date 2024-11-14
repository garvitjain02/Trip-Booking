package com.spring.trip_booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.BookingHasGuest;
import com.spring.trip_booking.model.Guest;
import com.spring.trip_booking.model.HotelBooking;
import com.spring.trip_booking.service.BookingHasGuestService;
import com.spring.trip_booking.service.GuestService;
import com.spring.trip_booking.service.HotelBookingService;

@RestController
public class BookingHasGuestController {

	@Autowired
	private BookingHasGuestService bookingHasGuestService;
	
	@Autowired
	private HotelBookingService hotelBookingService;
	
	@Autowired
	private GuestService guestService;
	
	@PostMapping("/booking/guests/{bid}/{gid}")
	public BookingHasGuest addBookingGuest (@PathVariable int bid, @PathVariable int gid) throws ResourceNotFoundException {
		HotelBooking hotelBooking = hotelBookingService.validate(bid);
		Guest guest = guestService.validate(gid);
		
		BookingHasGuest bookingHasGuest = new BookingHasGuest();
		bookingHasGuest.setGuest(guest);
		bookingHasGuest.setHotelBooking(hotelBooking);
		
		return bookingHasGuestService.addBookingGuest(bookingHasGuest);
		
	}
}
