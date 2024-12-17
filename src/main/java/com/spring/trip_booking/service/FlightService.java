package com.spring.trip_booking.service;

import com.spring.trip_booking.model.Flight;
import com.spring.trip_booking.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    // Save a new flight
    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    // Get all flights
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    // Get a flight by ID
    public Optional<Flight> getFlightById(int id) {
        return flightRepository.findById(id);
    }

    // Update a flight
    public Flight updateFlight(int id, Flight flightDetails) {
        return flightRepository.findById(id).map(existingFlight -> {
            existingFlight.setFlightNumber(flightDetails.getFlightNumber());
            existingFlight.setAirline(flightDetails.getAirline());
            existingFlight.setOrigin(flightDetails.getOrigin());
            existingFlight.setDestination(flightDetails.getDestination());
            existingFlight.setDepartureTime(flightDetails.getDepartureTime());
            existingFlight.setArrivalTime(flightDetails.getArrivalTime());
            existingFlight.setDuration(flightDetails.getDuration());
            existingFlight.setAvailableSeats(flightDetails.getAvailableSeats());
            existingFlight.setPrice(flightDetails.getPrice());
            return flightRepository.save(existingFlight);
        }).orElse(null);
    }

    // Delete a flight by ID
    public boolean deleteFlight(int id) {
        if (flightRepository.existsById(id)) {
            flightRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Flight> getFlightsByOriginAndDestination(String origin, String destination) {
        return flightRepository.findFlightsByOriginAndDestination(origin, destination);
    }
}
