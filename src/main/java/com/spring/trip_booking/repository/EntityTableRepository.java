package com.spring.trip_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.trip_booking.model.EntityTable;

import java.util.List;

public interface EntityTableRepository extends JpaRepository<EntityTable, Integer> {

    // Custom query to fetch entities by vendor ID
    @Query("SELECT e FROM EntityTable e WHERE e.vendor.id = :vendorId")
    List<EntityTable> findEntitiesByVendorId(@Param("vendorId") int vendorId);
}
