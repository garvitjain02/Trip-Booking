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
import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.HotelHasPolicy;
import com.spring.trip_booking.model.Policy;
import com.spring.trip_booking.service.HotelHasPolicyService;
import com.spring.trip_booking.service.HotelService;
import com.spring.trip_booking.service.PolicyService;

@RestController
public class HotelHasPolicyController {

	@Autowired
	private HotelHasPolicyService hotelHasPolicyService;
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private PolicyService policyService;
	
	@PostMapping("/hotel/policies/add")
	public ResponseEntity<?> addHotelHasPolicies (@RequestParam int hid, @RequestBody List<Policy> policies) throws ResourceNotFoundException {
		List<HotelHasPolicy> list = new ArrayList<>();
		
		// Validate if Room ID is valid
		Hotel h = hotelService.validate(hid);
		
		for (Policy p : policies) {
			// Validate each Policy is Valid
			p = policyService.validate (p.getId());
			
			// Creating new Object of HotelHasPolicy and adding it to List
			HotelHasPolicy hotelHasPolicy = new HotelHasPolicy();
			hotelHasPolicy.setPolicy(p);
			hotelHasPolicy.setHotel(h);
			list.add(hotelHasPolicy);
		}
				
		list = hotelHasPolicyService.addHotelHasPolicies(list);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/hotel/policies/{hid}")
	public List<HotelHasPolicy> getHotelPolicies (@PathVariable int hid) throws ResourceNotFoundException {
		Hotel hotel = hotelService.validate(hid);
		return hotelHasPolicyService.getHotelPolicies(hotel);
	}
}
