package com.example.pi_project.repositories;


import com.example.pi_project.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartementRepository extends JpaRepository<Departement,Integer> {
}
