package com.spring.trip_booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class BookingHasGuest {
	@ManyToOne
	private HotelBooking hotelBooking;
	
	@ManyToOne
	private Guest guest;

	public HotelBooking getHotelBooking() {
		return hotelBooking;
	}

	public void setHotelBooking(HotelBooking hotelBooking) {
		this.hotelBooking = hotelBooking;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	
}
