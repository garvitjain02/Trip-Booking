package com.spring.trip_booking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.Amenity;
import com.spring.trip_booking.model.Room;
import com.spring.trip_booking.model.RoomHasAmenity;
import com.spring.trip_booking.service.AmenityService;
import com.spring.trip_booking.service.RoomHasAmenityService;
import com.spring.trip_booking.service.RoomService;

@RestController
public class RoomHasAmenityController {

	@Autowired
	RoomHasAmenityService roomHasAmenityService;
	
	@Autowired
	private AmenityService amenityService;
	
	@Autowired
	private RoomService roomService;

	@PostMapping("/room/amenities/add")
	public ResponseEntity<?> addRoomHasAmenities (@RequestParam int roomId, @RequestBody List<Amenity> amenitites) {
		List<RoomHasAmenity> list = new ArrayList<>();
		
		// Validate if Room ID is valid
		Room r = null;
		try {
			r = roomService.validate(roomId);
		} catch (ResourceNotFoundException e) {
			ResponseEntity.badRequest().body(e.getMessage());
		}
		
		for (Amenity a : amenitites) {
			// Validate each Amenity is Valid
			try {
				a = amenityService.validate (a.getId());
			} catch (ResourceNotFoundException e) {
				ResponseEntity.badRequest().body(e.getMessage());
			}
	
			// Creating new Object of RoomHasAmenity and adding it to List
			RoomHasAmenity roomHasAmenity = new RoomHasAmenity();
			roomHasAmenity.setAmenity(a);
			roomHasAmenity.setRoom(r);
			list.add(roomHasAmenity);
		}
				
		list = roomHasAmenityService.addRoomHasAmenities(list);
		return ResponseEntity.ok(list);
	}
}
