package com.trip_project.bus.repository;

import com.trip_project.bus.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {
    // Custom query methods can be added here if needed

    // For example, find a bus by its bus number
    Bus findByBusNumber(String busNumber);
}
