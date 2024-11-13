package com.trip_project.bus.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "routes")
public class Route {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int routeId;

    @Column(nullable = false)
    private String source;

	@Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private float distanceKm; // distance in kilometers
    

    // Foreign Key relationships
    // Assuming that Route may be associated with a Bus and/or Stop
    // Example: Many routes can be assigned to a particular Bus (Optional)

    
    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false) // Assuming each route has one bus
    private Bus bus;


    public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public float getDistanceKm() {
		return distanceKm;
	}

	public void setDistanceKm(float distanceKm) {
		this.distanceKm = distanceKm;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}
	
}
