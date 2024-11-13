package com.spring.trip_booking.service;

import com.spring.trip_booking.model.FlightBooking;
import com.spring.trip_booking.repository.FlightBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightBookingService {

    @Autowired
    private FlightBookingRepository flightBookingRepository;

    // Save a new booking
    public FlightBooking saveFlightBooking(FlightBooking booking) {
        return flightBookingRepository.save(booking);
    }

    // Get all bookings
    public List<FlightBooking> getAllFlightBookings() {
        return flightBookingRepository.findAll();
    }

    // Get a booking by ID
    public Optional<FlightBooking> getFlightBookingById(Long bookingId) {
        return flightBookingRepository.findById(bookingId);
    }

    // Update an existing booking
    public FlightBooking updateFlightBooking(Long bookingId, FlightBooking bookingDetails) {
        return flightBookingRepository.findById(bookingId).map(existingBooking -> {
            existingBooking.setFlight(bookingDetails.getFlight());
            existingBooking.setBookingDate(bookingDetails.getBookingDate());
            existingBooking.setPaymentStatus(bookingDetails.isPaymentStatus());
            existingBooking.setTotalAmount(bookingDetails.getTotalAmount());
            existingBooking.setPassengers(bookingDetails.getPassengers());
            return flightBookingRepository.save(existingBooking);
        }).orElse(null);
    }

    // Delete a booking by ID
    public boolean deleteFlightBooking(Long bookingId) {
        if (flightBookingRepository.existsById(bookingId)) {
            flightBookingRepository.deleteById(bookingId);
            return true;
        }
        return false;
    }
}
