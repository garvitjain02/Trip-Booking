package com.spring.trip_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.trip_booking.model.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    // Additional query methods, if necessary, can be added here
}

