package com.spring.trip_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.trip_booking.model.Amenity;
import com.spring.trip_booking.model.RoomHasAmenity;

public interface RoomHasAmenityRepository extends JpaRepository<RoomHasAmenity, Integer> {

	@Query("select ra "
			+ "from RoomHasAmenity ra "
			+ "join ra.room r "
			+ "join ra.amenity a "
			+ "join r.hotel h "
			+ "where h.id = ?1")
	List<RoomHasAmenity> getAllRoomsAndAmenitiesByHotel(int hid);

	@Query("select a "
			+ "from RoomHasAmenity ra "
			+ "join ra.room r "
			+ "join ra.amenity a "
			+ "where r.id = ?1")
	List<Amenity> getAmenitiesByRoom(int i);

}
