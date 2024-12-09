package com.spring.trip_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.trip_booking.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Integer>{
	
	@Query("Select h from Hotel h where h.id=?1")
	Hotel getHotelById(int id);

}
