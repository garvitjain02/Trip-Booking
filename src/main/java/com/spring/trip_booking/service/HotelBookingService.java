package com.spring.trip_booking.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.trip_booking.enums.ApprovalStatus;
import com.spring.trip_booking.enums.TransactionStatus;
import com.spring.trip_booking.enums.TransactionType;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.Hotel;
import com.spring.trip_booking.model.HotelBooking;
import com.spring.trip_booking.model.UserInfo;
import com.spring.trip_booking.repository.HotelBookingRepository;

@Service
public class HotelBookingService {

	@Autowired
	private HotelBookingRepository hotelBookingRepository;
	
	public HotelBooking validate (int bid) throws ResourceNotFoundException {
		Optional<HotelBooking> optional = hotelBookingRepository.findById(bid);
		if (optional.isEmpty())
			throw new ResourceNotFoundException("Invalid Booking Id");
		return optional.get();
	}

	public List<HotelBooking> getHotelApprovalRequests(Hotel hotel) {
		List<HotelBooking> list = hotelBookingRepository.findAllByHotel(hotel);
		List<HotelBooking> filteredList = list.parallelStream().filter(e -> e.getTransactionStatus() == TransactionStatus.SUCCESS)
								.filter(e -> e.getApprovalStatus() == ApprovalStatus.PENDING)
								.filter(e -> !e.getStartDate().isBefore(LocalDate.now()))
								.toList();
		
		return filteredList;
		
	}

	public List<HotelBooking> getAllBookingsByHotel(Hotel hotel) {
		return hotelBookingRepository.findAllByHotel(hotel);
	}

	public HotelBooking addBooking(HotelBooking booking) {
		booking.setTransactionType(TransactionType.NEFT);
		booking.setTransactionStatus(TransactionStatus.SUCCESS);
		booking.setApprovalStatus(ApprovalStatus.PENDING);
		
		
		
		return hotelBookingRepository.save(booking);
	}

	public List<HotelBooking> getHotelBookingByVendor(UserInfo user) {
		return hotelBookingRepository.getHotelBookingByVendor(user.getId());
	}

	public HotelBooking updateApprovalStatus(HotelBooking hotelBooking) {
		return hotelBookingRepository.save(hotelBooking);
	}
}
