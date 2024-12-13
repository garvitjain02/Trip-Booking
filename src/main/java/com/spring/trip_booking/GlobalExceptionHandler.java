package com.spring.trip_booking;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.exception.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	private ResponseMessageDto dto;
	
	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(ResourceNotFoundException.class)
	ResponseEntity<?> handleResourceNotFoundException (Exception e) {
		dto.setMessage(e.getMessage());
		logger.error("ResourceNotFoundException thrown " + dto.getMessage());
		return ResponseEntity.badRequest().body(dto);
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<?> handleIOException (Exception e) {
		dto.setMessage(e.getMessage());
		logger.error("IOException thrown " + dto.getMessage());
		return ResponseEntity.badRequest().body(dto);
	}
	
}
