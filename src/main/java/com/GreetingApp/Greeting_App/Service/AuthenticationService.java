package com.GreetingApp.Greeting_App.Service;

import com.GreetingApp.Greeting_App.DTO.AuthUserDTO;
import com.GreetingApp.Greeting_App.Entity.AuthUser;
import com.GreetingApp.Greeting_App.Repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthenticationService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthUser registerUser(AuthUserDTO authUserDTO) {
        if (authUserRepository.findByEmail(authUserDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already in use.");
        }

        AuthUser authUser = new AuthUser();
        authUser.setFirstName(authUserDTO.getFirstName());
        authUser.setLastName(authUserDTO.getLastName());
        authUser.setEmail(authUserDTO.getEmail());
        authUser.setPassword(passwordEncoder.encode(authUserDTO.getPassword()));

        return authUserRepository.save(authUser);
    }
}
