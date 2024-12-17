package com.spring.trip_booking;

import com.spring.trip_booking.service.FlightBookingService;

import com.spring.trip_booking.model.Flight;
import com.spring.trip_booking.model.FlightBooking;
import com.spring.trip_booking.model.Passengers;
import com.spring.trip_booking.repository.FlightBookingRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FlightBookingServiceTest {

    @Mock
    private FlightBookingRepository flightBookingRepository;

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private FlightBookingService flightBookingService;

    private Flight flight;
    private FlightBooking flightBooking;
    private Passengers passenger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        flight = new Flight();
        flight.setFlightId(1);
        flight.setFlightNumber("AI101");
        flight.setAirline("Air India");

        passenger = new Passengers();
        passenger.setPassengerId(1L);
        passenger.setFirstName("John");
        passenger.setLastName("Doe");

        flightBooking = new FlightBooking();
        flightBooking.setFlight(flight);
        flightBooking.setBookingDate(LocalDateTime.now());
        flightBooking.setPaymentStatus(true);
        flightBooking.setTotalAmount(5000);
        flightBooking.setPassengers(Arrays.asList(passenger));
    }

    @Test
    void testSaveFlightBooking() {
        when(entityManager.merge(any(Passengers.class))).thenReturn(passenger);
        when(flightBookingRepository.save(any(FlightBooking.class))).thenReturn(flightBooking);

        FlightBooking savedFlightBooking = flightBookingService.saveFlightBooking(flightBooking);

        assertNotNull(savedFlightBooking);
        assertEquals("Air India", savedFlightBooking.getFlight().getAirline());
        assertEquals(1, savedFlightBooking.getPassengers().size());
        verify(flightBookingRepository, times(1)).save(flightBooking);
    }

    @Test
    void testGetAllFlightBookings() {
        List<FlightBooking> flightBookings = Arrays.asList(flightBooking);
        when(flightBookingRepository.findAll()).thenReturn(flightBookings);

        List<FlightBooking> result = flightBookingService.getAllFlightBookings();

        assertEquals(1, result.size());
        assertEquals("Air India", result.get(0).getFlight().getAirline());
        verify(flightBookingRepository, times(1)).findAll();
    }

    @Test
    void testGetFlightBookingById() {
        when(flightBookingRepository.findById(1L)).thenReturn(Optional.of(flightBooking));

        Optional<FlightBooking> result = flightBookingService.getFlightBookingById(1L);

        assertTrue(result.isPresent());
        assertEquals("AI101", result.get().getFlight().getFlightNumber());
        verify(flightBookingRepository, times(1)).findById(1L);
    }

    

    @Test
    void testDeleteFlightBooking() {
        when(flightBookingRepository.existsById(1L)).thenReturn(true);

        boolean result = flightBookingService.deleteFlightBooking(1L);

        assertTrue(result);
        verify(flightBookingRepository, times(1)).deleteById(1L);
    }
}
