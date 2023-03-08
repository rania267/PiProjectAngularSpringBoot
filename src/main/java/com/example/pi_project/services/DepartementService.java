package com.example.pi_project.services;


import com.example.pi_project.entities.Departement;

import java.util.List;

public interface DepartementService {
    public List<Departement> getAllDepartements();

    public Departement addDepartement(Departement departement);


    public Departement updateDepartement(Departement departement);
    public void deleteDepartement(int idDepartement);
}
