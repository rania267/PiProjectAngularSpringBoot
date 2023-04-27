package com.example.pi_project.services;

import com.example.pi_project.entities.Role;

public interface IRoleService {

    Role addRole(Role role);


    Role updateRole(Role r);

    void deleteRole(Long id);
}
