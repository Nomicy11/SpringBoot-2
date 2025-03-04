package com.GreetingApp.Greeting_App.Service;



import com.GreetingApp.Greeting_App.Entity.Greeting;
import com.GreetingApp.Greeting_App.Repository.GreetingRepository;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public String getGreetingMessage(String firstName, String lastName) {
        String message;

        if (firstName != null && !firstName.isBlank() && lastName != null && !lastName.isBlank()) {
            message = "Hello, " + firstName.trim() + " " + lastName.trim() + "!";
        } else if (firstName != null && !firstName.isBlank()) {
            message = "Hello, " + firstName.trim() + "!";
        } else if (lastName != null && !lastName.isBlank()) {
            message = "Hello, " + lastName.trim() + "!";
        } else {
            message = "Hello World!";
        }

        // Save the greeting message to the database
        Greeting greeting = new Greeting(message);
        greetingRepository.save(greeting);

        return message;
    }
}
