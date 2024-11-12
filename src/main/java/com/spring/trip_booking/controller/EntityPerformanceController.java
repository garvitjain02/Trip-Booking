package com.spring.trip_booking.controller;

import com.spring.trip_booking.model.EntityPerformance;
import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.service.EntityPerformanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entity-performance")
public class EntityPerformanceController {

    @Autowired
    private EntityPerformanceService entityPerformanceService;

    // Endpoint to add a new EntityPerformance
    @PostMapping("/add")
    public EntityPerformance addEntityPerformance(@RequestBody EntityPerformance entityPerformance) {
        return entityPerformanceService.saveEntityPerformance(entityPerformance);
    }

    // Endpoint to retrieve all EntityPerformance records
    @GetMapping("/all")
    public List<EntityPerformance> getAllEntityPerformances() {
        return entityPerformanceService.getAllEntityPerformances();
    }

    // Endpoint to get an EntityPerformance by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getEntityPerformanceById(@PathVariable int id, ResponseMessageDto dto) {
        try {
            EntityPerformance entityPerformance = entityPerformanceService.getEntityPerformanceById(id);
            return ResponseEntity.ok(entityPerformance);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }

    // Endpoint to delete an EntityPerformance by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEntityPerformanceById(@PathVariable int id, ResponseMessageDto dto) {
        try {
            entityPerformanceService.getEntityPerformanceById(id); // Validate if exists
            entityPerformanceService.deleteEntityPerformanceById(id);
            dto.setMsg("Entity performance deleted successfully.");
            return ResponseEntity.ok(dto);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }

    // Endpoint to get EntityPerformance by Entity ID
    @GetMapping("/entity/{entityId}")
    public List<EntityPerformance> getEntityPerformanceByEntityId(@PathVariable int entityId) {
        return entityPerformanceService.getEntityPerformanceByEntityId(entityId);
    }
}
