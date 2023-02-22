package com.example.pi_project.repositories;
import com.example.pi_project.entities.MessageRecorder;import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRecorderRepository extends JpaRepository<MessageRecorder,Integer> {
}
