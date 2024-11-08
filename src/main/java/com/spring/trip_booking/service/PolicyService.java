package com.spring.trip_booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.trip_booking.model.Policy;
import com.spring.trip_booking.repository.PolicyRepository;

@Service
public class PolicyService {

	@Autowired
	PolicyRepository policyRepository;

	public Policy addPolicy(Policy policy) {
		return policyRepository.save(policy);
	}
}
