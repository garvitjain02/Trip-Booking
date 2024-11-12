package com.spring.trip_booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.trip_booking.dto.ResponseMessageDto;
import com.spring.trip_booking.exception.ResourceNotFoundException;
import com.spring.trip_booking.model.UserInfo;
import com.spring.trip_booking.service.UserInfoService;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/add")
    public UserInfo addUser(@RequestBody UserInfo userInfo) {
        return userInfoService.insert(userInfo);
    }

    @GetMapping("/all")
    public List<UserInfo> getAllUsers() {
        return userInfoService.getAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id, ResponseMessageDto dto) {
        try {
            userInfoService.validate(id);
            userInfoService.delete(id);
            dto.setMsg("User deleted");
            return ResponseEntity.ok(dto);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UserInfo newUserInfo, ResponseMessageDto dto) {
        try {
            UserInfo existingUserInfo = userInfoService.validate(id);
            if (newUserInfo.getUserName() != null)
                existingUserInfo.setUserName(newUserInfo.getUserName());
            if (newUserInfo.getPassword() != null)
                existingUserInfo.setPassword(newUserInfo.getPassword());
            if (newUserInfo.getFirstName() != null)
                existingUserInfo.setFirstName(newUserInfo.getFirstName());
            if (newUserInfo.getLastName() != null)
                existingUserInfo.setLastName(newUserInfo.getLastName());
            if (newUserInfo.getEmail() != null)
                existingUserInfo.setEmail(newUserInfo.getEmail());
            if (newUserInfo.getPhone() != null)
                existingUserInfo.setPhone(newUserInfo.getPhone());

            // Save updated user info
            existingUserInfo = userInfoService.insert(existingUserInfo);
            return ResponseEntity.ok(existingUserInfo);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }
}
