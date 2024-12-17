package com.spring.trip_booking.service;

import com.spring.trip_booking.model.FlightBooking;
import com.spring.trip_booking.model.Passengers;
import com.spring.trip_booking.repository.FlightBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.function.Function;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightBookingService {

    @Autowired
    private FlightBookingRepository flightBookingRepository;

    @PersistenceContext
    private EntityManager entityManager;

    // Save a new booking
    @Transactional
    public FlightBooking saveFlightBooking(FlightBooking flightBooking) {
        
        
    
    // Merge passengers to attach them to the persistence context
        List<Passengers> mergedPassengers = flightBooking.getPassengers().stream()
            .map(passenger -> passenger.getPassengerId() != null 
                  ? entityManager.merge(passenger) 
                  : passenger)
            .collect(Collectors.toList());
        flightBooking.setPassengers(mergedPassengers);

        // Ensure passengers reference the current flight booking
    List<Passengers> passengers = flightBooking.getPassengers();
    if (passengers != null) {
        passengers.forEach(passenger -> passenger.setBooking(flightBooking));
    }
    
        
        // Save the booking
        return flightBookingRepository.save(flightBooking);
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
