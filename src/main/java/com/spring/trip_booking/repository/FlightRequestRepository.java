package com.spring.trip_booking.repository;

import com.spring.trip_booking.model.FlightRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FlightRequestRepository extends JpaRepository<FlightRequest, Integer> {

    // Custom method to find FlightRequests by Vendor ID
    List<FlightRequest> findByVendorId(int vendorId);
}
