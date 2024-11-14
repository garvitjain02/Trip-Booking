package com.spring.trip_booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.EntityTable;
import com.spring.trip_booking.repository.EntityTableRepository;

@Service
public class EntityTableService {

    @Autowired
    private EntityTableRepository entityTableRepository;

    // Insert or update an EntityTable
    public EntityTable insert(EntityTable entityTable) {
        return entityTableRepository.save(entityTable);
    }

    // Method to retrieve all entities with pagination
    public Page<EntityTable> getAllEntities(Pageable pageable) {
        return entityTableRepository.findAll(pageable);
    }

    // Get an EntityTable entry by ID
    public EntityTable getEntityById(int id) throws ResourceNotFoundException {
        Optional<EntityTable> optional = entityTableRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("EntityTable ID invalid");
        }
        return optional.get();
    }

    // Delete an EntityTable entry by ID
    public void delete(int id) {
        entityTableRepository.deleteById(id);
    }

    // Validate if an EntityTable entry exists by ID
    public EntityTable validate(int id) throws ResourceNotFoundException {
        Optional<EntityTable> optional = entityTableRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("EntityTable ID invalid");
        }
        return optional.get();
    }

    // Method to find entities by vendor ID
    public List<EntityTable> getEntitiesByVendorId(int vendorId) {
        return entityTableRepository.findEntitiesByVendorId(vendorId);
    }

    // Batch insert EntityTable entries
    public List<EntityTable> insertInBatch(List<EntityTable> entityTables) {
        return entityTableRepository.saveAll(entityTables);
    }
}
