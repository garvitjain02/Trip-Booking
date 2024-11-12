package com.spring.trip_booking.controller;

import com.spring.trip_booking.model.UserPerformance;
import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.service.UserPerformanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-performance")
public class UserPerformanceController {

    @Autowired
    private UserPerformanceService userPerformanceService;

    // Endpoint to add a new UserPerformance
    @PostMapping("/add")
    public UserPerformance addUserPerformance(@RequestBody UserPerformance userPerformance) {
        return userPerformanceService.saveUserPerformance(userPerformance);
    }

    // Endpoint to retrieve all UserPerformances
    @GetMapping("/all")
    public List<UserPerformance> getAllUserPerformances() {
        return userPerformanceService.getAllUserPerformances();
    }

    // Endpoint to get UserPerformance by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserPerformanceById(@PathVariable int id, ResponseMessageDto dto) {
        try {
            UserPerformance userPerformance = userPerformanceService.getUserPerformanceById(id);
            return ResponseEntity.ok(userPerformance);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }

    // Endpoint to delete UserPerformance by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserPerformanceById(@PathVariable int id, ResponseMessageDto dto) {
        try {
            userPerformanceService.getUserPerformanceById(id); // Validate if exists
            userPerformanceService.deleteUserPerformanceById(id);
            dto.setMsg("User performance deleted successfully.");
            return ResponseEntity.ok(dto);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }

    // Endpoint to get UserPerformances by User ID
    @GetMapping("/user/{userId}")
    public List<UserPerformance> getUserPerformancesByUserId(@PathVariable int userId) {
        return userPerformanceService.getUserPerformancesByUserId(userId);
    }
}
