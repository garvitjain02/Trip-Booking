package com.spring.trip_booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class RoomHasBooking {
	@ManyToOne
	private Room room;
	
	@ManyToOne
	private HotelBooking hotelBooking;

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public HotelBooking getHotelBooking() {
		return hotelBooking;
	}

	public void setHotelBooking(HotelBooking hotelBooking) {
		this.hotelBooking = hotelBooking;
	}

	
}
