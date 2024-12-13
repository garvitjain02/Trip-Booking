package com.spring.trip_booking.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.HotelImages;
import com.spring.trip_booking.service.HotelImagesService;
import com.spring.trip_booking.service.HotelService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class HotelImagesController {

	@Autowired
	private HotelImagesService hotelImagesService;
	
	@Autowired
	private HotelService hotelService;
	
	@PostMapping("/api/hotel/image/add/{hid}")
	public HotelImages addHotelImage(@RequestBody MultipartFile file, @PathVariable int hid) throws IOException, ResourceNotFoundException {
		return hotelImagesService.addHotelImage(file, hid);
	}
	
	@GetMapping("/api/hotel/images")
	public List<HotelImages> getAllHotelImagesByHotelId(@RequestParam int hid) throws ResourceNotFoundException {
		Hotel h = hotelService.validate(hid);
		return hotelImagesService.getAllImagesByHotel(h);
	}
}
