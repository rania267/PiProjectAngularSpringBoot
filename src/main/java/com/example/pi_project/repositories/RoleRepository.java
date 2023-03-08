package com.example.pi_project.repositories;

import com.example.pi_project.entities.ERole;
import com.example.pi_project.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
