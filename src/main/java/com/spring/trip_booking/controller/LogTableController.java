package com.spring.trip_booking.controller;

import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.LogTable;
import com.spring.trip_booking.service.LogTableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log")
public class LogTableController {

    private static final Logger logger = LoggerFactory.getLogger(LogTableController.class);

    @Autowired
    private LogTableService logTableService;

    // Add a new LogTable entry
    @PostMapping("/add")
    public LogTable addLog(@RequestBody LogTable logTable) {
        logger.info("Adding new log entry: {}", logTable);
        return logTableService.insert(logTable);
    }

    // Get all LogTable entries with pagination
    @GetMapping("/all")
    public Page<LogTable> getAllLogTables(Pageable pageable) {
        logger.info("Fetching all log entries with pagination: {}", pageable);
        return logTableService.getAllLogTables(pageable);
    }

    // Delete a LogTable entry by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLog(@PathVariable int id, ResponseMessageDto dto) throws ResourceNotFoundException{
        logger.info("Deleting log entry with ID: {}", id);
        logTableService.validate(id); // Global exception will handle any errors
        logTableService.delete(id);
        dto.setMsg("Log entry deleted");
        return ResponseEntity.ok(dto);
    }

    // Update a LogTable entry by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateLog(@PathVariable int id, @RequestBody LogTable newLogTable) throws ResourceNotFoundException{
        logger.info("Updating log entry with ID: {}", id);
        LogTable existingLogTable = logTableService.validate(id);
        
        // Update fields if provided
        if (newLogTable.getActivityDesc() != null) {
            existingLogTable.setActivityDesc(newLogTable.getActivityDesc());
        }
        if (newLogTable.getEntity() != null) {
            existingLogTable.setEntity(newLogTable.getEntity());
        }
        if (newLogTable.getUser() != null) {
            existingLogTable.setUser(newLogTable.getUser());
        }
        if (newLogTable.getTimestamp() != null) {
            existingLogTable.setTimestamp(newLogTable.getTimestamp());
        }

        existingLogTable = logTableService.insert(existingLogTable);
        return ResponseEntity.ok(existingLogTable);
    }

    @GetMapping("/entity/{entityId}")
    public ResponseEntity<?> getLogsByEntity(@PathVariable int entityId) throws ResourceNotFoundException {
        logger.info("Fetching logs for entity ID: {}", entityId);
        List<LogTable> logs = logTableService.getLogsByEntity(entityId);
        return ResponseEntity.ok(logs);
    }

    // Batch insert logs
    @PostMapping("/batch/add")
    public List<LogTable> batchInsert(@RequestBody List<LogTable> logTables) {
        logger.info("Batch inserting log entries");
        return logTableService.insertInBatch(logTables);
    }
}
