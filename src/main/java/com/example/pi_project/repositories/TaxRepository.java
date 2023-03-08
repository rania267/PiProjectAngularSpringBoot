package com.example.pi_project.repositories;



import com.example.pi_project.entities.Tax;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaxRepository extends JpaRepository<Tax,Integer> {
}
