package com.spring.trip_booking.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.Amenity;
import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.Location;
import com.spring.trip_booking.model.Policy;
import com.spring.trip_booking.repository.HotelRepository;

@Service
public class HotelService {

	@Autowired
	HotelRepository hotelRepository;

	public Hotel addHotel(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	public Hotel validate(int hotelId) throws ResourceNotFoundException {
		Optional<Hotel> optional = hotelRepository.findById(hotelId);
		if (optional.isEmpty())
			throw new ResourceNotFoundException("There is No Hotel with this Id");
		
		return optional.get();
	}

	public List<Hotel> getAllHotels() {
		return hotelRepository.findAll();
	}

	public List<Hotel> getHotelByLocation(Location location) {
		return hotelRepository.findByLocation(location);
	}

	public List<Hotel> getHotelsWithDates(LocalDate in, LocalDate out, int guests) {
		return hotelRepository.getHotelsWithDates(in, out);
	}

	public List<Amenity> hotelHasAmenities(int hid) {
		return hotelRepository.hotelHasAmenities(hid);
	}

}
