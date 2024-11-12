package com.spring.trip_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.trip_booking.model.VendorPerformance;
import java.util.List;

public interface VendorPerformanceRepository extends JpaRepository<VendorPerformance, Integer> {
    // Custom method to find VendorPerformances by Vendor ID
    List<VendorPerformance> findByVendorId(int vendorId);
}
