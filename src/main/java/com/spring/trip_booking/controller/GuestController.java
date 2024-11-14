package com.spring.trip_booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.Guest;
import com.spring.trip_booking.model.UserInfo;
import com.spring.trip_booking.service.GuestService;
import com.spring.trip_booking.service.UserInfoService;

@RestController
public class GuestController {

	@Autowired
	private GuestService guestService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@PostMapping("/guest/add")
	public Guest addGuest (@RequestBody Guest guest) {
		return guestService.addGuest(guest);
	}
	
	@PostMapping("/guest/batch/add")
	public List<Guest> addBatchGuest (@RequestBody List<Guest> guest) {
		return guestService.addBatchGuest(guest);
	}
	
	@GetMapping("/guest/{uid}")
	public List<Guest> userGuests (@PathVariable int uid) throws ResourceNotFoundException {
		userInfoService.validate(uid);
		return guestService.userGuests (uid);
	}
	
	@GetMapping("/guest/all")
	public List<Guest> allGuests () {
		return guestService.allGuests();
	}
}
