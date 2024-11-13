package com.trip_project.bus.service;

import com.trip_project.bus.exception.ResourceNotFoundException;
import com.trip_project.bus.exception.InvalidInputException;
import com.trip_project.bus.model.BusSeat;
import com.trip_project.bus.repository.BusSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusSeatService {

	@Autowired
    private final BusSeatRepository busSeatRepository;

    @Autowired
    public BusSeatService(BusSeatRepository busSeatRepository) {
        this.busSeatRepository = busSeatRepository;
    }

    // Create or Update a bus seat
    public BusSeat saveBusSeat(BusSeat busSeat) throws InvalidInputException {
        // Validate input (seat number, fare, etc.)
        if (busSeat.getSeatNumber() <= 0 || busSeat.getFare() <= 0) {
            throw new InvalidInputException("Seat number and fare must be valid.");
        }
        return busSeatRepository.save(busSeat);
    }

    // Find all bus seats
    public List<BusSeat> getAllBusSeats() throws ResourceNotFoundException {
        List<BusSeat> seats = busSeatRepository.findAll();
        if (seats.isEmpty()) {
            throw new ResourceNotFoundException("No bus seats found.");
        }
        return seats;
    }

    // Find bus seat by ID
    public BusSeat getBusSeatById(int id) throws ResourceNotFoundException {
        return busSeatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus seat not found with ID: " + id));
    }

    // Find bus seats by bus ID
    public List<BusSeat> getBusSeatsByBusId(int busId) throws ResourceNotFoundException {
        List<BusSeat> seats = busSeatRepository.findByBusId(busId);
        if (seats.isEmpty()) {
            throw new ResourceNotFoundException("No bus seats found for bus with ID: " + busId);
        }
        return seats;
    }

    // Find a bus seat by seat number and bus ID
    public BusSeat getBusSeatBySeatNumberAndBusId(int seatNumber, int busId) throws ResourceNotFoundException {
        BusSeat seat = busSeatRepository.findBySeatNumberAndBusId(seatNumber, busId);
        if (seat == null) {
            throw new ResourceNotFoundException("No bus seat found with seat number " + seatNumber + " and bus ID " + busId);
        }
        return seat;
    }

    // Delete a bus seat by ID
    public void deleteBusSeat(int id) throws ResourceNotFoundException {
        if (!busSeatRepository.existsById(id)) {
            throw new ResourceNotFoundException("Bus seat not found with ID: " + id);
        }
        busSeatRepository.deleteById(id);
    }
}
