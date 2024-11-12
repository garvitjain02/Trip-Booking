package com.spring.trip_booking.service;

import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.RatingTable;
import com.spring.trip_booking.repository.RatingTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingTableService {

	@Autowired
    private RatingTableRepository ratingTableRepository;

    // Insert or update a RatingTable
    public RatingTable saveRating(RatingTable ratingTable) {
        return ratingTableRepository.save(ratingTable);
    }

    // Get all ratings
    public List<RatingTable> getAllRatings() {
        return ratingTableRepository.findAll();
    }

    // Get a rating by ID
    public RatingTable getRatingById(int id) throws ResourceNotFoundException {
        return ratingTableRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Rating ID " + id + " not found"));
    }

    // Delete a rating by ID
    public void deleteRating(int id) {
        ratingTableRepository.deleteById(id);
    }

    // Get ratings by entity ID
    public List<RatingTable> getRatingsByEntityId(int entityId) {
        return ratingTableRepository.findByEntityId(entityId);
    }

    // Get ratings by user ID
    public List<RatingTable> getRatingsByUserId(int userId) {
        return ratingTableRepository.findByUserId(userId);
    }
}

