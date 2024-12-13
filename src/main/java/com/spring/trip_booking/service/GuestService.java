package com.spring.trip_booking.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

	public Guest addGuestID(int gid, MultipartFile file) throws ResourceNotFoundException, IOException {
		Guest g = validate(gid);
		
		String location = "";
		Path path = Path.of(location, file.getOriginalFilename());
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		
		g.setIdProof(path.toString());
		return g;
	}
}
