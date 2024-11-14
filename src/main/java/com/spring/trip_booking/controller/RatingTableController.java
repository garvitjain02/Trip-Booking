package com.spring.trip_booking.controller;

import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.RatingTable;
import com.spring.trip_booking.service.RatingTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/rating")
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
    public Page<RatingTable> getAllRatings(Pageable pageable) {
        logger.info("Fetching all ratings with pagination: {}", pageable);
        return ratingTableService.getAllRatings(pageable);
    }

    // Get a rating by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getRatingById(@PathVariable int id, ResponseMessageDto dto) throws ResourceNotFoundException{
        logger.info("Fetching rating by ID: {}", id);
        RatingTable rating = ratingTableService.getRatingById(id);
        return ResponseEntity.ok(rating);
    }

    // Update a rating by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRating(@PathVariable int id, @RequestBody RatingTable updatedRating) throws ResourceNotFoundException{
        logger.info("Updating rating with ID: {}", id);
        RatingTable rating = ratingTableService.getRatingById(id);
        rating.setRatingValue(updatedRating.getRatingValue());
        rating.setRatingDate(updatedRating.getRatingDate());
        rating.setFeedbackText(updatedRating.getFeedbackText());
        rating.setEntity(updatedRating.getEntity());
        rating.setUser(updatedRating.getUser());

        RatingTable updated = ratingTableService.saveRating(rating);
        return ResponseEntity.ok(updated);
    }

    // Delete a rating by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRating(@PathVariable int id, ResponseMessageDto dto) throws ResourceNotFoundException{
        logger.info("Deleting rating with ID: {}", id);
        ratingTableService.getRatingById(id);
        ratingTableService.deleteRating(id);
        dto.setMsg("Rating deleted successfully");
        return ResponseEntity.ok(dto);
    }

    // Get ratings by entity ID
    @GetMapping("/entity/{entityId}")
    public List<RatingTable> getRatingsByEntityId(@PathVariable int entityId) {
        logger.info("Fetching ratings for entity ID: {}", entityId);
        return ratingTableService.getRatingsByEntityId(entityId);
    }

    // Get ratings by user ID
    @GetMapping("/user/{userId}")
    public List<RatingTable> getRatingsByUserId(@PathVariable int userId) {
        logger.info("Fetching ratings for user ID: {}", userId);
        return ratingTableService.getRatingsByUserId(userId);
    }
}
