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

    public RatingTable saveRating(RatingTable ratingTable) {
        return ratingTableRepository.save(ratingTable);
    }

    public List<RatingTable> getAllRatings() {
        return ratingTableRepository.findAll();
    }

    public RatingTable getRatingById(int id) throws ResourceNotFoundException {
        return ratingTableRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Rating ID " + id + " not found"));
    }

    public void deleteRating(int id) {
        ratingTableRepository.deleteById(id);
    }

	public List<RatingTable> getAllRatingsByHotel(int hid) {
		return ratingTableRepository.getAllRatingsByHotel(hid);
	}
}
