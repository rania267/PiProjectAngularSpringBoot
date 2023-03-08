package com.example.pi_project.services;


import com.example.pi_project.entities.Departement;
import com.example.pi_project.repositories.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementServiceImp implements  DepartementService{
    @Autowired
    DepartementRepository departementRepository;
    @Override
    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    @Override
    public Departement addDepartement(Departement departement) {
        return departementRepository.save(departement);
    }

    @Override
    public Departement updateDepartement(Departement departement) {
        return departementRepository.save(departement);
    }

    @Override
    public void deleteDepartement(int idDepartement) {
        departementRepository.deleteById(idDepartement);
    }

}
