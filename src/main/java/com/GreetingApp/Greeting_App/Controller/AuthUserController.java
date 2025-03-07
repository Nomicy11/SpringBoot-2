package com.GreetingApp.Greeting_App.Controller;

import com.GreetingApp.Greeting_App.DTO.AuthUserDTO;
import com.GreetingApp.Greeting_App.DTO.LoginRequestDTO;
import com.GreetingApp.Greeting_App.DTO.LoginResponseDTO;
import com.GreetingApp.Greeting_App.DTO.ResetPasswordDTO;
import com.GreetingApp.Greeting_App.Service.AuthenticationService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthUserController {


    private final AuthenticationService authenticationService;

    @Autowired
    public AuthUserController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody AuthUserDTO authUserDTO) {
        authenticationService.registerUser(authUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginRequestDTO loginRequestDTO) throws MessagingException {
        LoginResponseDTO response = authenticationService.loginUser(loginRequestDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/forgotPassword/{email}")
    public ResponseEntity<String> forgotPassword(@PathVariable String email, @RequestBody Map<String, String> request) {
        String newPassword = request.get("password");
        boolean isUpdated = authenticationService.forgotPassword(email, newPassword);
        if (!isUpdated) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sorry! We cannot find the user email: " + email);
        }
        return ResponseEntity.ok("Password has been changed successfully!");
    }

    @PutMapping("/resetPassword/{email}")
    public ResponseEntity<String> resetPassword(@PathVariable String email,
                                                @RequestBody ResetPasswordDTO resetPasswordDTO) {
        boolean isReset = authenticationService.resetPassword(
                email, resetPasswordDTO.getCurrentPassword(), resetPasswordDTO.getNewPassword());

        if (!isReset) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Current password is incorrect or user not found!");
        }
        return ResponseEntity.ok("Password reset successfully!");
    }


}
