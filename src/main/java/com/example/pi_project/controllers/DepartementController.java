package com.example.pi_project.controllers;


import com.example.pi_project.entities.Departement;
import com.example.pi_project.services.DepartementServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DepartementController {

     DepartementServiceImp DepartementService;
    @GetMapping("/AllDepartements")
    @ResponseBody
    public List<Departement> getAllDepartement(){
        return DepartementService.getAllDepartements();
    }
    @PostMapping("/addDepartement")
    @ResponseBody
    public Departement addDepartement (@RequestBody Departement Departement) {
        return DepartementService.addDepartement(Departement);
    }
    @PutMapping("/updateDepartement")
    @ResponseBody
    public Departement updateDepartement(@RequestBody Departement Departement){
        return DepartementService.updateDepartement(Departement);
    }
    @DeleteMapping("/deleteDepartement/{id}")
    @ResponseBody
    public void deleteDepartement(@PathVariable int id){
        DepartementService.deleteDepartement(id);
    }
}
