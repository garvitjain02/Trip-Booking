package com.spring.trip_booking.controller;

import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.model.Airport;
import com.spring.trip_booking.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    @Autowired
    private AirportService airportService;

    // Post api to add a new airport
    @PostMapping("/add")
    public ResponseEntity<Airport> addAirport(@RequestBody Airport airport) {
        Airport savedAirport = airportService.saveAirport(airport);
        return ResponseEntity.ok(savedAirport);
    }

    // get api to get all airports
    @GetMapping("/all")
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> airports = airportService.getAllAirports();
        return ResponseEntity.ok(airports);
    }

    // get api to get an airport by code
    @GetMapping("/{airportCode}")
    public ResponseEntity<Airport> getAirportByCode(@PathVariable String airportCode,ResponseMessageDto dto) {
        Optional<Airport> airport = airportService.getAirportByCode(airportCode);
        
        return airport.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // put api to update an airport
    @PutMapping("/update/{airportCode}")
    public ResponseEntity<Airport> updateAirport(@PathVariable String airportCode, @RequestBody Airport airportDetails) {
        Airport updatedAirport = airportService.updateAirport(airportCode, airportDetails);
        if (updatedAirport != null) {
            return ResponseEntity.ok(updatedAirport);
        }
        return ResponseEntity.notFound().build();
    }

    // delete api to delete an airport by code
    @DeleteMapping("/delete/{airportCode}")
    public ResponseEntity<Void> deleteAirport(@PathVariable String airportCode) {
        boolean isDeleted = airportService.deleteAirport(airportCode);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
