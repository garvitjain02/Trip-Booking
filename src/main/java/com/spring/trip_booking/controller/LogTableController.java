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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log")
@CrossOrigin(origins = {"http://localhost:4200"})
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
    /*@GetMapping("/all")
    public Page<LogTable> getAllLogTables(Pageable pageable) {
        logger.info("Fetching all log entries with pagination: {}", pageable);
        return logTableService.getAllLogTables(pageable);
    }*/
    
    @GetMapping("/all")
    public List<LogTable> getAllLogTable(){
    	return logTableService.getAllLogTable();
    }
    
    // Batch insert logs
    @PostMapping("/batch/add")
    public List<LogTable> batchInsert(@RequestBody List<LogTable> logTables) {
        logger.info("Batch inserting log entries");
        return logTableService.insertInBatch(logTables);
    }
    
    @GetMapping("/getUserLogsByUserId/{id}")
    public List<LogTable> getUserLogsByUserId(@PathVariable int id){
    	return logTableService.getUserLogsByUserId(id);
    }
    
    @GetMapping("/pagedall")
    public Page<LogTable> pagedGetAll(@RequestParam(required = false, defaultValue = "0") String page,  @RequestParam(required = false, defaultValue = "1000000") String size){
		Pageable pageable = null; 
		try {
			pageable =   PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
		}
		catch(Exception e) {
			throw e; 
		}
		Page<LogTable> list=logTableService.pagedGetAll(pageable);
		return list;

    }
    
}
