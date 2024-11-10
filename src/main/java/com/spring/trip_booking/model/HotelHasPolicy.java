package com.spring.trip_booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class HotelHasPolicy {
	@ManyToOne
	private Hotel hotel;
	
	@ManyToOne
	private Policy policy;

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	
	
}
