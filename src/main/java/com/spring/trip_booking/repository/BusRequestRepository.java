package com.spring.trip_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.trip_booking.model.BusRequest;
import java.util.List;

public interface BusRequestRepository extends JpaRepository<BusRequest, Integer> {
    // Custom method to find requests by vendor ID
    List<BusRequest> findByVendorId(int vendorId);
}
