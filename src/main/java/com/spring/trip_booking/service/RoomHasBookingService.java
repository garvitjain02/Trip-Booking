package com.spring.trip_booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.trip_booking.model.RoomHasBooking;
import com.spring.trip_booking.repository.RoomHasBookingRepository;

@Service
public class RoomHasBookingService {

	@Autowired
	private RoomHasBookingRepository roomHasBookingRepository;

	public RoomHasBooking addRoomBooking(RoomHasBooking roomHasBooking) {
		return roomHasBookingRepository.save(roomHasBooking);
	}
}
