package com.spring.trip_booking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.trip_booking.model.UserInfo;
import com.spring.trip_booking.repository.UserInfoRepository;

@Service
public class UserSecurityService implements UserDetailsService {

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> optional = userInfoRepository.findByUsername(username);
		if (optional.isEmpty())
			throw new UsernameNotFoundException ("Invalid Username");
			
		UserInfo user = optional.get();
		return user;
	}

}
