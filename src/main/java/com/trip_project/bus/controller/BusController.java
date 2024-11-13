package com.trip_project.bus.controller;

import com.trip_project.bus.exception.ResourceNotFoundException;
import com.trip_project.bus.exception.InvalidInputException;
import com.trip_project.bus.exception.DuplicateResourceException;
import com.trip_project.bus.model.Bus;
import com.trip_project.bus.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    private final BusService busService;

    @Autowired
    public BusController(BusService busService) {
        this.busService = busService;
    }

    // Endpoint to get all buses
    @GetMapping
    public ResponseEntity<List<Bus>> getAllBuses() {
        try {
            List<Bus> buses = busService.getAllBuses();
            return ResponseEntity.ok(buses);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint to get bus by id
    @GetMapping("/{id}")
    public ResponseEntity<Bus> getBusById(@PathVariable int id) {
        try {
            Bus bus = busService.getBusById(id);
            return ResponseEntity.ok(bus);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint to get bus by bus number
    @GetMapping("/busNumber/{busNumber}")
    public ResponseEntity<Bus> getBusByBusNumber(@PathVariable String busNumber) {
        try {
            Bus bus = busService.getBusByBusNumber(busNumber);
            return ResponseEntity.ok(bus);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint to create or update a bus
    @PostMapping
    public ResponseEntity<Bus> saveBus(@RequestBody Bus bus) {
        try {
            Bus savedBus = busService.saveBus(bus);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBus);
        } catch (InvalidInputException | DuplicateResourceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Endpoint to delete a bus by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable int id) {
        try {
            busService.deleteBus(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
