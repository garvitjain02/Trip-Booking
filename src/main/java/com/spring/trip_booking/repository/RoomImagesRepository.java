package com.spring.trip_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.trip_booking.model.Room;
import com.spring.trip_booking.model.RoomImages;

public interface RoomImagesRepository extends JpaRepository<RoomImages, Integer> {

	@Query("select ri "
			+ "from RoomImages ri "
			+ "join ri.room r "
			+ "join r.hotel h "
			+ "where h.id = ?1")
	List<RoomImages> getAllImagesByHotel(int id);

	List<RoomImages> findAllByRoom(Room r);

}
