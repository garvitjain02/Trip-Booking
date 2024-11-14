package com.spring.trip_booking.controller;

import com.spring.trip_booking.model.VendorPerformance;
import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.service.VendorPerformanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/vendor-performance")
public class VendorPerformanceController {

    @Autowired
    private VendorPerformanceService vendorPerformanceService;

    private static final Logger logger = LoggerFactory.getLogger(VendorPerformanceController.class);

    // Endpoint to add a new VendorPerformance
    @PostMapping("/add")
    public VendorPerformance addVendorPerformance(@RequestBody VendorPerformance vendorPerformance) {
        logger.info("Adding new vendor performance: {}", vendorPerformance);
        return vendorPerformanceService.saveVendorPerformance(vendorPerformance);
    }

    // Endpoint to retrieve all VendorPerformances with pagination
    @GetMapping("/all")
    public ResponseEntity<Page<VendorPerformance>> getAllVendorPerformances(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        logger.info("Fetching all vendor performances with pagination (page: {}, size: {})", page, size);
        Page<VendorPerformance> vendorPerformancesPage = vendorPerformanceService.getAllVendorPerformances(pageable);
        return ResponseEntity.ok(vendorPerformancesPage);
    }

 // Endpoint to get VendorPerformance by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getVendorPerformanceById(@PathVariable int id) throws ResourceNotFoundException{
        logger.info("Fetching vendor performance with ID: {}", id);
        VendorPerformance vendorPerformance = vendorPerformanceService.getVendorPerformanceById(id);
        return ResponseEntity.ok(vendorPerformance);
    }


    // Endpoint to delete VendorPerformance by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVendorPerformanceById(@PathVariable int id, ResponseMessageDto dto) throws ResourceNotFoundException{
        logger.info("Deleting vendor performance with ID: {}", id);
        vendorPerformanceService.getVendorPerformanceById(id);  // Validate if exists
        vendorPerformanceService.deleteVendorPerformanceById(id);
        dto.setMsg("Vendor performance deleted successfully.");
        logger.info("Vendor performance deleted successfully with ID: {}", id);
        return ResponseEntity.ok(dto);
    }

    // Endpoint to get VendorPerformances by Vendor ID
    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<VendorPerformance>> getVendorPerformancesByVendorId(@PathVariable int vendorId) {
        logger.info("Fetching vendor performances for vendor ID: {}", vendorId);
        List<VendorPerformance> vendorPerformances = vendorPerformanceService.getVendorPerformancesByVendorId(vendorId);
        return ResponseEntity.ok(vendorPerformances);
    }
    
    // Endpoint to fetch VendorPerformance and FlightRequests by vendor ID
    @GetMapping("/vendorflight/{id}")
    public ResponseEntity<List<Object[]>> getVendorPerformanceWithFlightRequests(@PathVariable int id) {
        List<Object[]> vendorPerformanceData = vendorPerformanceService.getVendorPerformanceWithFlightRequests(id);
        return ResponseEntity.ok(vendorPerformanceData);
    }
}
