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
					//user data entry, SIGNUP
					.requestMatchers(HttpMethod.GET, "/api/token").permitAll() // generates token after sign up
				 	.requestMatchers(HttpMethod.POST, "/auth/sign-up").permitAll() //singup, make an account
				 	.requestMatchers(HttpMethod.GET, "/api/hello").authenticated() // trial to see if it works
				 	.requestMatchers(HttpMethod.GET, "/api/executive/hello").hasAuthority("EXECUTIVE") //only executive account tokens can access this
				 	
				 	//userinfo controller
				 	.requestMatchers(HttpMethod.GET, "/user/all").hasAuthority("EXECUTIVE") 
				 	.requestMatchers(HttpMethod.GET, "/user/delete/{id}").hasAuthority("EXECUTIVE")
				 	.requestMatchers(HttpMethod.GET, "/user/update/{id}").authenticated()
				 	.requestMatchers(HttpMethod.GET, "/user/role/{role}").hasAuthority("EXECUTIVE") 
				 	.requestMatchers(HttpMethod.GET, "/user/roleperformance/{role}").hasAuthority("EXECUTIVE") 
				 	
				 /*	//busrequest controller
				 	.requestMatchers(HttpMethod.POST, "/bus-requests/add").permitAll()
				 	.requestMatchers(HttpMethod.GET, "/bus-requests/{id}").hasAuthority("EXECUTIVE") 
				 	.requestMatchers(HttpMethod.GET, "/bus-requests/all").hasAuthority("EXECUTIVE") 
				 	.requestMatchers(HttpMethod.DELETE, "/bus-requests/delete/{id}").hasAuthority("EXECUTIVE")
				 	.requestMatchers(HttpMethod.GET, "/bus-requests/vendor/{vendorId}").hasAuthority("EXECUTIVE")
				 	
				 	//entityperformance controller
				 	.requestMatchers(HttpMethod.POST, "/entity-performance/add").permitAll()
				 	.requestMatchers(HttpMethod.GET, "/entity-performance/all").hasAuthority("EXECUTIVE") 
				 	.requestMatchers(HttpMethod.GET, "/entity-performance/{id}").hasAuthority("EXECUTIVE") 
				 	.requestMatchers(HttpMethod.DELETE, "/entity-performance/delete/{id}").hasAuthority("EXECUTIVE")
				 	.requestMatchers(HttpMethod.GET, "/entity-performance/entity/{entityId}").hasAuthority("EXECUTIVE")
				 	
				 	//entitytable controller
				 	.requestMatchers(HttpMethod.POST, "/entity-performance/add").permitAll()
				 	.requestMatchers(HttpMethod.POST, "/entity-performance/batch/add").permitAll()
				 	.requestMatchers(HttpMethod.PUT, "/entity-performance/update/{id}").permitAll()
				 	.requestMatchers(HttpMethod.GET, "/entity-performance/all").hasAuthority("EXECUTIVE") 
				 	.requestMatchers(HttpMethod.GET, "/entity-performance/{id}").hasAuthority("EXECUTIVE") 
				 	.requestMatchers(HttpMethod.DELETE, "/entity-performance/delete/{id}").hasAuthority("EXECUTIVE")
				 	.requestMatchers(HttpMethod.GET, "/entity-performance/vendor/{vendorId}").hasAuthority("EXECUTIVE") 
				 	
				 	//flightrequest controller
				 	.requestMatchers(HttpMethod.POST, "/flight-requests/add").permitAll()
				 	.requestMatchers(HttpMethod.GET, "/flight-requests/{id}").hasAuthority("EXECUTIVE") 
				 	.requestMatchers(HttpMethod.GET, "/flight-requests/all").hasAuthority("EXECUTIVE") 
				 	.requestMatchers(HttpMethod.DELETE, "/flight-requests/delete/{id}").hasAuthority("EXECUTIVE")
				 	.requestMatchers(HttpMethod.GET, "/flight-requests/vendor/{vendorId}").hasAuthority("EXECUTIVE") */
				 	
				 	//hotelrequest controller
				 	
				.anyRequest().permitAll()
			)
			.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		 
		return http.build();
	}
}
