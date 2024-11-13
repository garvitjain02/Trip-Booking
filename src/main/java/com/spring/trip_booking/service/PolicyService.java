package com.spring.trip_booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.Policy;
import com.spring.trip_booking.repository.PolicyRepository;

@Service
public class PolicyService {

	@Autowired
	PolicyRepository policyRepository;

	public Policy addPolicy(Policy policy) {
		return policyRepository.save(policy);
	}

	public List<Policy> addBatchPolicy(List<Policy> policy) {
		return policyRepository.saveAll(policy);
	}

	public Policy validate(int id) throws ResourceNotFoundException {
		Optional<Policy> optional = policyRepository.findById(id);
		if (optional.isEmpty())
			throw new ResourceNotFoundException("Invalid Policy Id");
		return optional.get();
	}
}
