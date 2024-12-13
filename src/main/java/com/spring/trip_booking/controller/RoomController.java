package com.spring.trip_booking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.trip_booking.dto.RoomResponseDto;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.Amenity;
import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.Room;
import com.spring.trip_booking.model.RoomImages;
import com.spring.trip_booking.service.HotelService;
import com.spring.trip_booking.service.RoomHasAmenityService;
import com.spring.trip_booking.service.RoomImagesService;
import com.spring.trip_booking.service.RoomService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class RoomController {

	@Autowired
	private RoomService roomService;

	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private RoomImagesService roomImagesService;
	
	@Autowired
	private RoomHasAmenityService roomHasAmenityService;
	
	@PostMapping("/rooms/batch/add/{hotelId}")
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
	
	@PostMapping("/rooms/add")
	public ResponseEntity<?> addRooms (@RequestBody List<Room> rooms) {
		rooms.stream().forEach(r -> {
			Hotel hotel = null;
			try {
				hotel = hotelService.validate (r.getHotel().getId());
				r.setHotel(hotel);
			} catch (ResourceNotFoundException e) {
//				return ResponseEntity.badRequest().body(e.getMessage());
			}
		});
		
		List<Room> list = roomService.addBatchRooms (rooms);
		return ResponseEntity.ok(list);
	}
	
	
	@GetMapping("/rooms/{hid}")
	public ResponseEntity<?> getRoomsByHotel(@PathVariable int hid) throws ResourceNotFoundException {
		Hotel hotel = hotelService.validate(hid);
		List<Room> list = roomService.getRoomsByHotel(hotel);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/api/hotel/rooms/{id}")
	public List<RoomResponseDto> getHotelRoomsById(@PathVariable int id) throws ResourceNotFoundException {
		
		Hotel hotel = hotelService.validate(id);
		
		List<Room> rooms = roomService.getRoomsByHotel(hotel);
		
		List<RoomResponseDto> list = new ArrayList<>();
		rooms.stream().forEach(r -> {
			RoomResponseDto dto = new RoomResponseDto();
			dto.setRoom(r);
			
			List<RoomImages> images = roomImagesService.getAllImagesByRoom(r);
			if (images.size() > 0)
			dto.setImage(images.get(0));
			
			List<Amenity> amenities = roomHasAmenityService.getAmenitiesByRoom(r.getId());
			dto.setAmenities(amenities);
			
			list.add(dto);
			
		});
		
		return list;
	}
}
