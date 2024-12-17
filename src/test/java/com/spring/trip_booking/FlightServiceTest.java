package com.spring.trip_booking;

import com.spring.trip_booking.service.FlightService;

import com.spring.trip_booking.model.Airport;
import com.spring.trip_booking.model.Flight;
import com.spring.trip_booking.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightService flightService;

    private Flight flight;
    private Airport origin;
    private Airport destination;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        origin = new Airport();
        origin.setAirportCode("DEL");
        origin.setAirportName("Indira Gandhi International Airport");
        origin.setCity("New Delhi");
        origin.setCountry("India");

        destination = new Airport();
        destination.setAirportCode("MUM");
        destination.setAirportName("Chhatrapati Shivaji Maharaj International Airport");
        destination.setCity("Mumbai");
        destination.setCountry("India");

        flight = new Flight();
        flight.setFlightId(1);
        flight.setFlightNumber("AI101");
        flight.setAirline("Air India");
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setDepartureTime(LocalDateTime.of(2024, 12, 20, 10, 0));
        flight.setArrivalTime(LocalDateTime.of(2024, 12, 20, 12, 0));
        flight.setDuration(120);
        flight.setAvailableSeats(150);
        flight.setPrice(new BigDecimal("5000.00"));
    }

    @Test
    void testSaveFlight() {
        when(flightRepository.save(any(Flight.class))).thenReturn(flight);

        Flight savedFlight = flightService.saveFlight(flight);

        assertNotNull(savedFlight);
        assertEquals("AI101", savedFlight.getFlightNumber());
        verify(flightRepository, times(1)).save(flight);
    }

    @Test
    void testGetAllFlights() {
        List<Flight> flights = Arrays.asList(flight);
        when(flightRepository.findAll()).thenReturn(flights);

        List<Flight> result = flightService.getAllFlights();

        assertEquals(1, result.size());
        verify(flightRepository, times(1)).findAll();
    }

    @Test
    void testGetFlightById() {
        when(flightRepository.findById(1)).thenReturn(Optional.of(flight));

        Optional<Flight> result = flightService.getFlightById(1);

        assertTrue(result.isPresent());
        assertEquals("AI101", result.get().getFlightNumber());
        verify(flightRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateFlight() {
        when(flightRepository.findById(1)).thenReturn(Optional.of(flight));
        when(flightRepository.save(any(Flight.class))).thenReturn(flight);

        Flight updatedFlight = flightService.updateFlight(1, flight);

        assertNotNull(updatedFlight);
        assertEquals("AI101", updatedFlight.getFlightNumber());
        verify(flightRepository, times(1)).save(flight);
    }

    @Test
    void testDeleteFlight() {
        when(flightRepository.existsById(1)).thenReturn(true);

        boolean result = flightService.deleteFlight(1);

        assertTrue(result);
        verify(flightRepository, times(1)).deleteById(1);
    }

    @Test
    void testGetFlightsByOriginAndDestination() {
        List<Flight> flights = Arrays.asList(flight);
        when(flightRepository.findFlightsByOriginAndDestination("DEL", "MUM")).thenReturn(flights);

        List<Flight> result = flightService.getFlightsByOriginAndDestination("DEL", "MUM");

        assertEquals(1, result.size());
        assertEquals("AI101", result.get(0).getFlightNumber());
        verify(flightRepository, times(1)).findFlightsByOriginAndDestination("DEL", "MUM");
    }
}
