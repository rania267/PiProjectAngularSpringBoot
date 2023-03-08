package com.example.pi_project.repositories;

import com.example.pi_project.entities.DeliveryRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRequestRepository extends JpaRepository<DeliveryRequest,Long> {


}
