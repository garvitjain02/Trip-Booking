
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
				 	.requestMatchers(HttpMethod.GET, "/api/executive/hello").hasAuthority("EXECUTIVE") 
                    //for airport controller 
                    .requestMatchers(HttpMethod.POST, "/api/airports/add").hasAuthority("FLIGHT_VENDOR")
				.anyRequest().permitAll()
			)
			.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		 
		return http.build();
	}
}