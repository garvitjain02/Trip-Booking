package com.spring.trip_booking.controller;

import com.spring.trip_booking.model.VendorPerformance;
import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.service.VendorPerformanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendor-performance")
public class VendorPerformanceController {

    @Autowired
    private VendorPerformanceService vendorPerformanceService;

    // Endpoint to add a new VendorPerformance
    @PostMapping("/add")
    public VendorPerformance addVendorPerformance(@RequestBody VendorPerformance vendorPerformance) {
        return vendorPerformanceService.saveVendorPerformance(vendorPerformance);
    }

    // Endpoint to retrieve all VendorPerformances
    @GetMapping("/all")
    public List<VendorPerformance> getAllVendorPerformances() {
        return vendorPerformanceService.getAllVendorPerformances();
    }

    // Endpoint to get VendorPerformance by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getVendorPerformanceById(@PathVariable int id, ResponseMessageDto dto) {
        try {
            VendorPerformance vendorPerformance = vendorPerformanceService.getVendorPerformanceById(id);
            return ResponseEntity.ok(vendorPerformance);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }

    // Endpoint to delete VendorPerformance by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVendorPerformanceById(@PathVariable int id, ResponseMessageDto dto) {
        try {
            vendorPerformanceService.getVendorPerformanceById(id); // Validate if exists
            vendorPerformanceService.deleteVendorPerformanceById(id);
            dto.setMsg("Vendor performance deleted successfully.");
            return ResponseEntity.ok(dto);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }

    // Endpoint to get VendorPerformances by Vendor ID
    @GetMapping("/vendor/{vendorId}")
    public List<VendorPerformance> getVendorPerformancesByVendorId(@PathVariable int vendorId) {
        return vendorPerformanceService.getVendorPerformancesByVendorId(vendorId);
    }
}
