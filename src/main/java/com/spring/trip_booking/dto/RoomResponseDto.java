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
	private RoomImages image;
	
	
	public RoomImages getImage() {
		return image;
	}
	public void setImage(RoomImages image) {
		this.image = image;
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
