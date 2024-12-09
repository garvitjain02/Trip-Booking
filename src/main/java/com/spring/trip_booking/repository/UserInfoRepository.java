package com.spring.trip_booking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.trip_booking.enums.Role;
import com.spring.trip_booking.model.UserInfo;

import jakarta.transaction.Transactional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

     Optional<UserInfo> findByUsername(String username);

     @Query("Select c from UserInfo c where c.role='CUSTOMER'")
	List<UserInfo> getAllCustomers();
     
     @Modifying
     @Transactional
    @Query("Delete from UserInfo u where u.username=?1")
	void deleteUsersByUsername(String username);

    @Query("Select u from UserInfo u where u.role!='CUSTOMER' AND u.role!= 'EXECUTIVE' AND u.vendor_approved='TRUE'")
	List<UserInfo> getAllVendors();

    @Query("Select u from UserInfo u where u.role!='CUSTOMER' AND u.role!= 'EXECUTIVE' AND u.vendor_approved='FALSE'")
	List<UserInfo> getVendorRequests();

    @Query("Select u from UserInfo u where u.id=?1")
	UserInfo findByIdv(int id);
 }



