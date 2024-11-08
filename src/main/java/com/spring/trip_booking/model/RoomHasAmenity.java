package com.spring.trip_booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class RoomHasAmenity {

	@ManyToOne
	private Room room;
	
	@ManyToOne
	private Amenity amenity;
	
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Amenity getAmenity() {
		return amenity;
	}

	public void setAmenity(Amenity amenity) {
		this.amenity = amenity;
	}

	
}
