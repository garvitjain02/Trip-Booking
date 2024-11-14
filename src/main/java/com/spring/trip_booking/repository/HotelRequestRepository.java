package com.spring.trip_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.trip_booking.model.HotelRequest;
import java.util.List;

public interface HotelRequestRepository extends JpaRepository<HotelRequest, Integer> {
    // Custom method to find hotel requests by vendor ID
    List<HotelRequest> findByVendorId(int vendorId);
}
