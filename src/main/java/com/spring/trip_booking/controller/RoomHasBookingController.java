package com.spring.trip_booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.HotelBooking;
import com.spring.trip_booking.model.Room;
import com.spring.trip_booking.model.RoomHasBooking;
import com.spring.trip_booking.service.HotelBookingService;
import com.spring.trip_booking.service.RoomHasBookingService;
import com.spring.trip_booking.service.RoomService;

@RestController
public class RoomHasBookingController {

	@Autowired
	private RoomHasBookingService roomHasBookingService;
	
	@Autowired
	private HotelBookingService hotelBookingService;
	
	@Autowired
	private RoomService roomService;
	
	@PostMapping("/booking/guests/{bid}/{rid}")
	public RoomHasBooking addRoomBooking (@PathVariable int bid, @PathVariable int rid) throws ResourceNotFoundException {
		HotelBooking hotelBooking = hotelBookingService.validate(bid);
		Room room  = roomService.validate(rid);
		
		RoomHasBooking roomHasBooking = new RoomHasBooking();
		roomHasBooking.setHotelBooking(hotelBooking);
		roomHasBooking.setRoom(room);
		
		return roomHasBookingService.addRoomBooking(roomHasBooking);
	}
	
	
}
