package com.spring.trip_booking.service;

import com.spring.trip_booking.model.FlightRequest;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.repository.FlightRequestRepository;

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

    // Save or update a FlightRequest
    public FlightRequest saveFlightRequest(FlightRequest flightRequest) {
        // Set the requestDate to the current time if it's not provided
        if (flightRequest.getRequestDate() == null) {
            flightRequest.setRequestDate(LocalDateTime.now());
        }
        return flightRequestRepository.save(flightRequest);
    }

    // Method to retrieve all FlightRequests with pagination
    public Page<FlightRequest> getAllFlightRequests(Pageable pageable) {
        return flightRequestRepository.findAll(pageable);
    }

    // Retrieve a FlightRequest by ID
    public FlightRequest getFlightRequestById(int id) throws ResourceNotFoundException {
        return flightRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FlightRequest with ID " + id + " not found"));
    }

    // Delete a FlightRequest by ID
    public void deleteFlightRequestById(int id) {
        flightRequestRepository.deleteById(id);
    }

    // Retrieve FlightRequests by Vendor ID
    public List<FlightRequest> getFlightRequestsByVendorId(int vendorId) {
        return flightRequestRepository.findByVendorId(vendorId);
    }
    
}
