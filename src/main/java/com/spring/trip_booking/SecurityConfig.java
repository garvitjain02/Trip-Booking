package com.spring.trip_booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.spring.trip_booking.service.UserSecurityService;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	BCryptPasswordEncoder passwordEncoder () {
		BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
		return passEncoder;
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider () {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userSecurityService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	@Bean
	AuthenticationManager authManager (AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean 
	SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
		http
			.csrf((csrf) -> csrf.disable())
			.authorizeHttpRequests(authorize -> authorize
					.requestMatchers(HttpMethod.GET, "/api/token").permitAll()
				 	.requestMatchers(HttpMethod.POST, "/auth/sign-up").permitAll() 
				 	.requestMatchers(HttpMethod.GET, "/api/hello").authenticated()
				 	.requestMatchers(HttpMethod.POST, "/hotel/add/{uid}/{lid}").hasAuthority("EXECUTIVE")
				 	.requestMatchers(HttpMethod.POST, "/booking/guests/{bid}/{gid}").hasAuthority("CUSTOMER")
				 	.requestMatchers(HttpMethod.GET, "/hotel/approval/requests/{hid}").hasAnyAuthority("EXECUTIVE", "HOTEL_VENDOR")
				 	.requestMatchers(HttpMethod.GET, "/bookings/all/{hid}").hasAnyAuthority("EXECUTIVE", "HOTEL_VENDOR")
				 	.requestMatchers(HttpMethod.POST, "/amenity/batch/add").hasAuthority("EXECUTIVE")
				 	.requestMatchers(HttpMethod.POST, "/hotel/policies/add").hasAnyAuthority("EXECUTIVE", "HOTEL_VENDOR")
				 	.requestMatchers(HttpMethod.POST, "/location/add").hasAuthority("EXECUTIVE")
				 	.requestMatchers(HttpMethod.POST, "/policy/add").hasAuthority("EXECUTIVE")
				 	.requestMatchers(HttpMethod.POST, "/policy/batch/add").hasAuthority("EXECUTIVE")
				 	.requestMatchers(HttpMethod.POST, "/room/amenities/add").hasAuthority("EXECUTIVE")
				 	.requestMatchers(HttpMethod.POST, "/rooms/batch/add/{hotelId}").hasAuthority("HOTEL_VENDOR")
				 	.requestMatchers(HttpMethod.POST, "/api/booking/add").hasAnyAuthority("CUSTOMER", "HOTEL_VENDOR")
				 	.anyRequest().permitAll()
			)
			.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		 
		return http.build();
	}
}
