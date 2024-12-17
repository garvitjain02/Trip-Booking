package com.spring.trip_booking.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.trip_booking.model.Airport;
import com.spring.trip_booking.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

      private static final Logger logger = LoggerFactory.getLogger(AirportService.class);
    @Autowired
    private AirportRepository airportRepository;

    
    // Save a new airport
    public Airport saveAirport(Airport airport) {
        logger.info("Attempting to save a new airport: {}", airport);
        try {
            Airport savedAirport = airportRepository.save(airport);
            logger.info("Successfully saved airport: {}", savedAirport);
            return savedAirport;
        } catch (Exception e) {
            logger.error("Failed to save airport: {}", airport, e);
            throw e; 
        }
    }
    // Save a new airport
    
    /*public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }*/

    // Get all airports
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    // Get an airport by code
    public Optional<Airport> getAirportByCode(String airportCode) {
        return airportRepository.findById(airportCode);
    }

    // Update an airport
    public Airport updateAirport(String airportCode, Airport airportDetails) {
        return airportRepository.findById(airportCode).map(existingAirport -> {
            existingAirport.setAirportName(airportDetails.getAirportName());
            existingAirport.setCity(airportDetails.getCity());
            existingAirport.setCountry(airportDetails.getCountry());
            return airportRepository.save(existingAirport);
        }).orElse(null);
    }

    // Delete an airport by code
    public boolean deleteAirport(String airportCode) {
        if (airportRepository.existsById(airportCode)) {
            airportRepository.deleteById(airportCode);
            return true;
        }
        return false;
    }
}
