package com.spring.trip_booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.repository.HotelRepository;
import com.spring.trip_booking.service.HotelService;
@SpringBootTest
public class HotelServiceTest {

	@InjectMocks
	private HotelService hotelService;
	
	@Mock
	private HotelRepository hotelRepository;
	
	private Hotel hotel;
	
	private List<Hotel> list;
	
	@BeforeEach
	public void initSetup () {
		hotel = new Hotel();
		hotel.setId(1);
		
		Hotel h = new Hotel();
		hotel.setId(2);
		
		list = Arrays.asList(hotel, h);
	}
	
	@Test
	public void validateHotelTest() throws ResourceNotFoundException {
		when(hotelRepository.findById(hotel.getId())).thenReturn(Optional.of(hotel));
		
		Hotel h = hotelService.validate(hotel.getId());
		
		assertNotNull(h);
		
		verify(hotelRepository, times(1)).findById(hotel.getId());
	}
	
	@Test
	public void validateHotelInvalidTest() throws ResourceNotFoundException {
		when(hotelRepository.findById(1234)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, () -> hotelService.validate(1234));
		
		verify(hotelRepository, times(1)).findById(1234);
	}
	
	@Test
	public void getAllHotelsTest () {
		when(hotelRepository.findAll()).thenReturn(list);
		
		List<Hotel> hotels = hotelService.getAllHotels();
		assertNotNull(hotels);
		assertEquals(2, hotels.size());
		
		verify(hotelRepository, times(1)).findAll();
	}
}
