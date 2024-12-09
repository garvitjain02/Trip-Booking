package com.spring.trip_booking;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.trip_booking.enums.Role;
import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.HotelRequest;
import com.spring.trip_booking.model.UserInfo;
import com.spring.trip_booking.repository.HotelRequestRepository;
import com.spring.trip_booking.repository.LogTableRepository;
import com.spring.trip_booking.service.HotelRequestService;

@SpringBootTest
public class HotelRequestTests {
	/*
	@InjectMocks
	HotelRequestService hotelRequestService;
	
	@Mock
	HotelRequestRepository hotelRequestRepository;
	
	@Mock
	private LogTableRepository logTableRepository; // Add this line

    private HotelRequest hotelRequest;
    private Hotel hotel;
    private List<HotelRequest> hotelRequestList;

    @BeforeEach
    public void setup() {
    	UserInfo userInfo = new UserInfo(1, "FALSE", "john_doe", "securePassword123", "John", "Doe", LocalDate.now(), "9876543210", "john.doe@example.com", Role.EXECUTIVE);
        hotelRequest = new HotelRequest(1,userInfo,LocalDateTime.now(),"Sai Palace",25,"Mumbai");
        hotel = new Hotel();
        hotelRequestList = Arrays.asList(
            hotelRequest, 
            new HotelRequest()
        );
    }
    
    @Test
    public void testAddHotelRequest() {
        when(hotelRequestRepository.save(hotelRequest)).thenReturn(hotelRequest);
        HotelRequest newHotelRequest=hotelRequestService.saveHotelRequest(hotelRequest);
        assertNotNull(newHotelRequest);
		verify(hotelRequestRepository, times(1)).save(hotelRequest);
    }*/

}
