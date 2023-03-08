package com.example.pi_project.repositories;

import com.example.pi_project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    User findByToken(String token);
    Boolean existsByUsername(String username);
    User findByEmail(String email);
    Boolean existsByEmail(String email);
}
