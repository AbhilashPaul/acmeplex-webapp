package com.acmeplex.api.service;

import com.acmeplex.api.model.RegisteredUser;
import com.acmeplex.api.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class RegisteredUserService {
    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    public List<RegisteredUser> getAllUsers() {
        return registeredUserRepository.findAll();
    }

    public RegisteredUser createUser(@RequestBody RegisteredUser user) {
        return registeredUserRepository.save(user);
    }

    public void deleteUser(@PathVariable Long id) {
        registeredUserRepository.deleteById(id);
    }
}
