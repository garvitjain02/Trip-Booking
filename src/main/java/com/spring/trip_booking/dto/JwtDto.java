package com.spring.trip_booking.dto;

import org.springframework.stereotype.Component;

@Component
public class JwtDto {

	private String username;
	private String token;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
