package com.spring.trip_booking;

import com.spring.trip_booking.service.PassengersService;

import com.spring.trip_booking.model.Passengers;
import com.spring.trip_booking.repository.PassengersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PassengersServiceTest {

    @Mock
    private PassengersRepository passengersRepository;

    @InjectMocks
    private PassengersService passengersService;

    private Passengers passenger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        passenger = new Passengers();
        passenger.setPassengerId(1L);
        passenger.setFirstName("John");
        passenger.setLastName("Doe");
        passenger.setDateOfBirth(LocalDate.of(1990, 1, 1));
        passenger.setPassportNumber("A1234567");
    }

    @Test
    void testSavePassenger() {
        when(passengersRepository.save(any(Passengers.class))).thenReturn(passenger);

        Passengers savedPassenger = passengersService.savePassenger(passenger);

        assertNotNull(savedPassenger);
        assertEquals("John", savedPassenger.getFirstName());
        assertEquals("Doe", savedPassenger.getLastName());
        verify(passengersRepository, times(1)).save(passenger);
    }

    @Test
    void testGetAllPassengers() {
        List<Passengers> passengersList = Arrays.asList(passenger);
        when(passengersRepository.findAll()).thenReturn(passengersList);

        List<Passengers> result = passengersService.getAllPassengers();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
        verify(passengersRepository, times(1)).findAll();
    }

    @Test
    void testGetPassengerById() {
        when(passengersRepository.findById(1L)).thenReturn(Optional.of(passenger));

        Optional<Passengers> result = passengersService.getPassengerById(1L);

        assertTrue(result.isPresent());
        assertEquals("John", result.get().getFirstName());
        verify(passengersRepository, times(1)).findById(1L);
    }

    

    @Test
    void testDeletePassenger() {
        when(passengersRepository.existsById(1L)).thenReturn(true);

        boolean result = passengersService.deletePassenger(1L);

        assertTrue(result);
        verify(passengersRepository, times(1)).deleteById(1L);
    }
}

