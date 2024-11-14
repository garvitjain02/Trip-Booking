package com.spring.trip_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.trip_booking.enums.Role;
import com.spring.trip_booking.model.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

	@Query("select u from UserInfo u where u.role = :role")
	List<UserInfo> findByRole(@Param("role") Role role);

    @Query("SELECT u.id, u.userName, u.email, p.totalLogins, p.totalBookings, p.lastActivity " +
            "FROM UserInfo u " +
            "JOIN UserPerformance p ON u.id = p.user.id " +
            "WHERE u.role = :role")
     List<Object[]> findUsersWithPerformanceByRole(Role role);
 }



