package com.trip_project.bus.controller;

import com.trip_project.bus.exception.ResourceNotFoundException;
import com.trip_project.bus.exception.InvalidInputException;
import com.trip_project.bus.model.BusBooking;
import com.trip_project.bus.service.BusBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BusBookingController {

    private final BusBookingService busBookingService;

    @Autowired
    public BusBookingController(BusBookingService busBookingService) {
        this.busBookingService = busBookingService;
    }

    // Endpoint to get all bookings
    @GetMapping
    public ResponseEntity<List<BusBooking>> getAllBusBookings() {
        try {
            List<BusBooking> bookings = busBookingService.getAllBusBookings();
            return ResponseEntity.ok(bookings);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint to get booking by id
    @GetMapping("/{id}")
    public ResponseEntity<BusBooking> getBusBookingById(@PathVariable int id) {
        try {
            BusBooking booking = busBookingService.getBusBookingById(id);
            return ResponseEntity.ok(booking);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint to get bookings by user id
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BusBooking>> getBookingsByUserId(@PathVariable int userId) {
        try {
            List<BusBooking> bookings = busBookingService.getBookingsByUserId(userId);
            return ResponseEntity.ok(bookings);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint to get bookings by bus id
    @GetMapping("/bus/{busId}")
    public ResponseEntity<List<BusBooking>> getBookingsByBusId(@PathVariable int busId) {
        try {
            List<BusBooking> bookings = busBookingService.getBookingsByBusId(busId);
            return ResponseEntity.ok(bookings);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint to create or update a bus booking
    @PostMapping
    public ResponseEntity<BusBooking> saveBusBooking(@RequestBody BusBooking busBooking) {
        try {
            BusBooking savedBooking = busBookingService.saveBusBooking(busBooking);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Endpoint to delete a booking by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusBooking(@PathVariable int id) {
        try {
            busBookingService.deleteBusBooking(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
