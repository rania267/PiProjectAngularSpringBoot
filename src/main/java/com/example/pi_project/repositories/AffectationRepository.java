package com.example.pi_project.repositories;

import com.example.pi_project.entities.Affectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AffectationRepository extends JpaRepository<Affectation,Long> {


    @Modifying
    @Query(value = "INSERT INTO Affectation ( customer_customer_id, donation_id_donation) VALUES (:id,:id_donatin)", nativeQuery = true)
    void addAffectation( int id, int id_donatin);


}
