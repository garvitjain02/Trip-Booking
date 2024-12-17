package com.spring.trip_booking.controller;

import com.spring.trip_booking.model.Passengers;
import com.spring.trip_booking.service.PassengersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200"})
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

    // GET API to get all passengers
    @GetMapping("/all")
    public ResponseEntity<List<Passengers>> getAllPassengers() {
        List<Passengers> passengersList = passengersService.getAllPassengers();
        return ResponseEntity.ok(passengersList);
    }

    // GET API to get a passenger by ID
    @GetMapping("/{passengerId}")
    public ResponseEntity<Passengers> getPassengerById(@PathVariable Long passengerId) {
        Optional<Passengers> passenger = passengersService.getPassengerById(passengerId);
        return passenger.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // PUT API to update passenger details
    @PutMapping("/update/{passengerId}")
    public ResponseEntity<Passengers> updatePassenger(@PathVariable Long passengerId, @RequestBody Passengers passengerDetails) {
        Passengers updatedPassenger = passengersService.updatePassenger(passengerId, passengerDetails);
        if (updatedPassenger != null) {
            return ResponseEntity.ok(updatedPassenger);
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE API to delete a passenger by ID
    @DeleteMapping("/delete/{passengerId}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long passengerId) {
        boolean isDeleted = passengersService.deletePassenger(passengerId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
