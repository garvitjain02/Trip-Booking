package com.spring.trip_booking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.EntityTable;
import com.spring.trip_booking.service.EntityTableService;

import java.util.List;

@RestController
@RequestMapping("/entity")
public class EntityTableController {

    @Autowired
    private EntityTableService entityTableService;

    private static final Logger logger = LoggerFactory.getLogger(EntityTableController.class);

    // Add a new EntityTable entry
    @PostMapping("/add")
    public EntityTable addEntity(@RequestBody EntityTable entityTable) {
        logger.info("Adding a new entity with name: {}", entityTable.getEntityName());
        return entityTableService.insert(entityTable);
    }

    // Get all EntityTable entries with pagination
    @GetMapping("/all")
    public ResponseEntity<Page<EntityTable>> getAllEntities(Pageable pageable) {
        logger.info("Fetching all entity records with pagination: page {}, size {}", pageable.getPageNumber(), pageable.getPageSize());
        Page<EntityTable> entities = entityTableService.getAllEntities(pageable);
        return ResponseEntity.ok(entities);
    }

    // Get an EntityTable entry by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getEntityById(@PathVariable int id) throws ResourceNotFoundException{
        logger.info("Fetching entity with ID: {}", id);
        EntityTable entityTable = entityTableService.getEntityById(id);
        logger.info("Entity found with ID: {}", id);
        return ResponseEntity.ok(entityTable);
    }

    // Delete an EntityTable entry by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEntity(@PathVariable int id, ResponseMessageDto dto) throws ResourceNotFoundException{
        logger.info("Attempting to delete entity with ID: {}", id);
        entityTableService.getEntityById(id);
        entityTableService.delete(id);
        dto.setMsg("Entity entry deleted");
        logger.info("Entity deleted with ID: {}", id);
        return ResponseEntity.ok(dto);
    }

    // Update an EntityTable entry by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEntity(@PathVariable int id, @RequestBody EntityTable newEntityTable, ResponseMessageDto dto) throws ResourceNotFoundException{
        logger.info("Attempting to update entity with ID: {}", id);
        EntityTable existingEntityTable = entityTableService.validate(id);

        if (newEntityTable.getEntityName() != null) {
            existingEntityTable.setEntityName(newEntityTable.getEntityName());
            logger.info("Updating entity name to: {}", newEntityTable.getEntityName());
        }
        if (newEntityTable.getEntityType() != null) {
            existingEntityTable.setEntityType(newEntityTable.getEntityType());
            logger.info("Updating entity type to: {}", newEntityTable.getEntityType());
        }
        if (newEntityTable.getVendor() != null) {
            existingEntityTable.setVendor(newEntityTable.getVendor());
            logger.info("Updating entity vendor to: {}", newEntityTable.getVendor());
        }

        existingEntityTable = entityTableService.insert(existingEntityTable);
        logger.info("Entity with ID {} updated successfully", id);
        return ResponseEntity.ok(existingEntityTable);
    }

    // Batch insert EntityTable entries
    @PostMapping("/batch/add")
    public List<EntityTable> batchInsert(@RequestBody List<EntityTable> entityTables) {
        logger.info("Batch inserting {} entities", entityTables.size());
        return entityTableService.insertInBatch(entityTables);
    }

    // Method to get entities by vendor ID
    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<?> getEntitiesByVendorId(@PathVariable int vendorId) {
        logger.info("Fetching entities for vendor ID: {}", vendorId);
        try {
            List<EntityTable> entities = entityTableService.getEntitiesByVendorId(vendorId);
            if (entities.isEmpty()) {
                logger.warn("No entities found for vendor ID: {}", vendorId);
                return ResponseEntity.status(404).body("No entities found for the provided vendor ID");
            }
            logger.info("{} entities found for vendor ID: {}", entities.size(), vendorId);
            return ResponseEntity.ok(entities);
        } catch (Exception e) {
            logger.error("Error retrieving entities for vendor ID {}: {}", vendorId, e.getMessage());
            return ResponseEntity.status(500).body("Error retrieving entities: " + e.getMessage());
        }
    }
}
