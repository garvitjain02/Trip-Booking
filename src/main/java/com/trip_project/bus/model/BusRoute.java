package com.trip_project.bus.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "bus_routes")
public class BusRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int busRouteId;

    @Column(nullable = false)
    private LocalDateTime scheduledDeparture;

    @Column(nullable = false)
    private LocalDateTime scheduledArrival;

    @Column(nullable = false)
    private String status; // E.g., "Active", "Canceled"

    // Foreign key relationships
    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus; // Many BusRoutes can correspond to one Bus

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route; // Many BusRoutes can correspond to one Route

    // Getters and Setters
    public int getBusRouteId() {
        return busRouteId;
    }

    public void setBusRouteId(int busRouteId) {
        this.busRouteId = busRouteId;
    }

    public LocalDateTime getScheduledDeparture() {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(LocalDateTime scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }

    public LocalDateTime getScheduledArrival() {
        return scheduledArrival;
    }

    public void setScheduledArrival(LocalDateTime scheduledArrival) {
        this.scheduledArrival = scheduledArrival;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
