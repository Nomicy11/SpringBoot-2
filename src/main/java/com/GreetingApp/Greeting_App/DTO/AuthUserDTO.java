package com.GreetingApp.Greeting_App.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data  // Lombok annotation for getters and setters
public class AuthUserDTO {


        @NotBlank(message = "First name cannot be blank")
        private String firstName;

        @NotBlank(message = "Last name cannot be blank")
        private String lastName;

        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Invalid email format")
        private String email;

        @NotBlank(message = "Password cannot be blank")
        private String password;

        // Getters and Setters (if not using Lombok)
    }
