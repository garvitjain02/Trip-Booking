package com.spring.trip_booking.controller;

import com.spring.trip_booking.model.Flight;
import com.spring.trip_booking.model.FlightRequest;
import com.spring.trip_booking.model.Hotel;
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
@CrossOrigin(origins = {"http://localhost:4200"})
public class FlightRequestController {

    private static final Logger logger = LoggerFactory.getLogger(FlightRequestController.class);

    @Autowired
    private FlightRequestService flightRequestService;

    //using
    @PostMapping("/add")
    public FlightRequest addFlightRequest(@RequestBody FlightRequest flightRequest) {
        logger.info("Adding new flight request: {}", flightRequest);
        return flightRequestService.saveFlightRequest(flightRequest);
    }
    
    @GetMapping("/all")
    public List<FlightRequest> getAllFlightRequest() {
    	return flightRequestService.getAllFlightRequest();
    }
    
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable int id) {
    	flightRequestService.deleteById(id);
    }
    
    @PostMapping("/approveHotelRequest")
    public Flight approveHotelRequest(@RequestBody Flight flight) {
    	return flightRequestService.approveHotelRequest(flight);
    }

}
