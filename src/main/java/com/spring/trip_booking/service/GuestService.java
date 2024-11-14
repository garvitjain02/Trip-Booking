package com.spring.trip_booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.Guest;
import com.spring.trip_booking.repository.GuestRepository;

@Service
public class GuestService {

	@Autowired
	private GuestRepository guestRepository;

	public Guest addGuest(Guest guest) {
		return guestRepository.save(guest);
	}

	public List<Guest> userGuests(int uid) {
		return guestRepository.userGuests (uid);
	}

	public List<Guest> addBatchGuest(List<Guest> guest) {
		return guestRepository.saveAll(guest);
	}

	public List<Guest> allGuests() {
		return guestRepository.findAll();
	}

	public Guest validate(int gid) throws ResourceNotFoundException {
		Optional<Guest> optional = guestRepository.findById(gid);
		if (optional.isEmpty())
			throw new ResourceNotFoundException("No Such Guest with this Id");
		return optional.get();
	}
}
