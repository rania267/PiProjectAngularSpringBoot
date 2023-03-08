package com.example.pi_project.repositories;


import com.example.pi_project.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LocationRepository extends JpaRepository<Location,Integer> {
}
