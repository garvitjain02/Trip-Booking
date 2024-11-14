package com.spring.trip_booking.controller;

import com.spring.trip_booking.model.UserPerformance;
import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.service.UserPerformanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/user-performance")
public class UserPerformanceController {

    private static final Logger logger = LoggerFactory.getLogger(UserPerformanceController.class);

    @Autowired
    private UserPerformanceService userPerformanceService;

    // Endpoint to add a new UserPerformance
    @PostMapping("/add")
    public UserPerformance addUserPerformance(@RequestBody UserPerformance userPerformance) {
        logger.info("Request to add a new UserPerformance: {}", userPerformance);
        return userPerformanceService.saveUserPerformance(userPerformance);
    }

    // Endpoint to retrieve all UserPerformances with pagination
    @GetMapping("/all")
    public Page<UserPerformance> getAllUserPerformances(Pageable pageable) {
        logger.info("Fetching all UserPerformances with pagination");
        return userPerformanceService.getAllUserPerformances(pageable);
    }

    // Endpoint to get UserPerformance by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserPerformanceById(@PathVariable int id) throws ResourceNotFoundException{
        logger.info("Fetching UserPerformance with ID: {}", id);
        UserPerformance userPerformance = userPerformanceService.getUserPerformanceById(id);
        return ResponseEntity.ok(userPerformance);
    }

    // Endpoint to delete UserPerformance by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserPerformanceById(@PathVariable int id, ResponseMessageDto dto) throws ResourceNotFoundException{
        logger.info("Request to delete UserPerformance with ID: {}", id);
        userPerformanceService.getUserPerformanceById(id); // Validate if exists
        userPerformanceService.deleteUserPerformanceById(id);
        dto.setMsg("User performance deleted successfully.");
        return ResponseEntity.ok(dto);
    }

    // Endpoint to get UserPerformances by User ID
    @GetMapping("/user/{userId}")
    public List<UserPerformance> getUserPerformancesByUserId(@PathVariable int userId) {
        logger.info("Fetching UserPerformances for User ID: {}", userId);
        return userPerformanceService.getUserPerformancesByUserId(userId);
    }
    
    @GetMapping("/ratings/{userId}")
    public List<Object[]> getUserPerformanceWithRatings(@PathVariable int userId) {
        return userPerformanceService.getUserPerformanceWithRatings(userId);
    }
}
