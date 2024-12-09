package com.spring.trip_booking.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.trip_booking.enums.ActivityType;
import com.spring.trip_booking.model.LogTable;
import com.spring.trip_booking.model.UserInfo;
import com.spring.trip_booking.repository.LogTableRepository;
import com.spring.trip_booking.repository.UserInfoRepository;

@Service
public class UserSecurityService implements UserDetailsService {

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	LogTableRepository logTableRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> optional = userInfoRepository.findByUsername(username);
		if (optional.isEmpty())
			throw new UsernameNotFoundException ("Invalid Username");
			
		UserInfo user = optional.get();
		
		LogTable logTable=new LogTable();
		logTable.setTimestamp(LocalDateTime.now());
		logTable.setActivityType(ActivityType.LOGIN);
		logTable.setActivityDesc("User Logged In up with username: "+user.getUsername()+" and role: "+user.getRole());
		logTable.setUser(user);
		logTableRepository.save(logTable);
		
		return user;
	}

}
