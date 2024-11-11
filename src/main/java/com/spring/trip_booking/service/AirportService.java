
package com.spring.trip_booking.service;

import com.spring.trip_booking.model.Airport;
import com.spring.trip_booking.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }
}
