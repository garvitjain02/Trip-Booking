package com.trip_project.bus.service;

import com.trip_project.bus.exception.ResourceNotFoundException;
import com.trip_project.bus.exception.InvalidInputException;
import com.trip_project.bus.model.BusBooking;
import com.trip_project.bus.repository.BusBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusBookingService {

    private final BusBookingRepository busBookingRepository;

    @Autowired
    public BusBookingService(BusBookingRepository busBookingRepository) {
        this.busBookingRepository = busBookingRepository;
    }

    // Create or Update a bus booking
    public BusBooking saveBusBooking(BusBooking busBooking) throws InvalidInputException {
        // Validate input
        if (busBooking.getNumberOfSeats() <= 0 || busBooking.getTotalFare() <= 0) {
            throw new InvalidInputException("Number of seats and total fare must be valid.");
        }
        return busBookingRepository.save(busBooking);
    }

    // Find all bus bookings
    public List<BusBooking> getAllBusBookings() throws ResourceNotFoundException {
        List<BusBooking> bookings = busBookingRepository.findAll();
        if (bookings.isEmpty()) {
            throw new ResourceNotFoundException("No bookings found.");
        }
        return bookings;
    }

    // Find a bus booking by ID
    public BusBooking getBusBookingById(int id) throws ResourceNotFoundException {
        return busBookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus booking not found with ID: " + id));
    }

    // Find bookings by user ID
    public List<BusBooking> getBookingsByUserId(int userId) throws ResourceNotFoundException {
        List<BusBooking> bookings = busBookingRepository.findByUserId(userId);
        if (bookings.isEmpty()) {
            throw new ResourceNotFoundException("No bookings found for user with ID: " + userId);
        }
        return bookings;
    }

    // Find bookings by bus ID
    public List<BusBooking> getBookingsByBusId(int busId) throws ResourceNotFoundException {
        List<BusBooking> bookings = busBookingRepository.findByBusId(busId);
        if (bookings.isEmpty()) {
            throw new ResourceNotFoundException("No bookings found for bus with ID: " + busId);
        }
        return bookings;
    }

    // Delete a bus booking by ID
    public void deleteBusBooking(int id) throws ResourceNotFoundException {
        if (!busBookingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Bus booking not found with ID: " + id);
        }
        busBookingRepository.deleteById(id);
    }
}
