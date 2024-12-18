package com.spring.trip_booking.service;

import com.spring.trip_booking.model.UserPerformance;
import com.spring.trip_booking.repository.UserPerformanceRepository;
import com.spring.trip_booking.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPerformanceService {

    @Autowired
    private UserPerformanceRepository userPerformanceRepository;

    // Method to add or update a UserPerformance
    public UserPerformance saveUserPerformance(UserPerformance userPerformance) {
        return userPerformanceRepository.save(userPerformance);
    }

    // Method to retrieve all UserPerformances
    public List<UserPerformance> getAllUserPerformances() {
        return userPerformanceRepository.findAll();
    }

    // Method to get a UserPerformance by ID
    public UserPerformance getUserPerformanceById(int id) throws ResourceNotFoundException {
        Optional<UserPerformance> userPerformance = userPerformanceRepository.findById(id);
        return userPerformance.orElseThrow(() -> new ResourceNotFoundException("UserPerformance with ID " + id + " not found"));
    }

    // Method to delete a UserPerformance by ID
    public void deleteUserPerformanceById(int id) {
        userPerformanceRepository.deleteById(id);
    }

    // Method to get all UserPerformances by User ID
    public List<UserPerformance> getUserPerformancesByUserId(int userId) {
        return userPerformanceRepository.findByUserId(userId);
    }
}
