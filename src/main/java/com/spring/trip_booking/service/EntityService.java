package com.spring.trip_booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.trip_booking.model.Flight;
import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.repository.FlightRepository;
import com.spring.trip_booking.repository.HotelRepository;

@Service
public class EntityService {
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	HotelRepository hotelRepository;

	public List<Flight> getFlight() {
		// TODO Auto-generated method stub
		System.out.print("hello i am called");
		return flightRepository.findAll();
	}

	public List<Hotel> getHotel() {
		// TODO Auto-generated method stub
		return hotelRepository.findAll();
	}

}
