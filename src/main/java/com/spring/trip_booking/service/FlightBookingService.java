
package com.spring.trip_booking.service;

import com.spring.trip_booking.model.FlightBooking;
import com.spring.trip_booking.repository.FlightBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightBookingService {

    @Autowired
    private FlightBookingRepository flightBookingRepository;

    public FlightBooking saveBooking(FlightBooking booking) {
        return flightBookingRepository.save(booking);
    }
}
