package com.spring.trip_booking.service;

import com.spring.trip_booking.model.FlightRequest;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.repository.FlightRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightRequestService {

	@Autowired
    private FlightRequestRepository flightRequestRepository;

    // Save or update a FlightRequest
    public FlightRequest saveFlightRequest(FlightRequest flightRequest) {
        return flightRequestRepository.save(flightRequest);
    }

    // Retrieve all FlightRequests
    public List<FlightRequest> getAllFlightRequests() {
        return flightRequestRepository.findAll();
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
