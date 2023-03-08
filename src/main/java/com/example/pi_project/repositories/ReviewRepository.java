package com.example.pi_project.repositories;

import com.example.pi_project.entities.CustomerReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<CustomerReview,Long> {
}
