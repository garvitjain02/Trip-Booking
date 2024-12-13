package com.spring.trip_booking.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.trip_booking.dto.HotelDetailDto;
import com.spring.trip_booking.dto.HotelResponseDto;
import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.Amenity;
import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.HotelImages;
import com.spring.trip_booking.model.Location;
import com.spring.trip_booking.model.RatingTable;
import com.spring.trip_booking.model.Room;
import com.spring.trip_booking.model.UserInfo;
import com.spring.trip_booking.service.AmenityService;
import com.spring.trip_booking.service.HotelImagesService;
import com.spring.trip_booking.service.HotelService;
import com.spring.trip_booking.service.LocationService;
import com.spring.trip_booking.service.RatingTableService;
import com.spring.trip_booking.service.RoomService;
import com.spring.trip_booking.service.UserInfoService;
import com.spring.trip_booking.service.UserSecurityService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RatingTableService ratingTableService;
	
	@Autowired 
	private AmenityService amenityService;
	
	@Autowired
	private HotelImagesService hotelImagesService;

	@Autowired
	private UserSecurityService userSecurityService;
	
	Logger logger = LoggerFactory.getLogger(HotelController.class);
	
	// To add hotel for respective user and location
	@PostMapping("/hotel/add/{uid}/{lid}")
	public Hotel addHotel (@RequestBody Hotel hotel, @PathVariable int uid, @PathVariable int lid) throws ResourceNotFoundException {
		Location location = locationService.getLocationById(lid);
		UserInfo user = userInfoService.validate(uid);
		
		hotel.setLocation(location);
		hotel.setOwner(user);
		
		return hotelService.addHotel (hotel);
	}
	
	// Get all hotels in pages
	@GetMapping("/api/hotel/all")
	public Page<Hotel> getAllHotelsInPages (@RequestParam(required = false, defaultValue = "0") int page,
											@RequestParam(required = false, defaultValue = "10") int size) {
		
		Pageable pageable = PageRequest.of(page, size);
		return hotelService.getAllHotelsInPages(pageable);
	}
	
	// Get all hotels
	@GetMapping("/hotel/all")
	public List<Hotel> getAllHotels () {
		return hotelService.getAllHotels ();
	}
	
	// Get hotels by location
	@GetMapping("/hotel/all/{lid}")
	public ResponseEntity<?> getHotelByLocation (@PathVariable int lid) throws ResourceNotFoundException {
		Location location = locationService.getLocationById(lid);
		return ResponseEntity.ok(hotelService.getHotelByLocation(location));
	}
	
	// Get hotels by dates and guests
	@GetMapping("/hotel/find")
	public List<Hotel> getHotelsWithDateAndGuests (@RequestParam(required = false, defaultValue = "2") int guests, 
												@RequestParam(required = false) String checkIn, 
												@RequestParam(required = false) String checkOut,
												@RequestParam String location) {
		LocalDate in;
		if (checkIn == null)
			in = LocalDate.now();
		else
			in = LocalDate.parse(checkIn);
		
		LocalDate out;
		if (checkOut == null)
			out = LocalDate.now().plusDays(1);
		else
			out = LocalDate.parse(checkOut);
		logger.warn("Returning List of hotels without using number of guests");
		
		return hotelService.getHotelsWithDates (in, out, guests, location);
	}
	
	// Get all amenities of a particular hotel
	@GetMapping("/hotel/amenities/{hid}")
	public List<Amenity> hotelHasAmenities (@PathVariable int hid) throws ResourceNotFoundException {
		hotelService.validate(hid);
		logger.info("Hotel Id Validated. Now fetching all amenities");
		return hotelService.hotelHasAmenities(hid);
	}
	
	// Search and Get all hotels based on checkin and checkout dates with location
	@GetMapping("/api/hotel/search")
	public List<HotelResponseDto> searchHotels(@RequestParam(required = false, defaultValue = "2") int guests, 
											@RequestParam(required = false) String checkIn, 
											@RequestParam(required = false) String checkOut,
											@RequestParam String location) {
		
		LocalDate in;
		if (checkIn == null)
			in = LocalDate.now();
		else
			in = LocalDate.parse(checkIn);
		
		LocalDate out;
		if (checkOut == null)
			out = LocalDate.now().plusDays(1);
		else
			out = LocalDate.parse(checkOut);
		
		//Getting Hotels which are not available for selected dates
		List<Hotel> hot = hotelService.getHotelsWithDates (in, out, guests, location);

		//Getting All Hotels
		List<Hotel> hotels = hotelService.getAllHotels();
		logger.warn("All Hotels Fetched!!");
		
		//Filtering hotels which are not available
		hotels = hotels.stream().filter(h -> hot.stream().noneMatch(oh -> oh.getId() == h.getId())).toList();
		
		//Filtering hotels based on location
		hotels = hotels.stream().filter(h -> h.getLocation().getCity().equals(location)).toList();
		
		List<HotelResponseDto> list = new ArrayList<>();
		hotels.stream().forEach(h -> {
			HotelResponseDto dto = new HotelResponseDto();
			dto.setHotel(h);
			
			List<Room> rooms = roomService.getRoomsByHotel(h);
			List<String> roomNames = new ArrayList<>();
	        rooms.stream().forEach(room -> roomNames.add(room.getType()));
	        dto.setRooms(roomNames);
	        
	        List<Amenity> amenities = amenityService.getAmenitiesByHotel(h.getId());
	        List<String> facilites = new ArrayList<>();
	        amenities.stream().forEach(a -> facilites.add(a.getName()));
	        dto.setFacilities(facilites);
	        
	        dto.setStartPrice(rooms.stream().mapToDouble(r -> r.getPrice()).min().orElse(1000));
	        
	        List<RatingTable> ratings = ratingTableService.getAllRatingsByHotel(h.getId());
	        dto.setRating((int)ratings.stream().mapToDouble(r -> r.getRatingValue()).average().orElse(0.00));
	        
	        List<HotelImages> images = hotelImagesService.getAllImagesByHotel(h);
	        if (images.size() > 0)
	        	dto.setImageUrl(images.get(0).getUrl());
	        
	        list.add(dto);
		});
		
		return list;
	}
	
	// Get specific details of a particular hotel
	@GetMapping("/api/hotel/details/{hid}")
	public HotelDetailDto getHotelDetails (@PathVariable int hid) throws ResourceNotFoundException {
		HotelDetailDto dto = new HotelDetailDto();
		
		Hotel hotel = hotelService.validate(hid);
		logger.info("Hotel Id Validated. Now fetching all details");
		dto.setHotel(hotel);
		
		List<RatingTable> ratings = ratingTableService.getAllRatingsByHotel(hotel.getId());
        dto.setRating((int)ratings.stream().mapToDouble(r -> r.getRatingValue()).average().orElse(0.00));
        
        dto.setAmenities(amenityService.getAmenitiesByHotel(hotel.getId()));
		
        dto.setHotelImages(hotelImagesService.getAllImagesByHotel(hotel));
        
        return dto;
	}
	
	// Get hotels owned by a particular owner
	@GetMapping("/api/hotel/vendor")
	public List<HotelResponseDto> getHotelsByVendor (@RequestParam String username) throws ResourceNotFoundException {
		UserInfo user = (UserInfo) userSecurityService.loadUserByUsername(username);
		
		 
		List<Hotel> hotels = hotelService.getHotelsByVendor(user.getId());
		
		List<HotelResponseDto> list = new ArrayList<>();
		hotels.stream().forEach(h -> {
			HotelResponseDto dto = new HotelResponseDto();
			dto.setHotel(h);
			
			List<Room> rooms = roomService.getRoomsByHotel(h);
			List<String> roomNames = new ArrayList<>();
	        rooms.stream().forEach(room -> roomNames.add(room.getType()));
	        dto.setRooms(roomNames);
	        
	        List<Amenity> amenities = amenityService.getAmenitiesByHotel(h.getId());
	        List<String> facilites = new ArrayList<>();
	        amenities.stream().forEach(a -> facilites.add(a.getName()));
	        dto.setFacilities(facilites);
	        
	        dto.setStartPrice(rooms.stream().mapToDouble(r -> r.getPrice()).min().orElse(1000));
	        
	        List<RatingTable> ratings = ratingTableService.getAllRatingsByHotel(h.getId());
	        dto.setRating((int)ratings.stream().mapToDouble(r -> r.getRatingValue()).average().orElse(0.00));
	        
	        List<HotelImages> images = hotelImagesService.getAllImagesByHotel(h);
	        if (images.size() > 0)
	        	dto.setImageUrl(images.get(0).getUrl());
	        
	        list.add(dto);
		});
		
		return list;
	}
	 
	// Find hotel by id
	@GetMapping("/api/hotel/{hid}")
	public Hotel getHotelById (@PathVariable int hid) throws ResourceNotFoundException {
		logger.info("Validating Hotel Id and fetching details of hotel");
		return hotelService.validate(hid);
	}
	
	// Update hotel details
	@PutMapping("/api/hotel/update")
	public Hotel updateHotel (@RequestBody Hotel hotel) throws ResourceNotFoundException {
		Hotel existingHotel = hotelService.validate(hotel.getId());
		logger.info("Hotel Id Validated. Updating Hotel Details....");
		existingHotel.setAddress(hotel.getAddress());
		existingHotel.setDescription(hotel.getDescription());
		existingHotel.setStars(hotel.getStars());
		existingHotel.setName(hotel.getName());
		existingHotel.setRooms(hotel.getRooms());
		return hotelService.updateHotel(existingHotel);
	}
	
	// Add hotels in batch
	@PostMapping("/hotel/batch/add")
	public ResponseEntity<?> addBatchHotels (@RequestBody List<Hotel> hotels, ResponseMessageDto dto) {
		hotels.stream().forEach(h -> {
			try {
				h.setOwner(userInfoService.validate(h.getOwner().getId()));
				h.setLocation(locationService.getLocationById(h.getLocation().getId()));
			} catch (ResourceNotFoundException e) {
				dto.setMessage(e.getMessage());
//				return ResponseEntity.badRequest().body(dto);
			}
		});
		return ResponseEntity.ok(hotelService.addBatchHotels(hotels));
	}
	
}
