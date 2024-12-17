package com.spring.trip_booking.controller;

import com.spring.trip_booking.model.FlightBooking;
import com.spring.trip_booking.service.FlightBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/flight-bookings")
public class FlightBookingController {

    @Autowired
    private FlightBookingService flightBookingService;

    // POST API to create a new booking
    @PostMapping("/add")
    public ResponseEntity<FlightBooking> addFlightBooking(@RequestBody FlightBooking booking) {
        FlightBooking savedBooking = flightBookingService.saveFlightBooking(booking);
        return ResponseEntity.ok(savedBooking);
    }

    // GET API to retrieve all bookings
    @GetMapping("/all")
    public ResponseEntity<List<FlightBooking>> getAllFlightBookings() {
        List<FlightBooking> bookings = flightBookingService.getAllFlightBookings();
        return ResponseEntity.ok(bookings);
    }

    // GET API to retrieve a booking by ID
    @GetMapping("/{bookingId}")
    public ResponseEntity<FlightBooking> getFlightBookingById(@PathVariable Long bookingId) {
        return flightBookingService.getFlightBookingById(bookingId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT API to update a booking
    @PutMapping("/update/{bookingId}")
    public ResponseEntity<FlightBooking> updateFlightBooking(@PathVariable Long bookingId, @RequestBody FlightBooking bookingDetails) {
        FlightBooking updatedBooking = flightBookingService.updateFlightBooking(bookingId, bookingDetails);
        if (updatedBooking != null) {
            return ResponseEntity.ok(updatedBooking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE API to remove a booking
    @DeleteMapping("/delete/{bookingId}")
    public ResponseEntity<Void> deleteFlightBooking(@PathVariable Long bookingId) {
        if (flightBookingService.deleteFlightBooking(bookingId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
