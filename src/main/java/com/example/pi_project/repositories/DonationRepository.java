package com.example.pi_project.repositories;

import com.example.pi_project.entities.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation,Integer> {
    @Query(value = "SELECT * FROM donation WHERE type = :type", nativeQuery = true)
    List<Donation> getDonationByType(@Param("type") String type);

    @Query(value = "SELECT SUM(montant) FROM donation", nativeQuery = true)
    Double getTotalMontant();

    @Modifying
    @Query(value = "UPDATE donation SET affected = 1 WHERE id_donation = :id_donation", nativeQuery = true)
    void markDonationAsAffected(@Param("id_donation") int id_donation);
}