package com.acmeplex.api.service;

import com.acmeplex.api.model.RegisteredUser;
import com.acmeplex.api.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisteredUserService {
    @Autowired
    private final RegisteredUserRepository registeredUserRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public Optional<RegisteredUser> findByEmail( String email) {
        return registeredUserRepository.findByEmail(email);
    }

    @Autowired
    public RegisteredUserService(RegisteredUserRepository registeredUserRepository) {
        this.registeredUserRepository = registeredUserRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public RegisteredUser createRegisteredUser(RegisteredUser user) {
        // Encrypt the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return registeredUserRepository.save(user);
    }


}
