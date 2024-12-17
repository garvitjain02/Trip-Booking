package com.spring.trip_booking;


import com.spring.trip_booking.service.AirportService;
import com.spring.trip_booking.model.Airport;
import com.spring.trip_booking.repository.AirportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AirportServiceTest {

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private AirportService airportService;

    private Airport airport;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        airport = new Airport();
        airport.setAirportCode("DEL");
        airport.setAirportName("Indira Gandhi International Airport");
        airport.setCity("New Delhi");
        airport.setCountry("India");
    }

    @Test
    void testSaveAirport() {
        when(airportRepository.save(any(Airport.class))).thenReturn(airport);

        Airport savedAirport = airportService.saveAirport(airport);

        assertNotNull(savedAirport);
        assertEquals("DEL", savedAirport.getAirportCode());
        verify(airportRepository, times(1)).save(airport);
    }

    @Test
    void testGetAllAirports() {
        List<Airport> airports = Arrays.asList(airport);
        when(airportRepository.findAll()).thenReturn(airports);

        List<Airport> result = airportService.getAllAirports();

        assertEquals(1, result.size());
        verify(airportRepository, times(1)).findAll();
    }

    @Test
    void testGetAirportByCode() {
        when(airportRepository.findById("DEL")).thenReturn(Optional.of(airport));

        Optional<Airport> result = airportService.getAirportByCode("DEL");

        assertTrue(result.isPresent());
        assertEquals("DEL", result.get().getAirportCode());
        verify(airportRepository, times(1)).findById("DEL");
    }

    @Test
    void testUpdateAirport() {
        when(airportRepository.findById("DEL")).thenReturn(Optional.of(airport));
        when(airportRepository.save(any(Airport.class))).thenReturn(airport);

        Airport updatedAirport = airportService.updateAirport("DEL", airport);

        assertNotNull(updatedAirport);
        assertEquals("DEL", updatedAirport.getAirportCode());
        verify(airportRepository, times(1)).save(airport);
    }

    @Test
    void testDeleteAirport() {
        when(airportRepository.existsById("DEL")).thenReturn(true);

        boolean result = airportService.deleteAirport("DEL");

        assertTrue(result);
        verify(airportRepository, times(1)).deleteById("DEL");
    }
}
