package com.example.pi_project.repositories;


import com.example.pi_project.entities.Forbiden;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ForbidenRepository extends CrudRepository<Forbiden, Integer> {

}