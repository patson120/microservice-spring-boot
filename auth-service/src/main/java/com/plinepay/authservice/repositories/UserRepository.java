package com.plinepay.authservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plinepay.authservice.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String username);
    
}
