package com.spring.trip_booking.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.trip_booking.enums.Role;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.UserInfo;
import com.spring.trip_booking.repository.UserInfoRepository;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public UserInfo insert(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    public Page<UserInfo> getAllUsers(Pageable pageable) {
        return userInfoRepository.findAll(pageable);
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

    public List<UserInfo> getUsersByRole(Role role) {
        return userInfoRepository.findByRole(role);
    }

    public List<Object[]> getUsersWithPerformanceByRole(Role role) {
        return userInfoRepository.findUsersWithPerformanceByRole(role);
    }

}
