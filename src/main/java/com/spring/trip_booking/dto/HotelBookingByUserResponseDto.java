package com.spring.trip_booking.dto;

import org.springframework.stereotype.Component;

import com.spring.trip_booking.model.HotelBooking;
import com.spring.trip_booking.model.HotelImages;

@Component
public class HotelBookingByUserResponseDto {

	private HotelBooking hotelBooking;
	private HotelImages hotelImages;
	
	public HotelBooking getHotelBooking() {
		return hotelBooking;
	}
	public void setHotelBooking(HotelBooking hotelBooking) {
		this.hotelBooking = hotelBooking;
	}
	public HotelImages getHotelImages() {
		return hotelImages;
	}
	public void setHotelImages(HotelImages hotelImages) {
		this.hotelImages = hotelImages;
	}
	
	
}
