package com.trip_project.bus.repository;

import com.trip_project.bus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Find a user by their email
    Optional<User> findByEmail(String email);

    // Find a user by their phone number
    Optional<User> findByPhoneNumber(String phoneNumber);

    // Find a user by their role (e.g., USER, ADMIN)
    Optional<User> findByRole(String role);
}
