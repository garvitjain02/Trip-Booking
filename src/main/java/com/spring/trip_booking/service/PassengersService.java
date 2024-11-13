package com.spring.trip_booking.service;

import com.spring.trip_booking.model.Passengers;
import com.spring.trip_booking.repository.PassengersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengersService {

    @Autowired
    private PassengersRepository passengersRepository;

    // Save a new passenger
    public Passengers savePassenger(Passengers passenger) {
        return passengersRepository.save(passenger);
    }

    // Get all passengers
    public List<Passengers> getAllPassengers() {
        return passengersRepository.findAll();
    }

    // Get a passenger by ID
    public Optional<Passengers> getPassengerById(Long passengerId) {
        return passengersRepository.findById(passengerId);
    }

    // Update passenger details
    public Passengers updatePassenger(Long passengerId, Passengers passengerDetails) {
        return passengersRepository.findById(passengerId).map(existingPassenger -> {
            existingPassenger.setFirstName(passengerDetails.getFirstName());
            existingPassenger.setLastName(passengerDetails.getLastName());
            existingPassenger.setDateOfBirth(passengerDetails.getDateOfBirth());
            existingPassenger.setPassportNumber(passengerDetails.getPassportNumber());
            existingPassenger.setBooking(passengerDetails.getBooking());
            return passengersRepository.save(existingPassenger);
        }).orElse(null);
    }

    // Delete a passenger by ID
    public boolean deletePassenger(Long passengerId) {
        if (passengersRepository.existsById(passengerId)) {
            passengersRepository.deleteById(passengerId);
            return true;
        }
        return false;
    }
}
