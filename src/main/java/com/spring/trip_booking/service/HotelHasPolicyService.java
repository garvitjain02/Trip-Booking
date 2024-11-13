package com.spring.trip_booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.HotelHasPolicy;
import com.spring.trip_booking.model.Policy;
import com.spring.trip_booking.repository.HotelHasPolicyRepository;

@Service
public class HotelHasPolicyService {

	@Autowired
	HotelHasPolicyRepository hotelHasPolicyRepository;

	public List<HotelHasPolicy> getHotelPolicies(Hotel hotel) {
		return hotelHasPolicyRepository.findAllByHotel(hotel);
	}

	public List<HotelHasPolicy> addHotelHasPolicies(List<HotelHasPolicy> list) {
		return hotelHasPolicyRepository.saveAll(list);
	}
}
