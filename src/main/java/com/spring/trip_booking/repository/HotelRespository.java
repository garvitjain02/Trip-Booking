package com.spring.trip_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.trip_booking.model.Hotel;

public interface HotelRespository extends JpaRepository<Hotel,Integer>{
	
	@Query("Select h from Hotel h join h.owner o where o.username=?1")
	List<Hotel> getHotelsByVendor(String username);

}
