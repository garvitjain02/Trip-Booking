package com.spring.trip_booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.spring.trip_booking.service.RoomHasBookingService;

@RestController
public class RoomHasBookingController {

	@Autowired
	private RoomHasBookingService roomHasBookingService;
}
