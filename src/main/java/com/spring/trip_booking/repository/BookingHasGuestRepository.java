package com.spring.trip_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.trip_booking.model.BookingHasGuest;

public interface BookingHasGuestRepository extends JpaRepository<BookingHasGuest, Integer> {

}