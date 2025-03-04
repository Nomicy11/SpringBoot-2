package com.GreetingApp.Greeting_App.Service;

import com.GreetingApp.Greeting_App.DTO.AuthUserDTO;
import com.GreetingApp.Greeting_App.DTO.LoginRequestDTO;
import com.GreetingApp.Greeting_App.DTO.LoginResponseDTO;
import com.GreetingApp.Greeting_App.Entity.AuthUser;
import com.GreetingApp.Greeting_App.Repository.AuthUserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Autowired
    public AuthenticationService(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
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

        AuthUser savedUser = authUserRepository.save(authUser);

        // Send registration email
        emailService.sendRegistrationEmail(savedUser.getEmail(), savedUser.getFirstName());

        return savedUser;
    }

    public LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO) throws MessagingException {
        Optional<AuthUser> userOptional = authUserRepository.findByEmail(loginRequestDTO.getEmail());

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found!");
        }

        AuthUser user = userOptional.get();

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password!");
        }

        // Generate JWT token using Auth0
        String token = JWT.create()
                .withSubject(user.getEmail())
                .sign(Algorithm.HMAC256("your_secret_key"));

        // Send login notification email
        emailService.sendLoginNotification(user.getEmail(), user.getFirstName());

        return new LoginResponseDTO("Login successful!", token);
    }
}
