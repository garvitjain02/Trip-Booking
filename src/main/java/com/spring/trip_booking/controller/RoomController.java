package com.spring.trip_booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.Room;
import com.spring.trip_booking.service.HotelService;
import com.spring.trip_booking.service.RoomService;

@RestController
public class RoomController {

	@Autowired
	private RoomService roomService;

	@Autowired
	private HotelService hotelService;
	
	@PostMapping("/rooms/add/{hotelId}")
	public ResponseEntity<?> addRooms (@PathVariable int hotelId, @RequestBody List<Room> rooms) {
		Hotel hotel = null;
		try {
			hotel = hotelService.validate (hotelId);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		List<Room> list = roomService.addRooms (hotel, rooms);
		return ResponseEntity.ok(list);
	}
	
	
}
