package com.spring.trip_booking.controller;

import com.spring.trip_booking.model.FlightRequest;
import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.service.FlightRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight-requests")
public class FlightRequestController {

	@Autowired
    private FlightRequestService flightRequestService;

    // Endpoint to add a new FlightRequest
    @PostMapping("/add")
    public FlightRequest addFlightRequest(@RequestBody FlightRequest flightRequest) {
        return flightRequestService.saveFlightRequest(flightRequest);
    }

    // Endpoint to retrieve all FlightRequests
    @GetMapping("/all")
    public List<FlightRequest> getAllFlightRequests() {
        return flightRequestService.getAllFlightRequests();
    }

    // Endpoint to get a FlightRequest by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getFlightRequestById(@PathVariable int id, ResponseMessageDto dto) {
        try {
            FlightRequest flightRequest = flightRequestService.getFlightRequestById(id);
            return ResponseEntity.ok(flightRequest);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }

    // Endpoint to delete a FlightRequest by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFlightRequestById(@PathVariable int id, ResponseMessageDto dto) {
        try {
            flightRequestService.getFlightRequestById(id); // Validate if exists
            flightRequestService.deleteFlightRequestById(id);
            dto.setMsg("Flight request deleted successfully.");
            return ResponseEntity.ok(dto);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }

    // Endpoint to get FlightRequests by Vendor ID
    @GetMapping("/vendor/{vendorId}")
    public List<FlightRequest> getFlightRequestsByVendorId(@PathVariable int vendorId) {
        return flightRequestService.getFlightRequestsByVendorId(vendorId);
    }
}
