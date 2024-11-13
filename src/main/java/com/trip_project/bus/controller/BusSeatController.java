package com.trip_project.bus.controller;

import com.trip_project.bus.exception.ResourceNotFoundException;
import com.trip_project.bus.exception.InvalidInputException;
import com.trip_project.bus.model.BusSeat;
import com.trip_project.bus.service.BusSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class BusSeatController {

	@Autowired
    private final BusSeatService busSeatService;

    @Autowired
    public BusSeatController(BusSeatService busSeatService) {
        this.busSeatService = busSeatService;
    }

    // Endpoint to get all bus seats
    @GetMapping
    public ResponseEntity<List<BusSeat>> getAllBusSeats() {
        try {
            List<BusSeat> seats = busSeatService.getAllBusSeats();
            return ResponseEntity.ok(seats);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint to get bus seat by ID
    @GetMapping("/{id}")
    public ResponseEntity<BusSeat> getBusSeatById(@PathVariable int id) {
        try {
            BusSeat seat = busSeatService.getBusSeatById(id);
            return ResponseEntity.ok(seat);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint to get bus seats by bus ID
    @GetMapping("/bus/{busId}")
    public ResponseEntity<List<BusSeat>> getBusSeatsByBusId(@PathVariable int busId) {
        try {
            List<BusSeat> seats = busSeatService.getBusSeatsByBusId(busId);
            return ResponseEntity.ok(seats);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint to get bus seat by seat number and bus ID
    @GetMapping("/bus/{busId}/seat/{seatNumber}")
    public ResponseEntity<BusSeat> getBusSeatBySeatNumberAndBusId(@PathVariable int busId, @PathVariable int seatNumber) {
        try {
            BusSeat seat = busSeatService.getBusSeatBySeatNumberAndBusId(seatNumber, busId);
            return ResponseEntity.ok(seat);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint to create or update a bus seat
    @PostMapping
    public ResponseEntity<BusSeat> saveBusSeat(@RequestBody BusSeat busSeat) {
        try {
            BusSeat savedSeat = busSeatService.saveBusSeat(busSeat);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSeat);
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Endpoint to delete a bus seat by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusSeat(@PathVariable int id) {
        try {
            busSeatService.deleteBusSeat(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
