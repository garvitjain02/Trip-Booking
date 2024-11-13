package com.trip_project.bus.service;

import com.trip_project.bus.exception.ResourceNotFoundException;
import com.trip_project.bus.exception.InvalidInputException;
import com.trip_project.bus.exception.DuplicateResourceException;
import com.trip_project.bus.model.Bus;
import com.trip_project.bus.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {

    private final BusRepository busRepository;

    @Autowired
    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    // Create or Update a bus
    public Bus saveBus(Bus bus) throws InvalidInputException, DuplicateResourceException {
        // Validate input
        if (bus.getBusNumber() == null || bus.getBusNumber().isEmpty()) {
            throw new InvalidInputException("Bus number cannot be null or empty.");
        }
        
        // Check for duplicate bus number
        Bus existingBus = busRepository.findByBusNumber(bus.getBusNumber());
        if (existingBus != null && existingBus.getId() != bus.getId()) {
            throw new DuplicateResourceException("Bus with number " + bus.getBusNumber() + " already exists.");
        }

        return busRepository.save(bus);
    }

    // Find all buses
    public List<Bus> getAllBuses() throws ResourceNotFoundException {
        List<Bus> buses = busRepository.findAll();
        if (buses.isEmpty()) {
            throw new ResourceNotFoundException("No buses found.");
        }
        return buses;
    }

    // Find bus by id
    public Bus getBusById(int id) throws ResourceNotFoundException {
        return busRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with ID: " + id));
    }

    // Find bus by bus number
    public Bus getBusByBusNumber(String busNumber) throws ResourceNotFoundException {
        Bus bus = busRepository.findByBusNumber(busNumber);
        if (bus == null) {
            throw new ResourceNotFoundException("Bus not found with number: " + busNumber);
        }
        return bus;
    }

    // Delete a bus by id
    public void deleteBus(int id) throws ResourceNotFoundException {
        // Check if the bus exists before deleting
        if (!busRepository.existsById(id)) {
            throw new ResourceNotFoundException("Bus not found with ID: " + id);
        }
        busRepository.deleteById(id);
    }
}
