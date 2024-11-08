package com.spring.trip_booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.trip_booking.model.RoomHasAmenity;
import com.spring.trip_booking.repository.RoomHasAmenityRepository;

@Service
public class RoomHasAmenityService {

	@Autowired
	RoomHasAmenityRepository roomHasAmenityRepository;

	public List<RoomHasAmenity> addRoomHasAmenities(List<RoomHasAmenity> list) {
		return roomHasAmenityRepository.saveAll(list);
	}
}
