package com.spring.trip_booking.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.Room;
import com.spring.trip_booking.model.RoomImages;
import com.spring.trip_booking.repository.RoomImagesRepository;

@Service
public class RoomImagesService {

	@Autowired
	private RoomImagesRepository roomImagesRepository;
	
	@Autowired
	private RoomService roomService;

	public List<RoomImages> getAllImagesByHotel(Hotel hotel) {
		return roomImagesRepository.getAllImagesByHotel(hotel.getId());
	}

	public List<RoomImages> getAllImagesByRoom(Room r) {
		return roomImagesRepository.findAllByRoom(r);
	}

	public RoomImages addRoomImage(MultipartFile file, int rid) throws IOException, ResourceNotFoundException {
		String loc = "C:/Users/Admin/Desktop/Hexaware/Angular/hotel-booking/public/images";
		Path path = Path.of(loc, file.getOriginalFilename());
		
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		
		RoomImages roomImages = new RoomImages();
		roomImages.setRoom(roomService.validate(rid));
		roomImages.setUrl("images/"+file.getOriginalFilename());
		
		return roomImagesRepository.save(roomImages);
	}
}
