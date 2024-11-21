package com.acmeplex.api.controller;

import com.acmeplex.api.model.RegisteredUser;
import com.acmeplex.api.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200") // Allow Angular access
public class RegisteredUserController {

    @Autowired
    private RegisteredUserService registeredUserService;

    @GetMapping
    public List<RegisteredUser> getAllUsers() {
        return registeredUserService.getAllUsers();
    }

    @PostMapping
    public RegisteredUser createUser(@RequestBody RegisteredUser user) {
        return registeredUserService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        registeredUserService.deleteUser(id);
    }
}