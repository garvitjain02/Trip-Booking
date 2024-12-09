package com.spring.trip_booking.controller;

import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.HotelRequest;

import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.service.HotelRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/hotel-requests")
@CrossOrigin(origins = {"http://localhost:4200"})
public class HotelRequestController {

    private static final Logger logger = LoggerFactory.getLogger(HotelRequestController.class);

    @Autowired
    private HotelRequestService hotelRequestService;

    // Endpoint to add a new HotelRequest
    @PostMapping("/add")
    public HotelRequest addHotelRequest(@RequestBody HotelRequest hotelRequest) {
        logger.info("Adding new hotel request: {}", hotelRequest);
        return hotelRequestService.saveHotelRequest(hotelRequest);
    }

 // Endpoint to retrieve all HotelRequests without pagination
    @GetMapping("/all")
    public ResponseEntity<List<HotelRequest>> getAllHotelRequests() {
        logger.info("Fetching all hotel requests without pagination");
        List<HotelRequest> hotelRequests = hotelRequestService.getAllHotelRequests();
        return ResponseEntity.ok(hotelRequests);
    }


    // Endpoint to get a HotelRequest by ID
    @GetMapping("/{id}")
    public ResponseEntity<HotelRequest> getHotelRequestById(@PathVariable int id) throws ResourceNotFoundException{
        logger.info("Fetching hotel request with ID: {}", id);
        HotelRequest hotelRequest = hotelRequestService.getHotelRequestById(id);
        return ResponseEntity.ok(hotelRequest);
    }

    // Endpoint to delete a HotelRequest by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessageDto> deleteHotelRequestById(@PathVariable int id) throws ResourceNotFoundException{
        logger.info("Deleting hotel request with ID: {}", id);
        hotelRequestService.getHotelRequestById(id);
        hotelRequestService.deleteHotelRequestById(id);
        ResponseMessageDto dto = new ResponseMessageDto();
        dto.setMsg("Hotel request deleted successfully.");
        return ResponseEntity.ok(dto);
    }

    // Endpoint to get HotelRequests by Vendor ID
    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<HotelRequest>> getHotelRequestsByVendorId(@PathVariable int vendorId) {
        logger.info("Fetching hotel requests for vendor ID: {}", vendorId);
        List<HotelRequest> hotelRequests = hotelRequestService.getHotelRequestsByVendorId(vendorId);
        return ResponseEntity.ok(hotelRequests);
    }
    
    @PostMapping("/vendor-hotels-add")
    public Hotel vendorsHotelsAdd(@RequestBody Hotel hotel) {
		return hotelRequestService.vendorsHotelsAdd(hotel);
    }
    
    @GetMapping("/getHotelsByVendor/{username}")
	public List<Hotel> getHotelsByVendor(@PathVariable String username){
		return hotelRequestService.getHotelsByVendor(username);
	}
    
}
