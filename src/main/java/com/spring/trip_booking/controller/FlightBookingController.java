
package com.spring.trip_booking.controller;

import com.spring.trip_booking.model.FlightBooking;
import com.spring.trip_booking.service.FlightBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class FlightBookingController {

    @Autowired
    private FlightBookingService flightBookingService;

    // POST API to book a flight
    @PostMapping("/FlightBooking/add")
    public ResponseEntity<FlightBooking> addBooking(@RequestBody FlightBooking booking) {
        FlightBooking savedBooking = flightBookingService.saveBooking(booking);
        return ResponseEntity.ok(savedBooking);
    }
}
