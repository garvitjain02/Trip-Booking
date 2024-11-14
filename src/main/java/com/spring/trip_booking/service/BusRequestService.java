package com.spring.trip_booking.service;

import com.spring.trip_booking.model.BusRequest;
import com.spring.trip_booking.repository.BusRequestRepository;
import com.spring.trip_booking.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusRequestService {

	@Autowired
    private BusRequestRepository busRequestRepository;

    // Method to add or update a BusRequest
    public BusRequest saveBusRequest(BusRequest busRequest) {
        return busRequestRepository.save(busRequest);
    }

    // Method to get all bus requests with pagination
    public Page<BusRequest> getAllBusRequests(Pageable pageable) {
        return busRequestRepository.findAll(pageable);
    }

    // Method to get a BusRequest by ID
    public BusRequest getBusRequestById(int id) throws ResourceNotFoundException {
        Optional<BusRequest> busRequest = busRequestRepository.findById(id);
        return busRequest.orElseThrow(() -> new ResourceNotFoundException("BusRequest with ID " + id + " not found"));
    }

    // Method to delete a BusRequest by ID
    public void deleteBusRequestById(int id) {
        busRequestRepository.deleteById(id);
    }

    // Method to get all BusRequests by Vendor ID
    public List<BusRequest> getBusRequestsByVendorId(int vendorId) {
        return busRequestRepository.findByVendorId(vendorId);
    }
}
