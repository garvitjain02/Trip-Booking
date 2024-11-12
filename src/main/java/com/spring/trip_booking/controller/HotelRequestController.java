package com.spring.trip_booking.controller;

import com.spring.trip_booking.model.HotelRequest;
import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.service.HotelRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel-requests")
public class HotelRequestController {

	@Autowired
    private HotelRequestService hotelRequestService;

    // Endpoint to add a new HotelRequest
    @PostMapping("/add")
    public HotelRequest addHotelRequest(@RequestBody HotelRequest hotelRequest) {
        return hotelRequestService.saveHotelRequest(hotelRequest);
    }

    // Endpoint to retrieve all HotelRequests
    @GetMapping("/all")
    public List<HotelRequest> getAllHotelRequests() {
        return hotelRequestService.getAllHotelRequests();
    }

    // Endpoint to get a HotelRequest by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getHotelRequestById(@PathVariable int id, ResponseMessageDto dto) {
        try {
            HotelRequest hotelRequest = hotelRequestService.getHotelRequestById(id);
            return ResponseEntity.ok(hotelRequest);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }

    // Endpoint to delete a HotelRequest by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHotelRequestById(@PathVariable int id, ResponseMessageDto dto) {
        try {
            hotelRequestService.getHotelRequestById(id); // Validate if exists
            hotelRequestService.deleteHotelRequestById(id);
            dto.setMsg("Hotel request deleted successfully.");
            return ResponseEntity.ok(dto);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }

    // Endpoint to get HotelRequests by Vendor ID
    @GetMapping("/vendor/{vendorId}")
    public List<HotelRequest> getHotelRequestsByVendorId(@PathVariable int vendorId) {
        return hotelRequestService.getHotelRequestsByVendorId(vendorId);
    }
}
