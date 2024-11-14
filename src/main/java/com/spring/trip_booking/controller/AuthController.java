package com.spring.trip_booking.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.trip_booking.JwtUtil;
import com.spring.trip_booking.dto.JwtDto;
import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.exception.InvalidUsernameException;
import com.spring.trip_booking.model.UserInfo;
import com.spring.trip_booking.service.UserInfoService;
import com.spring.trip_booking.service.UserSecurityService;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@GetMapping("/api/token")
	public ResponseEntity<?> getToken(@RequestBody UserInfo user, JwtDto dto) {
		try {
			Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
			authenticationManager.authenticate(auth);
			
			// Check if username is in DB
			user = (UserInfo) userSecurityService.loadUserByUsername(user.getUsername());
			
			String jwt = jwtUtil.generateToken(user.getUsername());
			
			dto.setUsername(user.getUsername());
			dto.setToken(jwt);
			
			return ResponseEntity.ok(dto);
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	

	@GetMapping("/api/hello")
	public String sayHello(Principal principal) {
		String user = "";
		if(principal == null) {
			user = "TEMP_USER";
		}
		else {
			user = principal.getName();
		}
		return "api accessed by: " + user;
	}
	
	@PostMapping("/auth/sign-up")
	public ResponseEntity<?> signUp(@RequestBody UserInfo user,ResponseMessageDto dto){
		try {
			return ResponseEntity.ok(userInfoService.signUp(user));
		} catch (InvalidUsernameException e) {
			dto.setMessage(e.getMessage());
			 return ResponseEntity.badRequest().body(dto);
		}
	}
}
