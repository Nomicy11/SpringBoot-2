package com.GreetingApp.Greeting_App.Service;



import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public String getGreetingMessage(String firstName, String lastName) {
        if (firstName != null && !firstName.isBlank() && lastName != null && !lastName.isBlank()) {
            return String.format("Hello, %s %s!", firstName.trim(), lastName.trim());
        } else if (firstName != null && !firstName.isBlank()) {
            return String.format("Hello, %s!", firstName.trim());
        } else if (lastName != null && !lastName.isBlank()) {
            return String.format("Hello, %s!", lastName.trim());
        } else {
            return "Hello World!";
        }
    }
}
