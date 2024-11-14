package com.spring.trip_booking.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.spring.trip_booking.enums.BusType;

@Entity
@Table(name = "bus_requests")
public class BusRequest {  // BusRequest M:1 UserInfo (Vendor)

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
    private String busName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BusType busType;

    @Column(nullable = false)
    private Integer totalSeats;

    @Column(nullable = false)
    private Double price;

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

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public BusType getBusType() {
        return busType;
    }

    public void setBusType(BusType busType) {
        this.busType = busType;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
