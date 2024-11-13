package com.spring.trip_booking.dto;

import org.springframework.stereotype.Component;

@Component
public class ResponseMessageDto {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
