package com.spring.trip_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.HotelHasPolicy;
import com.spring.trip_booking.model.Policy;

public interface HotelHasPolicyRepository extends JpaRepository<HotelHasPolicy, Integer> {

	List<HotelHasPolicy> findAllByHotel(Hotel hotel);

}
