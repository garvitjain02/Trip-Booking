package com.spring.trip_booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.LogTable;
import com.spring.trip_booking.service.LogTableService;

@RestController
public class LogTableController {

    @Autowired
    private LogTableService logTableService;

    // Add a new LogTable entry
    @PostMapping("/log/add")
    public LogTable addLog(@RequestBody LogTable logTable) {
        return logTableService.insert(logTable);
    }

    // Get all LogTable entries
    @GetMapping("/log/all")
    public List<LogTable> getAllLogTables() {
        return logTableService.getAllLogTables();
    }

    // Delete a LogTable entry by ID
    @DeleteMapping("/log/delete/{id}")
    public ResponseEntity<?> deleteLog(@PathVariable int id, ResponseMessageDto dto) {
        try {
            logTableService.validate(id);
            logTableService.delete(id);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
        dto.setMsg("Log entry deleted");
        return ResponseEntity.ok(dto);
    }

    // Update a LogTable entry by ID
    @PutMapping("/log/update/{id}")
    public ResponseEntity<?> updateLog(@PathVariable int id, @RequestBody LogTable newLogTable, ResponseMessageDto dto) {
        try {
            LogTable existingLogTable = logTableService.validate(id);
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
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }

    // Get all logs by entity ID
    @GetMapping("/log/entity/{entityId}")
    public ResponseEntity<?> getLogsByEntity(@PathVariable int entityId) {
        try {
            List<LogTable> logs = logTableService.getLogsByEntity(entityId);
            return ResponseEntity.ok(logs);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving logs: " + e.getMessage());
        }
    }
    
    @PostMapping("/log/batch/add")
    public List<LogTable> batchInsert(@RequestBody List<LogTable> logTables) {
        return logTableService.insertInBatch(logTables);
    }
    
    
}
