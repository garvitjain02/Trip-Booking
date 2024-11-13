package com.trip_project.bus.service;

import com.trip_project.bus.exception.ResourceNotFoundException;
import com.trip_project.bus.exception.InvalidInputException;
import com.trip_project.bus.exception.DuplicateResourceException;
import com.trip_project.bus.model.User;
import com.trip_project.bus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create or Update a user
    public User saveUser(User user) throws DuplicateResourceException, InvalidInputException {
        // Validate input
        if (user.getEmail() == null || user.getPhoneNumber() == null || user.getRole() == null) {
            throw new InvalidInputException("Email, phone number, and role must not be null.");
        }

        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateResourceException("User already exists with email: " + user.getEmail());
        }

        // Check if phone number already exists
        if (userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent()) {
            throw new DuplicateResourceException("User already exists with phone number: " + user.getPhoneNumber());
        }

        return userRepository.save(user);
    }

    // Find all users
    public List<User> getAllUsers() throws ResourceNotFoundException {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No users found.");
        }
        return users;
    }

    // Find user by id
    public User getUserById(int id) throws ResourceNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
    }

    // Find user by email
    public User getUserByEmail(String email) throws ResourceNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }

    // Find user by phone number
    public User getUserByPhoneNumber(String phoneNumber) throws ResourceNotFoundException {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with phone number: " + phoneNumber));
    }

    // Find user by role
    public List<User> getUsersByRole(String role) throws ResourceNotFoundException {
        List<User> users = userRepository.findByRole(role)
                .stream().toList();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No users found with role: " + role);
        }
        return users;
    }

    // Delete a user by id
    public void deleteUser(int id) throws ResourceNotFoundException {
        // Check if user exists before deleting
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }
}
