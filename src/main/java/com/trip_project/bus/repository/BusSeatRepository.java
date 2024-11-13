package com.trip_project.bus.repository;

import com.trip_project.bus.model.BusSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusSeatRepository extends JpaRepository<BusSeat, Integer> {

    // Custom query methods

    // Find bus seats by bus ID
    List<BusSeat> findByBusId(int busId);

    // Find a bus seat by seat number and bus ID
    BusSeat findBySeatNumberAndBusId(int seatNumber, int busId);
}
