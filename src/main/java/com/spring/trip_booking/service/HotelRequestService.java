package com.spring.trip_booking.service;

import com.spring.trip_booking.model.FlightRequest;
import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.HotelRequest;
import com.spring.trip_booking.model.LogTable;
import com.spring.trip_booking.repository.HotelRequestRepository;
import com.spring.trip_booking.repository.HotelRespository;
import com.spring.trip_booking.repository.LocationRepository;
import com.spring.trip_booking.repository.LogTableRepository;
import com.spring.trip_booking.enums.ActivityType;
import com.spring.trip_booking.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HotelRequestService {

	@Autowired
    private HotelRequestRepository hotelRequestRepository;
	
	@Autowired
	private HotelRespository hotelRepository;
	
	@Autowired
	LogTableRepository logTableRepository;
	
	@Autowired 
	LocationRepository locationRepository;
	
    // Method to add or update a HotelRequest
    public HotelRequest saveHotelRequest(HotelRequest hotelRequest) {
    	
		LogTable logTable=new LogTable();
		logTable.setTimestamp(LocalDateTime.now());
		logTable.setActivityType(ActivityType.HOTEL_REQUEST);
		logTable.setUser(hotelRequest.getVendor());
		logTable.setActivityDesc("Hotel request sent by Vendor");
		logTableRepository.save(logTable);
		
        return hotelRequestRepository.save(hotelRequest);
    }

    public List<HotelRequest> getAllHotelRequests() {
        return hotelRequestRepository.findAll();
    }

    // Method to get a HotelRequest by ID
    public HotelRequest getHotelRequestById(int id) throws ResourceNotFoundException {
        Optional<HotelRequest> hotelRequest = hotelRequestRepository.findById(id);
        return hotelRequest.orElseThrow(() -> new ResourceNotFoundException("HotelRequest with ID " + id + " not found"));
    }

    // Method to delete a HotelRequest by ID
    public void deleteHotelRequestById(int id) {
    	
		LogTable logTable=new LogTable();
		logTable.setTimestamp(LocalDateTime.now());
		logTable.setActivityType(ActivityType.DELETE_HOTEL_REQUEST);
		logTable.setActivityDesc("Hotel request deleted");
		logTableRepository.save(logTable);
        hotelRequestRepository.deleteById(id);
    }

    // Method to get all HotelRequests by Vendor ID
    public List<HotelRequest> getHotelRequestsByVendorId(int vendorId) {
        return hotelRequestRepository.findByVendorId(vendorId);
    }

	public Hotel vendorsHotelsAdd(Hotel hotel) {
		
		LogTable logTable=new LogTable();
		logTable.setTimestamp(LocalDateTime.now());
		logTable.setActivityType(ActivityType.APPROVE_HOTEL_REQUEST);
		logTable.setUser(hotel.getOwner());
		logTable.setActivityDesc("Hotel request approved");
		logTableRepository.save(logTable);
		// TODO Auto-generated method stub
		locationRepository.save(hotel.getLocation());
		return hotelRepository.save(hotel);
	}

	public List<Hotel> getHotelsByVendor(String username) {
		// TODO Auto-generated method stub
		return hotelRepository.getHotelsByVendor(username);
	}
}
