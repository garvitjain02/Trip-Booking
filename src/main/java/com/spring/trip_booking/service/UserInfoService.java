package com.spring.trip_booking.service;

import java.nio.file.Path;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.trip_booking.enums.ActivityType;
import com.spring.trip_booking.enums.Role;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.Certificate;
import com.spring.trip_booking.model.LogTable;
import com.spring.trip_booking.model.UserInfo;
import com.spring.trip_booking.repository.CertificateRepository;
import com.spring.trip_booking.repository.LogTableRepository;
import com.spring.trip_booking.repository.UserInfoRepository;

import jakarta.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.spring.trip_booking.exception.InvalidUsernameException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;
    
    @Autowired
    private LogTableRepository logTableRepository;
    
    @Autowired
    private BCryptPasswordEncoder passEncoder;
    
    @Autowired
    CertificateRepository certificateRepository;
    
    public UserInfo insert(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    public Page<UserInfo> getAllUsers(Pageable pageable) {
        return userInfoRepository.findAll(pageable);
    }

    public void delete(int id) {
		LogTable logTable=new LogTable();
		logTable.setTimestamp(LocalDateTime.now());
		logTable.setActivityType(ActivityType.DELETE_VENDOR_REQUEST);
		logTable.setActivityDesc("Vendor request was deleted");
		logTableRepository.save(logTable);
        userInfoRepository.deleteById(id);
    }

    public UserInfo validate(int id) throws ResourceNotFoundException {
        Optional<UserInfo> optional = userInfoRepository.findById(id);
        if (optional.isEmpty())
            throw new ResourceNotFoundException("User ID invalid");

        return optional.get();
    }


    public List<UserInfo> getAllUsers() {
        return userInfoRepository.findAll();
    }


	public UserInfo signUp(UserInfo user) throws InvalidUsernameException {
		Optional<UserInfo> optional = userInfoRepository.findByUsername(user.getUsername());
		if(optional.isPresent()) {
			throw new InvalidUsernameException("Username already in use");
		}
		
		//encrypt the password 
		String encryptedPass = passEncoder.encode(user.getPassword());
		user.setPassword(encryptedPass);
		user.setVendor_approved("FALSE");
		
		user = userInfoRepository.save(user);

		LogTable logTable=new LogTable();
		logTable.setTimestamp(LocalDateTime.now());
		logTable.setActivityType(ActivityType.SIGN_UP);
		logTable.setActivityDesc("User Signed up with username: "+user.getUsername()+" and role: "+user.getRole());
		//logTable.setUser(user);
		logTableRepository.save(logTable);
		
		return user;
	}

	public List<UserInfo> getAllCustomers() {
		return userInfoRepository.getAllCustomers();
	}

    @Transactional
	public void deleteUsersByUsername(String username) {
		LogTable logTable=new LogTable();
		logTable.setTimestamp(LocalDateTime.now());
		logTable.setActivityType(ActivityType.DELETE_USER);
		logTable.setActivityDesc("User was deleted");
		logTableRepository.save(logTable);
        //logTableRepository.deleteByUser(userInfoRepository.findByUsername(username));
		userInfoRepository.deleteUsersByUsername(username);
	}

	public List<UserInfo> getAllVendors() {
		// TODO Auto-generated method stub
		return userInfoRepository.getAllVendors();
	}

	public Optional<UserInfo> getByUsername(String username) {
		// TODO Auto-generated method stub
		return userInfoRepository.findByUsername(username);
	}

	public List<UserInfo> getVendorRequests() {
		// TODO Auto-generated method stub
		return userInfoRepository.getVendorRequests();
	}

	public UserInfo approveVendorId(int id) {
		// TODO Auto-generated method stub
		UserInfo u=userInfoRepository.findByIdv(id);
		u.setVendor_approved("TRUE");
		return userInfoRepository.save(u);
	}

	public Certificate addCertificate(MultipartFile file, int id) throws IOException {
		// TODO Auto-generated method stub
		System.out.println(file.getOriginalFilename());
		String location = "C:\\Users\\Dhruv\\Desktop\\Angular Stuff\\my-trip-app\\public\\images";
		Path path = Path.of(location, file.getOriginalFilename()); 
		//System.out.println(path.toString());
		try {
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw e; 
		}

		Certificate pi = new Certificate();
		pi.setFileName(file.getOriginalFilename());
		pi.setPath(path.toString());
		pi.setUserInfo(userInfoRepository.findByIdv(id));
		return saveCertificate(pi);
	}

	private Certificate saveCertificate(Certificate pi) {
		// TODO Auto-generated method stub
		return certificateRepository.save(pi);
	}

	public List<Certificate> getAllCertificates() {
		// TODO Auto-generated method stub
		return certificateRepository.findAll();
	}

	public Certificate getCertificateByVendorId(int id) {
		// TODO Auto-generated method stub
		return certificateRepository.getCertificateByVendorId(id);
	}

}

