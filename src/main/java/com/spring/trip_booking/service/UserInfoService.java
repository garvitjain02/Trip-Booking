package com.spring.trip_booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.trip_booking.enums.Role;
import com.spring.trip_booking.exception.InvalidUsernameException;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.UserInfo;
import com.spring.trip_booking.repository.UserInfoRepository;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;
    
    @Autowired
    private BCryptPasswordEncoder passEncoder;

    public UserInfo insert(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    public List<UserInfo> getAllUsers() {
        return userInfoRepository.findAll();
    }

    public void delete(int id) {
        userInfoRepository.deleteById(id);
    }

    public UserInfo validate(int id) throws ResourceNotFoundException {
        Optional<UserInfo> optional = userInfoRepository.findById(id);
        if (optional.isEmpty())
            throw new ResourceNotFoundException("User ID invalid");

        return optional.get();
    }

	public UserInfo signUp(UserInfo user) throws InvalidUsernameException {
		Optional<UserInfo> optional = userInfoRepository.findByUsername(user.getUsername());
		if(optional.isPresent()) {
			throw new InvalidUsernameException("Username already in use");
		}
		
		//encrypt the password 
		String encryptedPass = passEncoder.encode(user.getPassword());
		user.setPassword(encryptedPass);
		
//		user.setRole(Role.CUSTOMER);
		
		return userInfoRepository.save(user);
	}

	public UserInfo updateUser(UserInfo user) throws InvalidUsernameException, ResourceNotFoundException {
		UserInfo userInfo = validate(user.getId());
		
		Optional<UserInfo> optional = userInfoRepository.findByUsername(user.getUsername());
		if(optional.isPresent()) {
			UserInfo temp = optional.get();
			if (temp.getId() != user.getId())
				throw new InvalidUsernameException("Username already in use");
		}
		
		if (!user.getPassword().isBlank()) {
			String encryptedPass = passEncoder.encode(user.getPassword());
			user.setPassword(encryptedPass);
		} else {
			user.setPassword(userInfo.getPassword());
		}
//		user.setRole(Role.CUSTOMER);
		
		userInfo.setFirstName(user.getFirstName());
		userInfo.setLastName(user.getLastName());
		userInfo.setEmail(user.getEmail());
		userInfo.setPhone(user.getPhone());
		userInfo.setDob(user.getDob());
		userInfo.setUsername(user.getUsername());
		userInfo.setPassword(user.getPassword());
		
		return userInfoRepository.save(userInfo); 
	}
}
