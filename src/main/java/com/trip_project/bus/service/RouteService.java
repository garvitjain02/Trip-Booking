package com.trip_project.bus.service;

import com.trip_project.bus.exception.ResourceNotFoundException;
import com.trip_project.bus.exception.InvalidInputException;
import com.trip_project.bus.exception.DuplicateResourceException;
import com.trip_project.bus.model.Route;
import com.trip_project.bus.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@Service
public class RouteService {

    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    // Create or Update a route
    public Route saveRoute(Route route) throws DuplicateResourceException, InvalidInputException {
        // Validate input
        if (route.getSource() == null || route.getDestination() == null || route.getDistanceKm() <= 0) {
            throw new InvalidInputException("Source, destination, and distance must be valid.");
        }

        // Check if route already exists with the same source and destination
        List<Route> existingRoutes = routeRepository.findBySourceAndDestination(route.getSource(), route.getDestination());
        if (!existingRoutes.isEmpty()) {
            throw new DuplicateResourceException("Route already exists with the same source and destination.");
        }

        return routeRepository.save(route);
    }

    // Get all routes
    public List<Route> getAllRoutes() throws ResourceNotFoundException {
        List<Route> routes = routeRepository.findAll();
        if (routes.isEmpty()) {
            throw new ResourceNotFoundException("No routes found.");
        }
        return routes;
    }

    // Get route by ID
    public Route getRouteById(int routeId) throws ResourceNotFoundException {
        return routeRepository.findById(routeId)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found with ID: " + routeId));
    }

    // Get routes by source
    public List<Route> getRoutesBySource(String source) throws ResourceNotFoundException {
        List<Route> routes = routeRepository.findBySource(source);
        if (routes.isEmpty()) {
            throw new ResourceNotFoundException("No routes found with source: " + source);
        }
        return routes;
    }

    // Get routes by destination
    public List<Route> getRoutesByDestination(String destination) throws ResourceNotFoundException {
        List<Route> routes = routeRepository.findByDestination(destination);
        if (routes.isEmpty()) {
            throw new ResourceNotFoundException("No routes found with destination: " + destination);
        }
        return routes;
    }

    // Get routes by source and destination
    public List<Route> getRoutesBySourceAndDestination(String source, String destination) throws ResourceNotFoundException {
        List<Route> routes = routeRepository.findBySourceAndDestination(source, destination);
        if (routes.isEmpty()) {
            throw new ResourceNotFoundException("No routes found from source: " + source + " to destination: " + destination);
        }
        return routes;
    }

    // Delete a route by ID
    public void deleteRoute(int routeId) throws ResourceNotFoundException {
        // Check if the route exists before deleting
        if (!routeRepository.existsById(routeId)) {
            throw new ResourceNotFoundException("Route not found with ID: " + routeId);
        }
        routeRepository.deleteById(routeId);
    }
}
