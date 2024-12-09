package com.spring.trip_booking.controller;
import com.spring.trip_booking.model.RatingTable;
import com.spring.trip_booking.service.RatingTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/rating")
@CrossOrigin(origins = {"http://localhost:4200"})
public class RatingTableController {
    
    private static final Logger logger = LoggerFactory.getLogger(RatingTableController.class);

    @Autowired
    private RatingTableService ratingTableService;

    // Add a new rating
    @PostMapping("/add")
    public RatingTable addRating(@RequestBody RatingTable ratingTable) {
        logger.info("Adding new rating: {}", ratingTable);
        return ratingTableService.saveRating(ratingTable);
    }

    // Get all ratings with pagination
    @GetMapping("/all")
    public List<RatingTable> getAllRatings() {
        return ratingTableService.getAllRatings();
    }
    
    @GetMapping("/getByHotelId/{id}")
    public List<RatingTable> getByHotelId(@PathVariable int id){
    	return ratingTableService.getByHotelId(id);
    }
    
    @GetMapping("/getByFlightId/{id}")
    public List<RatingTable> getByFlightId(@PathVariable int id){
    	return ratingTableService.getByFlightId(id);
    }
   
}
