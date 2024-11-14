package com.spring.trip_booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.LogTable;
import com.spring.trip_booking.repository.LogTableRepository;

@Service
public class LogTableService {

    @Autowired
    private LogTableRepository logTableRepository;

    // Insert or update a LogTable
    public LogTable insert(LogTable logTable) {
        return logTableRepository.save(logTable);
    }

    // Get all LogTable entries with pagination
    public Page<LogTable> getAllLogTables(Pageable pageable) {
        return logTableRepository.findAll(pageable);
    }
    
    // Delete a LogTable entry by ID
    public void delete(int id) {
        logTableRepository.deleteById(id);
    }

    // Validate if a LogTable entry exists by ID
    public LogTable validate(int id) throws ResourceNotFoundException {
        Optional<LogTable> optional = logTableRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("LogTable ID invalid");
        }
        return optional.get();
    }

    // Method to find logs by entity ID
    public List<LogTable> getLogsByEntity(int entityId) {
        return logTableRepository.findLogsByEntity(entityId);
    }
    
    public List<LogTable> insertInBatch(List<LogTable> logTables) {
        return logTableRepository.saveAll(logTables);
    }


}
