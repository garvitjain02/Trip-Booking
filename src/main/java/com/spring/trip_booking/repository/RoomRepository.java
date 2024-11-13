package com.spring.trip_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {

	List<Room> findAllByHotel(Hotel hotel);

}
