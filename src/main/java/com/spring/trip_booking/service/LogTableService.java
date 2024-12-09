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
    /*public Page<LogTable> getAllLogTables(Pageable pageable) {
        return logTableRepository.findAll(pageable);
    }*/
  
    public List<LogTable> insertInBatch(List<LogTable> logTables) {
        return logTableRepository.saveAll(logTables);
    }

	public List<LogTable> getAllLogTable() {
		// TODO Auto-generated method stub
		return logTableRepository.findAll();
	}

	public List<LogTable> getUserLogsByUserId(int id) {
		// TODO Auto-generated method stub
		return logTableRepository.getUserLogsByUserId(id);
	}

	public Page<LogTable> pagedGetAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return logTableRepository.findAll(pageable);
	}


}
