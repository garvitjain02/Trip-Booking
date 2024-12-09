package com.spring.trip_booking.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hotel_requests")
public class HotelRequest {  // HotelRequest M:1 UserInfo (Vendor)

	
    // Default Constructor
   /* public HotelRequest() {
        super();
    }

    // Parameterized Constructor
    public HotelRequest(int id, UserInfo vendor, LocalDateTime requestDate, String hotelName, Integer numberOfRooms, String location) {
        super();
        this.id = id;
        this.vendor = vendor;
        this.requestDate = requestDate;
        this.hotelName = hotelName;
        this.numberOfRooms = numberOfRooms;
        this.location = location;
    }*/

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne  // Added cascade for owner
    private UserInfo vendor;

    @Column(nullable = false)
    private LocalDateTime requestDate;

    @Column(nullable = false)
    private String hotelName;

    @Column(nullable = false)
    private Integer numberOfRooms;

    @Column(nullable = false)
    private String location;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserInfo getVendor() {
        return vendor;
    }

    public void setVendor(UserInfo vendor) {
        this.vendor = vendor;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
