package com.spring.trip_booking.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.RoomImages;
import com.spring.trip_booking.service.RoomImagesService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class RoomImagesController {
	@Autowired
	private RoomImagesService roomImagesService;
	
	@PostMapping("/api/room/image/add/{rid}")
	public RoomImages addHotelImage(@RequestBody MultipartFile file, @PathVariable int rid) throws IOException, ResourceNotFoundException {
		return roomImagesService.addRoomImage(file, rid);
	}
}
