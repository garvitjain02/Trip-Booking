package com.spring.trip_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.trip_booking.model.Guest;

public interface GuestRepository extends JpaRepository<Guest, Integer> {

}
