package com.spring.trip_booking.controller;

import com.spring.trip_booking.model.HotelRequest;
import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.service.HotelRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/hotel-requests")
public class HotelRequestController {

    private static final Logger logger = LoggerFactory.getLogger(HotelRequestController.class);

    @Autowired
    private HotelRequestService hotelRequestService;

    // Endpoint to add a new HotelRequest
    @PostMapping("/add")
    public HotelRequest addHotelRequest(@RequestBody HotelRequest hotelRequest) {
        logger.info("Adding new hotel request: {}", hotelRequest);
        return hotelRequestService.saveHotelRequest(hotelRequest);
    }

    // Endpoint to retrieve all HotelRequests with pagination
    @GetMapping("/all")
    public ResponseEntity<Page<HotelRequest>> getAllHotelRequests(Pageable pageable) {
        logger.info("Fetching all hotel requests with pagination: {}", pageable);
        Page<HotelRequest> hotelRequests = hotelRequestService.getAllHotelRequests(pageable);
        return ResponseEntity.ok(hotelRequests);
    }

    // Endpoint to get a HotelRequest by ID
    @GetMapping("/{id}")
    public ResponseEntity<HotelRequest> getHotelRequestById(@PathVariable int id) throws ResourceNotFoundException{
        logger.info("Fetching hotel request with ID: {}", id);
        HotelRequest hotelRequest = hotelRequestService.getHotelRequestById(id);
        return ResponseEntity.ok(hotelRequest);
    }

    // Endpoint to delete a HotelRequest by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessageDto> deleteHotelRequestById(@PathVariable int id) throws ResourceNotFoundException{
        logger.info("Deleting hotel request with ID: {}", id);
        hotelRequestService.getHotelRequestById(id);
        hotelRequestService.deleteHotelRequestById(id);
        ResponseMessageDto dto = new ResponseMessageDto();
        dto.setMsg("Hotel request deleted successfully.");
        return ResponseEntity.ok(dto);
    }

    // Endpoint to get HotelRequests by Vendor ID
    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<HotelRequest>> getHotelRequestsByVendorId(@PathVariable int vendorId) {
        logger.info("Fetching hotel requests for vendor ID: {}", vendorId);
        List<HotelRequest> hotelRequests = hotelRequestService.getHotelRequestsByVendorId(vendorId);
        return ResponseEntity.ok(hotelRequests);
    }
}
