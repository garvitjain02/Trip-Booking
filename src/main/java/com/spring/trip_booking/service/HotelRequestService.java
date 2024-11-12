package com.spring.trip_booking.service;

import com.spring.trip_booking.model.HotelRequest;
import com.spring.trip_booking.repository.HotelRequestRepository;
import com.spring.trip_booking.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelRequestService {

	@Autowired
    private HotelRequestRepository hotelRequestRepository;

    // Method to add or update a HotelRequest
    public HotelRequest saveHotelRequest(HotelRequest hotelRequest) {
        return hotelRequestRepository.save(hotelRequest);
    }

    // Method to retrieve all HotelRequests
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
        hotelRequestRepository.deleteById(id);
    }

    // Method to get all HotelRequests by Vendor ID
    public List<HotelRequest> getHotelRequestsByVendorId(int vendorId) {
        return hotelRequestRepository.findByVendorId(vendorId);
    }
}
