package com.spring.trip_booking.service;

import com.spring.trip_booking.model.VendorPerformance;
import com.spring.trip_booking.repository.VendorPerformanceRepository;
import com.spring.trip_booking.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorPerformanceService {

    @Autowired
    private VendorPerformanceRepository vendorPerformanceRepository;

    // Method to add or update a VendorPerformance
    public VendorPerformance saveVendorPerformance(VendorPerformance vendorPerformance) {
        return vendorPerformanceRepository.save(vendorPerformance);
    }

    // Method to retrieve all VendorPerformances
    public List<VendorPerformance> getAllVendorPerformances() {
        return vendorPerformanceRepository.findAll();
    }

    // Method to get a VendorPerformance by ID
    public VendorPerformance getVendorPerformanceById(int id) throws ResourceNotFoundException {
        Optional<VendorPerformance> vendorPerformance = vendorPerformanceRepository.findById(id);
        return vendorPerformance.orElseThrow(() -> new ResourceNotFoundException("VendorPerformance with ID " + id + " not found"));
    }

    // Method to delete a VendorPerformance by ID
    public void deleteVendorPerformanceById(int id) {
        vendorPerformanceRepository.deleteById(id);
    }

    // Method to get all VendorPerformances by Vendor ID
    public List<VendorPerformance> getVendorPerformancesByVendorId(int vendorId) {
        return vendorPerformanceRepository.findByVendorId(vendorId);
    }
}
