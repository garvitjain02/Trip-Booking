package com.spring.trip_booking.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.trip_booking.enums.ApprovalStatus;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.HotelBooking;
import com.spring.trip_booking.model.UserInfo;
import com.spring.trip_booking.service.HotelBookingService;
import com.spring.trip_booking.service.HotelService;
import com.spring.trip_booking.service.UserInfoService;
import com.spring.trip_booking.service.UserSecurityService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class HotelBookingController {

	@Autowired
	private HotelBookingService hotelBookingService;
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	@GetMapping("/hotel/approval/requests/{hid}")
	public List<HotelBooking> getHotelApprovalRequests (@PathVariable int hid) throws ResourceNotFoundException {
		Hotel hotel = hotelService.validate(hid);
		return hotelBookingService.getHotelApprovalRequests(hotel);
	}
	
	@GetMapping("/bookings/all/{hid}")
	public List<HotelBooking> getAllBookingsByHotel (@PathVariable int hid) throws ResourceNotFoundException {
		Hotel hotel = hotelService.validate(hid);
		return hotelBookingService.getAllBookingsByHotel(hotel);
	}
	
	@PostMapping("/api/booking/add")
	public HotelBooking addBooking (@RequestBody HotelBooking booking, Principal principal) throws ResourceNotFoundException {
		booking.setUser((UserInfo) userSecurityService.loadUserByUsername(principal.getName()));
		booking.setHotel(hotelService.validate(booking.getHotel().getId()));
		return hotelBookingService.addBooking(booking);
	}
	
	@GetMapping("/api/booking/requests")
	public List<HotelBooking> getHotelBookingByVendor (@RequestParam String username) {
		UserInfo user = (UserInfo) userSecurityService.loadUserByUsername(username);
		return hotelBookingService.getHotelBookingByVendor(user);
	}

	@PutMapping("/api/approvalStatus/{bid}")
	public HotelBooking updateApprovalStatus (@PathVariable int bid, @RequestParam String status) throws ResourceNotFoundException {
		HotelBooking hotelBooking = hotelBookingService.validate(bid);
		hotelBooking.setApprovalStatus(ApprovalStatus.valueOf(status));
		return hotelBookingService.updateApprovalStatus(hotelBooking);
	}
}