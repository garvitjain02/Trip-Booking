package com.spring.trip_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.trip_booking.model.Certificate;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate,Integer>{

	@Query("Select c from Certificate c where c.userInfo.id=?1")
	Certificate getCertificateByVendorId(int id);

}
