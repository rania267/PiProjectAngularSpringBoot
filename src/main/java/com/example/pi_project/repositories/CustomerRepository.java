package com.example.pi_project.repositories;

import com.example.pi_project.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository  extends JpaRepository<Customer,Integer> {
    @Query(value = "SELECT * FROM customer where score =(select MAX(score) from customer)", nativeQuery = true)
    List<Customer> getMaxScoree();






}
