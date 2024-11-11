package com.spring.trip_booking.controller;

import com.spring.trip_booking.model.Passengers;
import com.spring.trip_booking.service.PassengersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengersController {

    @Autowired
    private PassengersService passengersService;

    // POST API to add a new passenger
    @PostMapping("/add")
    public ResponseEntity<Passengers> addPassenger(@RequestBody Passengers passenger) {
        Passengers savedPassenger = passengersService.savePassenger(passenger);
        return ResponseEntity.ok(savedPassenger);
    }

    /*// GET API to get passenger details by ID
    @GetMapping("/{passengerId}")
    public ResponseEntity<Passengers> getPassengerById(@PathVariable int passengerId) {
        return ResponseEntity.of(passengersService.getPassengerById(passengerId));
    }

    // GET API to get all passengers for a specific flight
    @GetMapping("/flight/{flightId}")
    public ResponseEntity<List<Passengers>> getPassengersByFlightId(@PathVariable int flightId) {
        return ResponseEntity.ok(passengersService.getPassengersByFlightId(flightId));
    }

    // PUT API to update passenger details
    @PutMapping("/update/{passengerId}")
    public ResponseEntity<Passengers> updatePassenger(@PathVariable int passengerId, @RequestBody Passengers passenger) {
        Passengers updatedPassenger = passengersService.updatePassenger(passengerId, passenger);
        return ResponseEntity.ok(updatedPassenger);
    }

    // DELETE API to delete a passenger by ID
    @DeleteMapping("/delete/{passengerId}")
    public ResponseEntity<String> deletePassenger(@PathVariable int passengerId) {
        passengersService.deletePassenger(passengerId);
        return ResponseEntity.ok("Passenger deleted successfully");
    }
        */
}
