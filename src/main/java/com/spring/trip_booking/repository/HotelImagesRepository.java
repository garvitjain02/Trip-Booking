package com.spring.trip_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.HotelImages;

public interface HotelImagesRepository extends JpaRepository<HotelImages, Integer> {

	List<HotelImages> findAllByHotel(Hotel h);

}
