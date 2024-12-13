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
import com.spring.trip_booking.model.HotelImages;
import com.spring.trip_booking.repository.HotelImagesRepository;

@Service
public class HotelImagesService {

	@Autowired
	private HotelImagesRepository hotelImagesRepository;
	
	@Autowired
	private HotelService hotelService;

	public List<HotelImages> getAllImagesByHotel(Hotel h) {
		return hotelImagesRepository.findAllByHotel(h);
	}

	public HotelImages addHotelImage(MultipartFile file, int hid) throws IOException, ResourceNotFoundException {
		String loc = "C:/Users/Admin/Desktop/Hexaware/Angular/hotel-booking/public/images";
		Path path = Path.of(loc, file.getOriginalFilename());
		
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		
		HotelImages hotelImages = new HotelImages();
		hotelImages.setHotel(hotelService.validate(hid));
		hotelImages.setUrl("images/"+file.getOriginalFilename());
		
		return hotelImagesRepository.save(hotelImages);
	}
}
