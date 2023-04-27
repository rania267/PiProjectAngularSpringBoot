package com.example.pi_project.controllers;

import com.example.pi_project.entities.User;
import com.example.pi_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private UserService userService;

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email) {

        String response = userService.forgotPassword(email);

        if (!response.startsWith("Invalid")) {
            response = "http://localhost:8081/reset-password?token=" + response;
        }
        return response;
    }

    @PutMapping("/reset-password")
    public String resetPassword(@RequestParam String token,
                                @RequestParam String password) {

        return userService.resetPassword(token, encoder.encode(password));
    }

    @Autowired
    private UserService iPiService;

    @PostMapping("/addUser")
    @ResponseBody
    public User addUser(@RequestBody User u) {
        return iPiService.addUser(u);
    }

    @PutMapping("/updateUser")
    @ResponseBody
    public User updateUser(@RequestBody User u) {
        return iPiService.updateUser(u);
    }

    @DeleteMapping("/deleteUser/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable("id") Long id) {
        iPiService.deleteUser(id);
    }

    @GetMapping("/AllUsers")
    @ResponseBody
    public List<User> getAllUsers() {
        return iPiService.getAllUsers();
    }}


