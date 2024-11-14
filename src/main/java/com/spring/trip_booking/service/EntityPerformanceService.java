package com.spring.trip_booking.service;

import com.spring.trip_booking.model.EntityPerformance;
import com.spring.trip_booking.repository.EntityPerformanceRepository;
import com.spring.trip_booking.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntityPerformanceService {

    @Autowired
    private EntityPerformanceRepository entityPerformanceRepository;

    // Method to add or update an EntityPerformance
    public EntityPerformance saveEntityPerformance(EntityPerformance entityPerformance) {
        return entityPerformanceRepository.save(entityPerformance);
    }

    // Method to retrieve all EntityPerformance records
    public List<EntityPerformance> getAllEntityPerformances() {
        return entityPerformanceRepository.findAll();
    }

    // Method to get an EntityPerformance by ID
    public EntityPerformance getEntityPerformanceById(int id) throws ResourceNotFoundException {
        Optional<EntityPerformance> entityPerformance = entityPerformanceRepository.findById(id);
        return entityPerformance.orElseThrow(() -> new ResourceNotFoundException("EntityPerformance with ID " + id + " not found"));
    }

    // Method to delete an EntityPerformance by ID
    public void deleteEntityPerformanceById(int id) {
        entityPerformanceRepository.deleteById(id);
    }

    // Method to get all EntityPerformance records by Entity ID
    public List<EntityPerformance> getEntityPerformanceByEntityId(int entityId) {
        return entityPerformanceRepository.findByEntityId(entityId);
    }
    
    // Method to get all entity performance records with pagination
    public Page<EntityPerformance> getAllEntityPerformances(Pageable pageable) {
        return entityPerformanceRepository.findAll(pageable);
    }
}
