
package com.spring.trip_booking.controller;

import com.spring.trip_booking.model.Airport;
import com.spring.trip_booking.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    @Autowired
    private AirportService airportService;

    // POST API to add a new airport
    @PostMapping("/airport/add")
    public ResponseEntity<Airport> addAirport(@RequestBody Airport airport) {
        Airport savedAirport = airportService.saveAirport(airport);
        return ResponseEntity.ok(savedAirport);
    }
}
