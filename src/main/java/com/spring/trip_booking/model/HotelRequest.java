package com.spring.trip_booking.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hotel_requests")
public class HotelRequest {  // HotelRequest M:1 UserInfo (Vendor)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private UserInfo vendor;

    @Lob
    private byte[] approvalDocs;

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

    public byte[] getApprovalDocs() {
        return approvalDocs;
    }

    public void setApprovalDocs(byte[] approvalDocs) {
        this.approvalDocs = approvalDocs;
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
