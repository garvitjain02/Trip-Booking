package com.spring.trip_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.trip_booking.model.Amenity;

public interface AmenityRepository extends JpaRepository<Amenity, Integer> {

	@Query("select distinct a "
			+ "from RoomHasAmenity ra "
			+ "join ra.amenity a "
			+ "join ra.room r "
			+ "join r.hotel h "
			+ "where h.id = ?1")
	List<Amenity> getAmenitiesByHotel(int hid);

}
