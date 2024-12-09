package com.spring.trip_booking.service;
import com.spring.trip_booking.enums.ActivityType;
import com.spring.trip_booking.model.LogTable;
import com.spring.trip_booking.model.RatingTable;
import com.spring.trip_booking.repository.LogTableRepository;
import com.spring.trip_booking.repository.RatingTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingTableService {

	@Autowired
    private RatingTableRepository ratingTableRepository;
	
	@Autowired
	LogTableRepository logTableRepository;

    // Insert or update a RatingTable
    public RatingTable saveRating(RatingTable ratingTable) {
		LogTable logTable=new LogTable();
		logTable.setTimestamp(LocalDateTime.now());
		logTable.setActivityType(ActivityType.RATING);
		logTable.setUser(ratingTable.getUser());
		logTable.setActivityDesc("Rating added by user");
		logTableRepository.save(logTable);
        return ratingTableRepository.save(ratingTable);
    }

    public List<RatingTable> getAllRatings() {
        return ratingTableRepository.findAll();
    }

	public List<RatingTable> getByHotelId(int id) {
		// TODO Auto-generated method stub
		return ratingTableRepository.getByHotelId(id);
	}

	public List<RatingTable> getByFlightId(int id) {
		// TODO Auto-generated method stub
		return ratingTableRepository.getByFlightId(id);
	}
    
   
}

