
package com.spring.trip_booking.controller;

import com.spring.trip_booking.model.Flight;
import com.spring.trip_booking.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    // POST API to add a new flight
    @PostMapping("/flight/add")
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
        Flight savedFlight = flightService.saveFlight(flight);
        return ResponseEntity.ok(savedFlight);
    }
}
