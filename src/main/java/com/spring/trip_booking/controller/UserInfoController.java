package com.spring.trip_booking.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.enums.Role;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.Certificate;
import com.spring.trip_booking.model.UserInfo;
import com.spring.trip_booking.service.UserInfoService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:4200"})
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @PostMapping("/add")
    public UserInfo addUser(@RequestBody UserInfo userInfo) {
        logger.info("Adding new user: {}", userInfo.getUsername());
        return userInfoService.insert(userInfo);
    }
    
    //using
    @GetMapping("/all")
    public List<UserInfo> getAllUsers(){
    	return userInfoService.getAllUsers();
    }
    
    //using
    @GetMapping("/byUsername/{username}")
    public Optional<UserInfo> getByUsername(@PathVariable String username){
    	return userInfoService.getByUsername(username);
    }
    
    //using
    @GetMapping("/allCustomers")
    public List<UserInfo> getAllCustomers(){
    	return userInfoService.getAllCustomers();
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UserInfo newUserInfo, ResponseMessageDto dto) {
        logger.info("Updating user with ID: {}", id);
        try {
            UserInfo existingUserInfo = userInfoService.validate(id);

            if (newUserInfo.getUsername() != null) {
                existingUserInfo.setUsername(newUserInfo.getUsername());
                logger.info("Updated userName for user with ID: {}", id);
            }
            if (newUserInfo.getPassword() != null) {
                existingUserInfo.setPassword(newUserInfo.getPassword());
                logger.info("Updated password for user with ID: {}", id);
            }
            if (newUserInfo.getName() != null) {
                existingUserInfo.setName(newUserInfo.getName());
                logger.info("Updated firstName for user with ID: {}", id);
            }
            if (newUserInfo.getLastName() != null) {
                existingUserInfo.setLastName(newUserInfo.getLastName());
                logger.info("Updated lastName for user with ID: {}", id);
            }
            if (newUserInfo.getEmail() != null) {
                existingUserInfo.setEmail(newUserInfo.getEmail());
                logger.info("Updated email for user with ID: {}", id);
            }
            if (newUserInfo.getPhone() != null) {
                existingUserInfo.setPhone(newUserInfo.getPhone());
                logger.info("Updated phone for user with ID: {}", id);
            }

            // Save updated user info
            existingUserInfo = userInfoService.insert(existingUserInfo);
            logger.info("User updated successfully with ID: {}", id);
            return ResponseEntity.ok(existingUserInfo);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            logger.error("Error updating user with ID {}: {}", id, e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }
    
    //using
    @DeleteMapping("/deleteuserbyusername/{username}")
    public void deleteUsersByUsername(@PathVariable String username) {
		userInfoService.deleteUsersByUsername(username);	
    }
    
    //using
    @GetMapping("/allVendors")
    public List<UserInfo> getAllVendors(){
    	return userInfoService.getAllVendors();
    }
    
    //using
    @GetMapping("/vendors-request")
    public List<UserInfo> getVendorRequests(){
    	return userInfoService.getVendorRequests();
    }
    
    //using- deleting vendor requests
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable int id) {
    	userInfoService.delete(id); 
    }
    
    //using
    /*@PostMapping("/approveVendor")
    public UserInfo approveVendor(@RequestBody UserInfo userInfo) {
		return userInfoService.insert(userInfo);
    }*/
    
    //using
    @PostMapping("/approveVendorId")
    public UserInfo approveVendorId(@RequestParam int id) {
    	return userInfoService.approveVendorId(id);
    }
    
    //trying for certificate
    @PostMapping("/addCertificate/{id}")
    public Certificate addCertificate(@RequestParam MultipartFile file, @PathVariable int id) throws IOException {
    	return userInfoService.addCertificate(file,id);
    }
    
    @GetMapping("/getCertificate")
    public List<Certificate> getCertificate(){
    	return userInfoService.getAllCertificates();
    }
    
    @GetMapping("/getCertificateByVendorId/{id}")
    public Certificate getCertificateByVendorId(@PathVariable int id){
    	return userInfoService.getCertificateByVendorId(id);
    }
    
}
