package com.spring.trip_booking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.enums.Role;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.UserInfo;
import com.spring.trip_booking.service.UserInfoService;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @PostMapping("/add")
    public UserInfo addUser(@RequestBody UserInfo userInfo) {
        logger.info("Adding new user: {}", userInfo.getUsername());
        return userInfoService.insert(userInfo);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<UserInfo>> getAllUsers(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        logger.info("Fetching all users with pagination (page: {}, size: {})...", page, size);
        Page<UserInfo> usersPage = userInfoService.getAllUsers(pageable);
        return ResponseEntity.ok(usersPage);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id, ResponseMessageDto dto) throws ResourceNotFoundException {
        logger.info("Deleting user with ID: {}", id);
        userInfoService.validate(id);  // If validation fails, ResourceNotFoundException will be thrown
        userInfoService.delete(id);
        dto.setMsg("User deleted");
        logger.info("User deleted successfully with ID: {}", id);
        return ResponseEntity.ok(dto);
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
            if (newUserInfo.getFirstName() != null) {
                existingUserInfo.setFirstName(newUserInfo.getFirstName());
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

    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserInfo>> getUsersByRole(@PathVariable Role role) {
        logger.info("Fetching users with role: {}", role);
        List<UserInfo> users = userInfoService.getUsersByRole(role);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/roleperformance/{role}")
    public List<Object[]> getUsersWithPerformanceByRole(@PathVariable Role role) {
        return userInfoService.getUsersWithPerformanceByRole(role);
    }
}
