package com.acmeplex.api.controller;

import com.acmeplex.api.model.RegisteredUser;
import com.acmeplex.api.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private RegisteredUserService registeredUserService;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisteredUser> registerUser(@RequestBody RegisteredUser user) {
        if (registeredUserService.findByEmail(user.getEmail()).isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        try {
            user.setMemberSince(new Date());
            RegisteredUser savedUser = registeredUserService.createRegisteredUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Optional<RegisteredUser>> login(@RequestBody RegisteredUser user) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
        final Optional<RegisteredUser> registeredUser = registeredUserService.findByEmail(user.getEmail());
        return new ResponseEntity<>(registeredUser, HttpStatus.OK);
    }
}
