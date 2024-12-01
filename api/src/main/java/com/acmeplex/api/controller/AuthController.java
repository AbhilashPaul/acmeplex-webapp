package com.acmeplex.api.controller;

import com.acmeplex.api.model.RegisteredUser;
import com.acmeplex.api.notifier.EmailNotifier;
import com.acmeplex.api.service.RegisteredUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final RegisteredUserService registeredUserService;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, RegisteredUserService registeredUserService) {
        this.authenticationManager = authenticationManager;
        this.registeredUserService = registeredUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisteredUser> registerUser(@RequestBody RegisteredUser user) {
        if (registeredUserService.findByEmail(user.getEmail()).isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        try {
            user.setMemberSince(new Date());
            RegisteredUser savedUser = registeredUserService.createRegisteredUser(user);
            logger.debug("Successfully registered "+user.getEmail());
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error while registering user "+user.getEmail()+". Error: "+ e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Optional<RegisteredUser>> login(@RequestBody RegisteredUser user) throws ResponseStatusException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        } catch (Exception e) {
            logger.error("Error while logging in user "+user.getEmail()+". Error: "+ e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
        final Optional<RegisteredUser> registeredUser = registeredUserService.findByEmail(user.getEmail());
        logger.debug("Authenticated user "+user.getEmail());
        return new ResponseEntity<>(registeredUser, HttpStatus.OK);
    }
}
