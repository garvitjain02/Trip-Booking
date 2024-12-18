package com.spring.trip_booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.trip_booking.model.Policy;
import com.spring.trip_booking.service.PolicyService;

@RestController
public class PolicyController {

	@Autowired
	PolicyService policyService;
	
	@PostMapping("/policy/add")
	public Policy addPolicy (@RequestBody Policy policy) {
		return policyService.addPolicy (policy);
	}
}
