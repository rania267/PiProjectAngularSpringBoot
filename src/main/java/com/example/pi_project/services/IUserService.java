package com.example.pi_project.services;

import com.example.pi_project.entities.User;

import java.util.List;

public interface IUserService {

    User addUser(User u);

    User updateUser(User u);


    public void deleteUser(Long id);
    public List<User> getAllUsers();
}
