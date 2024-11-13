package com.trip_project.bus.controller;

import com.trip_project.bus.exception.ResourceNotFoundException;
import com.trip_project.bus.exception.InvalidInputException;
import com.trip_project.bus.exception.DuplicateResourceException;
import com.trip_project.bus.model.Route;
import com.trip_project.bus.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    // Create or Update a route
    @PostMapping
    public ResponseEntity<Route> saveRoute(@RequestBody Route route) {
        try {
            Route savedRoute = routeService.saveRoute(route);
            return new ResponseEntity<>(savedRoute, HttpStatus.CREATED);
        } catch (DuplicateResourceException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } catch (InvalidInputException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Get all routes
    @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes() {
        try {
            List<Route> routes = routeService.getAllRoutes();
            return new ResponseEntity<>(routes, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Get route by ID
    @GetMapping("/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable("id") int routeId) {
        try {
            Route route = routeService.getRouteById(routeId);
            return new ResponseEntity<>(route, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Get routes by source
    @GetMapping("/source/{source}")
    public ResponseEntity<List<Route>> getRoutesBySource(@PathVariable("source") String source) {
        try {
            List<Route> routes = routeService.getRoutesBySource(source);
            return new ResponseEntity<>(routes, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Get routes by destination
    @GetMapping("/destination/{destination}")
    public ResponseEntity<List<Route>> getRoutesByDestination(@PathVariable("destination") String destination) {
        try {
            List<Route> routes = routeService.getRoutesByDestination(destination);
            return new ResponseEntity<>(routes, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Get routes by source and destination
    @GetMapping("/search")
    public ResponseEntity<List<Route>> getRoutesBySourceAndDestination(
            @RequestParam("source") String source,
            @RequestParam("destination") String destination) {
        try {
            List<Route> routes = routeService.getRoutesBySourceAndDestination(source, destination);
            return new ResponseEntity<>(routes, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Delete a route by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable("id") int routeId) {
        try {
            routeService.deleteRoute(routeId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
