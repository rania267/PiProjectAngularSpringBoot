package com.example.pi_project.controllers;

import com.example.pi_project.entities.Role;
import com.example.pi_project.services.IRoleService;
import com.example.pi_project.services.InvoiceService;
import com.example.pi_project.services.RoleService;
import org.springframework.web.bind.annotation.*;

public class RoleController {

    IRoleService piService;
    @PostMapping("/addRole")
    @ResponseBody
    public Role addRole (@RequestBody Role role) {
        return piService.addRole (role);
    }
    @PutMapping("/updateRole")
    @ResponseBody
    public Role updateRole(@RequestBody Role r) {
        return piService.updateRole(r);
    }
    @DeleteMapping("/deleteRole/{id}")
    @ResponseBody
    public void deleteRole(@PathVariable("id") Long id) {
        piService.deleteRole(id);
    }
}
