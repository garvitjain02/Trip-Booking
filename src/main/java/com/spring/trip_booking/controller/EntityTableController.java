package com.spring.trip_booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.EntityTable;
import com.spring.trip_booking.service.EntityTableService;

@RestController
@RequestMapping("/entity")
public class EntityTableController {

    @Autowired
    private EntityTableService entityTableService;

    // Add a new EntityTable entry
    @PostMapping("/add")
    public EntityTable addEntity(@RequestBody EntityTable entityTable) {
        return entityTableService.insert(entityTable);
    }

    // Get all EntityTable entries
    @GetMapping("/all")
    public List<EntityTable> getAllEntities() {
        return entityTableService.getAllEntities();
    }

    // Get an EntityTable entry by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getEntityById(@PathVariable int id) {
        try {
            EntityTable entityTable = entityTableService.getEntityById(id);
            return ResponseEntity.ok(entityTable);
        } catch (ResourceNotFoundException e) {
            ResponseMessageDto dto = new ResponseMessageDto();
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }

    // Delete an EntityTable entry by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEntity(@PathVariable int id, ResponseMessageDto dto) {
        try {
            entityTableService.validate(id);
            entityTableService.delete(id);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
        dto.setMsg("Entity entry deleted");
        return ResponseEntity.ok(dto);
    }

    // Update an EntityTable entry by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEntity(@PathVariable int id, @RequestBody EntityTable newEntityTable, ResponseMessageDto dto) {
        try {
            EntityTable existingEntityTable = entityTableService.validate(id);
            if (newEntityTable.getEntityName() != null) {
                existingEntityTable.setEntityName(newEntityTable.getEntityName());
            }
            if (newEntityTable.getEntityType() != null) {
                existingEntityTable.setEntityType(newEntityTable.getEntityType());
            }
            if (newEntityTable.getVendor() != null) {
                existingEntityTable.setVendor(newEntityTable.getVendor());
            }

            existingEntityTable = entityTableService.insert(existingEntityTable);
            return ResponseEntity.ok(existingEntityTable);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }

    // Batch insert EntityTable entries
    @PostMapping("/batch/add")
    public List<EntityTable> batchInsert(@RequestBody List<EntityTable> entityTables) {
        return entityTableService.insertInBatch(entityTables);
    }
    
    // Method to get entities by vendor ID
    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<?> getEntitiesByVendorId(@PathVariable int vendorId) {
        try {
            List<EntityTable> entities = entityTableService.getEntitiesByVendorId(vendorId);
            if (entities.isEmpty()) {
                return ResponseEntity.status(404).body("No entities found for the provided vendor ID");
            }
            return ResponseEntity.ok(entities);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving entities: " + e.getMessage());
        }
    }

}
