package com.spring.trip_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.trip_booking.model.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Integer> {

}
