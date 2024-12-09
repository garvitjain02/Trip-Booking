package com.spring.trip_booking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.trip_booking.enums.ActivityType;
import com.spring.trip_booking.model.Flight;
import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.HotelBooking;
import com.spring.trip_booking.model.LogTable;
import com.spring.trip_booking.repository.HotelBookingRepository;
import com.spring.trip_booking.repository.HotelRepository;
import com.spring.trip_booking.repository.LogTableRepository;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class HotelBookingService {
	
	@Autowired
	HotelBookingRepository hotelBookingRepository;
	
	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired 
	LogTableRepository logTableRepository;

	public HotelBooking add(HotelBooking hotelBooking) {
		
		LogTable logTable=new LogTable();
		logTable.setTimestamp(LocalDateTime.now());
		logTable.setActivityType(ActivityType.HOTEL_BOOKING);
		logTable.setActivityDesc("Hotel was booked for Rs."+hotelBooking.getAmount());
		logTable.setHotel(hotelBooking.getHotel());
		logTable.setUser(hotelBooking.getUser());
		logTableRepository.save(logTable);
		
		return hotelBookingRepository.save(hotelBooking);		
	}

	public Optional<HotelBooking> getById(int id) {
		// TODO Auto-generated method stub
		return hotelBookingRepository.findById(id);
	}

	public List<HotelBooking> getByUsername(String username) {
		// TODO Auto-generated method stub
		return hotelBookingRepository.getByUsername(username);
	}

	public Hotel getHotelById(int id) {
		// TODO Auto-generated method stub
		return hotelRepository.getHotelById(id);
	}

	public List<HotelBooking> getHotelBookingByHotelId(int id) {
		// TODO Auto-generated method stub
		return hotelBookingRepository.getHotelBookingByHotelId(id);
	}

	public void deleteHotelById(int id) {
		
		Hotel hotel=hotelRepository.getHotelById(id);
		LogTable logTable=new LogTable();
		logTable.setTimestamp(LocalDateTime.now());
		logTable.setActivityType(ActivityType.DELETE_HOTEL);
		logTable.setUser(hotel.getOwner());
		logTable.setActivityDesc("Hotel Deleted. Hotel name: "+hotel.getName());
		logTableRepository.save(logTable);
		// TODO Auto-generated method stub
		hotelRepository.deleteById(id);;
	}

	public Hotel addHotel(Hotel hotel) {
		// TODO Auto-generated method stub
		return hotelRepository.save(hotel);
	}

}
