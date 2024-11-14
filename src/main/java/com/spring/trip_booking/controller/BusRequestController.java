package com.spring.trip_booking.controller;

import com.spring.trip_booking.model.BusRequest;
import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.service.BusRequestService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bus-requests")
public class BusRequestController {

    @Autowired
    private BusRequestService busRequestService;

    private static final Logger logger = LoggerFactory.getLogger(BusRequestController.class);

    // Endpoint to add a new BusRequest
    @PostMapping("/add")
    public BusRequest addBusRequest(@RequestBody BusRequest busRequest) {
        logger.info("Adding new bus request for: {}", busRequest.getVendor());
        return busRequestService.saveBusRequest(busRequest);
    }

    // Endpoint to retrieve all BusRequests with pagination
    @GetMapping("/all")
    public ResponseEntity<Page<BusRequest>> getAllBusRequests(Pageable pageable) {
        logger.info("Fetching all bus requests with pagination: page {}, size {}", pageable.getPageNumber(), pageable.getPageSize());
        Page<BusRequest> busRequests = busRequestService.getAllBusRequests(pageable);
        return ResponseEntity.ok(busRequests);
    }

    // Endpoint to get a BusRequest by ID
    @GetMapping("/{id}")
    public ResponseEntity<BusRequest> getBusRequestById(@PathVariable int id) throws ResourceNotFoundException{
        logger.info("Fetching bus request with ID: {}", id);
        BusRequest busRequest = busRequestService.getBusRequestById(id);
        logger.info("Bus request found with ID: {}", id);
        return ResponseEntity.ok(busRequest);
    }

    // Endpoint to delete a BusRequest by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessageDto> deleteBusRequestById(@PathVariable int id) throws ResourceNotFoundException{
        logger.info("Attempting to delete bus request with ID: {}", id);
        busRequestService.getBusRequestById(id);
        busRequestService.deleteBusRequestById(id);
        ResponseMessageDto dto = new ResponseMessageDto();
        dto.setMsg("Bus request deleted successfully.");
        logger.info("Bus request deleted with ID: {}", id);
        return ResponseEntity.ok(dto);
    }

    // Endpoint to get BusRequests by Vendor ID
    @GetMapping("/vendor/{vendorId}")
    public List<BusRequest> getBusRequestsByVendorId(@PathVariable int vendorId) {
        logger.info("Fetching bus requests for vendor ID: {}", vendorId);
        return busRequestService.getBusRequestsByVendorId(vendorId);
    }
}
