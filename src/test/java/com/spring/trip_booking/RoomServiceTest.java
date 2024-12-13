package com.spring.trip_booking;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.Room;
import com.spring.trip_booking.repository.RoomRepository;
import com.spring.trip_booking.service.RoomService;

@SpringBootTest
public class RoomServiceTest {

	@InjectMocks
	private RoomService roomService;
	
	@Mock
	private RoomRepository roomRepository;
	
	private Room room;
	
	@BeforeEach
	public void initSetup() {
		room = new Room();
		room.setId(1);
	}
	
	@Test
	public void validateRoomTest() throws ResourceNotFoundException {
		when(roomRepository.findById(room.getId())).thenReturn(Optional.of(room));
		
		Room h = roomService.validate(room.getId());
		
		assertNotNull(h);
		
		verify(roomRepository, times(1)).findById(room.getId());
	}

	@Test
	public void validateRoomInvalidTest() throws ResourceNotFoundException {
		when(roomRepository.findById(1234)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, () -> roomService.validate(1234));
		
		verify(roomRepository, times(1)).findById(1234);
	}
	
}
