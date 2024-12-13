package com.spring.trip_booking.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.spring.trip_booking.model.Amenity;
import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.HotelImages;

@Component
public class HotelDetailDto {

	private Hotel hotel;
	private int rating;
	private List<HotelImages> hotelImages;
	private List<Amenity> amenities;
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public List<HotelImages> getHotelImages() {
		return hotelImages;
	}
	public void setHotelImages(List<HotelImages> hotelImages) {
		this.hotelImages = hotelImages;
	}
	public List<Amenity> getAmenities() {
		return amenities;
	}
	public void setAmenities(List<Amenity> amenities) {
		this.amenities = amenities;
	}
	
	
}
