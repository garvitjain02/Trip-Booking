package com.spring.trip_booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RatingTable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
}
