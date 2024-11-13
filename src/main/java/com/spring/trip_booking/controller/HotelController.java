package com.spring.trip_booking.controller;

import java.time.LocalDate;
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
import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.Location;
import com.spring.trip_booking.model.RoomHasAmenity;
import com.spring.trip_booking.model.UserInfo;
import com.spring.trip_booking.service.HotelService;
import com.spring.trip_booking.service.LocationService;
import com.spring.trip_booking.service.RoomHasAmenityService;
import com.spring.trip_booking.service.UserInfoService;

@RestController
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@PostMapping("/hotel/add/{uid}/{lid}")
	public Hotel addHotel (@RequestBody Hotel hotel, @PathVariable int uid, @PathVariable int lid) throws ResourceNotFoundException {
		Location location = locationService.getLocationById(lid);
		UserInfo user = userInfoService.validate(uid);
		
		hotel.setLocation(location);
		hotel.setOwner(user);
		
		return hotelService.addHotel (hotel);
	}
	
	@GetMapping("/hotel/all")
	public List<Hotel> getAllHotels () {
		return hotelService.getAllHotels ();
	}
	
	@GetMapping("/hotel/all/{lid}")
	public ResponseEntity<?> getHotelByLocation (@PathVariable int lid) throws ResourceNotFoundException {
		Location location = locationService.getLocationById(lid);
		return ResponseEntity.ok(hotelService.getHotelByLocation(location));
	}
	
	@GetMapping("/hotel/find")
	public List<Hotel> getHotelsWithDateAndGuests (@RequestParam(required = false, defaultValue = "2") int guests, 
												@RequestParam(required = false) String checkIn, 
												@RequestParam(required = false) String checkOut) {
		LocalDate in;
		if (checkIn == null)
			in = LocalDate.now();
		else
			in = LocalDate.parse(checkIn);
		
		LocalDate out;
		if (checkOut == null)
			out = LocalDate.now().plusDays(1);
		else
			out = LocalDate.parse(checkOut);
		
		return hotelService.getHotelsWithDates (in, out, guests);
	}
	
	@GetMapping("/hotel/amenities/{hid}")
	public List<Amenity> hotelHasAmenities (@PathVariable int hid) throws ResourceNotFoundException {
		hotelService.validate(hid);
		return hotelService.hotelHasAmenities(hid);
	}
	
}
