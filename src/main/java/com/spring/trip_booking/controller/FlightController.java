package com.spring.trip_booking.controller;

import com.spring.trip_booking.model.Flight;
import com.spring.trip_booking.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    // POST API to add a new flight
    @PostMapping("/add")
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
        Flight savedFlight = flightService.saveFlight(flight);
        return ResponseEntity.ok(savedFlight);
    }

    // GET API to get all flights
    @GetMapping("/all")
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

    // GET API to get a flight by ID
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable int id) {
        Optional<Flight> flight = flightService.getFlightById(id);
        return flight.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // PUT API to update a flight
    @PutMapping("/update/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable int id, @RequestBody Flight flightDetails) {
        Flight updatedFlight = flightService.updateFlight(id, flightDetails);
        if (updatedFlight != null) {
            return ResponseEntity.ok(updatedFlight);
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE API to delete a flight by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable int id) {
        boolean isDeleted = flightService.deleteFlight(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Flight>> getFlightsByOriginAndDestination(
            @RequestParam("origin") String origin,
            @RequestParam("destination") String destination) {
        List<Flight> flights = flightService.getFlightsByOriginAndDestination(origin, destination);
        return ResponseEntity.ok(flights);
    }
}
