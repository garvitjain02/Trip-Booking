package com.spring.trip_booking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.trip_booking.model.Amenity;
import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.Location;
import com.spring.trip_booking.model.Policy;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

	List<Hotel> findByLocation(Location location);

	@Query("select h "
			+ "from HotelBooking hb "
			+ "join hb.hotel h "
			+ "where hb.endDate < :in or hb.startDate > :out")
	List<Hotel> getHotelsWithDates(@Param("in") LocalDate in, @Param("out") LocalDate out);

	@Query("select a "
			+ "from RoomHasAmenity ra "
			+ "join ra.room r "
			+ "join ra.amenity a "
			+ "join r.hotel h "
			+ "where h.id = ?1")
	List<Amenity> hotelHasAmenities(int hid);

}
