package com.spring.trip_booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.Room;
import com.spring.trip_booking.repository.RoomRepository;

@Service
public class RoomService {
	@Autowired
	private RoomRepository roomRepository;

	public List<Room> addRooms(Hotel hotel, List<Room> rooms) {
		for (Room r : rooms) {
			r.setHotel(hotel);
		}
		return roomRepository.saveAll(rooms);
	}

	public Room validate(int roomId) throws ResourceNotFoundException {
		Optional<Room> optional = roomRepository.findById(roomId);
		if (optional.isEmpty())
			throw new ResourceNotFoundException("No Such Room Exists");
		return optional.get();
	}
	
	
	
}
