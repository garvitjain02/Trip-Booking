package com.spring.trip_booking.controller;

import com.spring.trip_booking.model.FlightRequest;
import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.service.FlightRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/flight-requests")
public class FlightRequestController {

    private static final Logger logger = LoggerFactory.getLogger(FlightRequestController.class);

    @Autowired
    private FlightRequestService flightRequestService;

    // Endpoint to add a new FlightRequest
    @PostMapping("/add")
    public FlightRequest addFlightRequest(@RequestBody FlightRequest flightRequest) {
        logger.info("Adding new flight request: {}", flightRequest);
        return flightRequestService.saveFlightRequest(flightRequest);
    }

    // Endpoint to retrieve all FlightRequests with pagination
    @GetMapping("/all")
    public ResponseEntity<Page<FlightRequest>> getAllFlightRequests(Pageable pageable) {
        logger.info("Fetching all flight requests with pagination: page {}, size {}", pageable.getPageNumber(), pageable.getPageSize());
        Page<FlightRequest> flightRequests = flightRequestService.getAllFlightRequests(pageable);
        return ResponseEntity.ok(flightRequests);
    }

    // Endpoint to get a FlightRequest by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getFlightRequestById(@PathVariable int id) throws ResourceNotFoundException{
        logger.info("Fetching flight request with ID: {}", id);
        FlightRequest flightRequest = flightRequestService.getFlightRequestById(id);
        return ResponseEntity.ok(flightRequest);
    }

    // Endpoint to delete a FlightRequest by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFlightRequestById(@PathVariable int id, ResponseMessageDto dto) throws ResourceNotFoundException{
        logger.info("Deleting flight request with ID: {}", id);
        flightRequestService.getFlightRequestById(id);
        flightRequestService.deleteFlightRequestById(id);
        dto.setMsg("Flight request deleted successfully.");
        return ResponseEntity.ok(dto);
    }

    // Endpoint to get FlightRequests by Vendor ID
    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<FlightRequest>> getFlightRequestsByVendorId(@PathVariable int vendorId) {
        logger.info("Fetching flight requests for vendor ID: {}", vendorId);
        List<FlightRequest> flightRequests = flightRequestService.getFlightRequestsByVendorId(vendorId);
        return ResponseEntity.ok(flightRequests);
    }
}
