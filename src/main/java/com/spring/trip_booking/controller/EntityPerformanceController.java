package com.spring.trip_booking.controller;

import com.spring.trip_booking.model.EntityPerformance;
import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.service.EntityPerformanceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entity-performance")
public class EntityPerformanceController {

    @Autowired
    private EntityPerformanceService entityPerformanceService;

    private static final Logger logger = LoggerFactory.getLogger(EntityPerformanceController.class);

    // Endpoint to add a new EntityPerformance
    @PostMapping("/add")
    public EntityPerformance addEntityPerformance(@RequestBody EntityPerformance entityPerformance) {
        logger.info("Adding new entity performance for entity ID: {}", entityPerformance.getEntity());
        return entityPerformanceService.saveEntityPerformance(entityPerformance);
    }

    // Endpoint to retrieve all EntityPerformance records with pagination
    @GetMapping("/all")
    public ResponseEntity<Page<EntityPerformance>> getAllEntityPerformances(Pageable pageable) {
        logger.info("Fetching all entity performance records with pagination: page {}, size {}", pageable.getPageNumber(), pageable.getPageSize());
        Page<EntityPerformance> entityPerformances = entityPerformanceService.getAllEntityPerformances(pageable);
        return ResponseEntity.ok(entityPerformances);
    }

    // Endpoint to get an EntityPerformance by ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityPerformance> getEntityPerformanceById(@PathVariable int id) throws ResourceNotFoundException{
        logger.info("Fetching entity performance record with ID: {}", id);
        EntityPerformance entityPerformance = entityPerformanceService.getEntityPerformanceById(id);
        logger.info("Entity performance record found with ID: {}", id);
        return ResponseEntity.ok(entityPerformance);
    }

    // Endpoint to delete an EntityPerformance by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessageDto> deleteEntityPerformanceById(@PathVariable int id, ResponseMessageDto dto) throws ResourceNotFoundException{
        logger.info("Attempting to delete entity performance with ID: {}", id);
        entityPerformanceService.getEntityPerformanceById(id);
        entityPerformanceService.deleteEntityPerformanceById(id);
        dto.setMsg("Entity performance deleted successfully.");
        logger.info("Entity performance deleted with ID: {}", id);
        return ResponseEntity.ok(dto);
    }

    // Endpoint to get EntityPerformance by Entity ID
    @GetMapping("/entity/{entityId}")
    public List<EntityPerformance> getEntityPerformanceByEntityId(@PathVariable int entityId) {
        logger.info("Fetching entity performance records for entity ID: {}", entityId);
        return entityPerformanceService.getEntityPerformanceByEntityId(entityId);
    }
}
