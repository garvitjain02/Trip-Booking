package com.spring.trip_booking.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.spring.trip_booking.model.Amenity;
import com.spring.trip_booking.model.Room;
import com.spring.trip_booking.model.RoomImages;

@Component
public class RoomResponseDto {

	private Room room;
	private List<Amenity> amenities;
	private List<RoomImages> images;
	
	
	
	public List<RoomImages> getImages() {
		return images;
	}
	public void setImages(List<RoomImages> images) {
		this.images = images;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public List<Amenity> getAmenities() {
		return amenities;
	}
	public void setAmenities(List<Amenity> amenities) {
		this.amenities = amenities;
	}

	
}
