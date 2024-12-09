package com.spring.trip_booking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.trip_booking.model.LogTable;
import com.spring.trip_booking.model.UserInfo;

public interface LogTableRepository extends JpaRepository<LogTable, Integer> {

	
	void deleteByUser(Optional<UserInfo> byUsername);

	@Query("Select l from LogTable l where l.user.id=?1")
	List<LogTable> getUserLogsByUserId(int id);

}
