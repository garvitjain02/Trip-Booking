package com.spring.trip_booking.service;

import com.spring.trip_booking.model.Flight;
import com.spring.trip_booking.model.FlightRequest;
import com.spring.trip_booking.model.LogTable;
import com.spring.trip_booking.enums.ActivityType;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.repository.FlightRepository;
import com.spring.trip_booking.repository.FlightRequestRepository;
import com.spring.trip_booking.repository.LogTableRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightRequestService {

	@Autowired
    private FlightRequestRepository flightRequestRepository;
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	LogTableRepository logTableRepository;

    // Save or update a FlightRequest
    public FlightRequest saveFlightRequest(FlightRequest flightRequest) {
        // Set the requestDate to the current time if it's not provided
        if (flightRequest.getRequestDate() == null) {
            flightRequest.setRequestDate(LocalDateTime.now());
        }
        
        flightRequest=flightRequestRepository.save(flightRequest);
        
		LogTable logTable=new LogTable();
		logTable.setTimestamp(LocalDateTime.now());
		logTable.setActivityType(ActivityType.FLIGHT_REQUEST);
		logTable.setUser(flightRequest.getVendor());
		logTable.setActivityDesc("Flight request sent by Vendor");
		logTableRepository.save(logTable);
		
        return flightRequest;
    }

	public List<FlightRequest> getAllFlightRequest() {
		// TODO Auto-generated method stub
		return flightRequestRepository.findAll();
	}
	
	public FlightRequest getById(int id) {
		return flightRequestRepository.getByIdn(id);
	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub
				
		LogTable logTable=new LogTable();
		logTable.setTimestamp(LocalDateTime.now());
		logTable.setActivityType(ActivityType.DELETE_FLIGHT_REQUEST);
		logTable.setActivityDesc("Flight request deleted");
		logTableRepository.save(logTable);
		
		flightRequestRepository.deleteById(id);
	}

	public Flight approveHotelRequest(Flight flight) {
				
		LogTable logTable=new LogTable();
		logTable.setTimestamp(LocalDateTime.now());
		logTable.setActivityType(ActivityType.APPROVE_FLIGHT_REQUEST);
		logTable.setUser(flight.getVendor());
		logTable.setActivityDesc("Flight request approved");
		logTableRepository.save(logTable);
		
		// TODO Auto-generated method stub
		return flightRepository.save(flight);
	}

    
}
