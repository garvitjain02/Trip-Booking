package com.spring.trip_booking.controller;

import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.RatingTable;
import com.spring.trip_booking.service.RatingTableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingTableController {
	
	@Autowired
    private RatingTableService ratingTableService;

    // Add a new rating
    @PostMapping("/add")
    public RatingTable addRating(@RequestBody RatingTable ratingTable) {
        return ratingTableService.saveRating(ratingTable);
    }

    // Get all ratings
    @GetMapping("/all")
    public List<RatingTable> getAllRatings() {
        return ratingTableService.getAllRatings();
    }

    // Get a rating by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getRatingById(@PathVariable int id, ResponseMessageDto dto) {
        try {
            RatingTable rating = ratingTableService.getRatingById(id);
            return ResponseEntity.ok(rating);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }

    // Update a rating by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRating(@PathVariable int id, @RequestBody RatingTable updatedRating, ResponseMessageDto dto) {
        try {
            RatingTable rating = ratingTableService.getRatingById(id);
            rating.setRatingValue(updatedRating.getRatingValue());
            rating.setRatingDate(updatedRating.getRatingDate());
            rating.setFeedbackText(updatedRating.getFeedbackText());
            rating.setEntity(updatedRating.getEntity());
            rating.setUser(updatedRating.getUser());

            RatingTable updated = ratingTableService.saveRating(rating);
            return ResponseEntity.ok(updated);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }

    // Delete a rating by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRating(@PathVariable int id, ResponseMessageDto dto) {
        try {
            ratingTableService.getRatingById(id);
            ratingTableService.deleteRating(id);
            dto.setMsg("Rating deleted successfully");
            return ResponseEntity.ok(dto);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }

    // Get ratings by entity ID
    @GetMapping("/entity/{entityId}")
    public List<RatingTable> getRatingsByEntityId(@PathVariable int entityId) {
        return ratingTableService.getRatingsByEntityId(entityId);
    }

    // Get ratings by user ID
    @GetMapping("/user/{userId}")
    public List<RatingTable> getRatingsByUserId(@PathVariable int userId) {
        return ratingTableService.getRatingsByUserId(userId);
    }
}
