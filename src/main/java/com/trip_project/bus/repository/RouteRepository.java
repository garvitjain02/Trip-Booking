package com.trip_project.bus.repository;

import com.trip_project.bus.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

    // Custom query methods

    // Find routes by source
    List<Route> findBySource(String source);

    // Find routes by destination
    List<Route> findByDestination(String destination);

    // Find routes by source and destination
    List<Route> findBySourceAndDestination(String source, String destination);

    // Find a route by bus id
    Optional<Route> findByBusId(int busId);
}
