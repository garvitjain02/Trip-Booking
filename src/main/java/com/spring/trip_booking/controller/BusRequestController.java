package com.spring.trip_booking.controller;

import com.spring.trip_booking.model.BusRequest;
import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.service.BusRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus-requests")
public class BusRequestController {

	@Autowired
    private BusRequestService busRequestService;

    // Endpoint to add a new BusRequest
    @PostMapping("/add")
    public BusRequest addBusRequest(@RequestBody BusRequest busRequest) {
        return busRequestService.saveBusRequest(busRequest);
    }

    // Endpoint to retrieve all BusRequests
    @GetMapping("/all")
    public List<BusRequest> getAllBusRequests() {
        return busRequestService.getAllBusRequests();
    }

    // Endpoint to get a BusRequest by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getBusRequestById(@PathVariable int id, ResponseMessageDto dto) {
        try {
            BusRequest busRequest = busRequestService.getBusRequestById(id);
            return ResponseEntity.ok(busRequest);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }

    // Endpoint to delete a BusRequest by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBusRequestById(@PathVariable int id, ResponseMessageDto dto) {
        try {
            busRequestService.getBusRequestById(id); // Validate if exists
            busRequestService.deleteBusRequestById(id);
            dto.setMsg("Bus request deleted successfully.");
            return ResponseEntity.ok(dto);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }

    // Endpoint to get BusRequests by Vendor ID
    @GetMapping("/vendor/{vendorId}")
    public List<BusRequest> getBusRequestsByVendorId(@PathVariable int vendorId) {
        return busRequestService.getBusRequestsByVendorId(vendorId);
    }
}
