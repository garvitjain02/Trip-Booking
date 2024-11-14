package com.spring.trip_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.trip_booking.model.VendorPerformance;
import java.util.List;

public interface VendorPerformanceRepository extends JpaRepository<VendorPerformance, Integer> {
    // Custom method to find VendorPerformances by Vendor ID
    List<VendorPerformance> findByVendorId(int vendorId);
    
    // Complex query to join VendorPerformance and FlightRequest by vendor (UserInfo)
    @Query("SELECT vp.id, vp.totalBookings, vp.averageRating, fr.id, fr.flightNumber, fr.airline, fr.origin, " +
           "fr.destination, fr.departureTime, fr.arrivalTime, fr.duration, fr.availableSeats, fr.price " +
           "FROM VendorPerformance vp " +
           "JOIN vp.vendor v " +
           "JOIN FlightRequest fr ON v.id = fr.vendor.id " +
           "WHERE v.id = :vendorId")
    List<Object[]> findVendorPerformanceWithFlightRequests(int vendorId);
}
