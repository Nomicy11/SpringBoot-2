package com.GreetingApp.Greeting_App.Service;



import com.GreetingApp.Greeting_App.Entity.Greeting;
import com.GreetingApp.Greeting_App.Repository.GreetingRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class GreetingService {

    @Autowired
    private GreetingRepository greetingRepository;

    // CREATE a new greeting
    public Greeting createGreeting(Greeting greeting) {
        return greetingRepository.save(greeting);
    }

    // READ all greetings
    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }

    // READ a single greeting by ID
    public Greeting getGreetingById(Long id) {
        return greetingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Greeting not found"));
    }

    // UPDATE a greeting
    public Greeting updateGreeting(Long id, Greeting updatedGreeting) {
        Greeting existingGreeting = getGreetingById(id);
        existingGreeting.setMessage(updatedGreeting.getMessage());
        return greetingRepository.save(existingGreeting);
    }

    // DELETE a greeting
    public void deleteGreeting(Long id) {
        Greeting greeting = getGreetingById(id);
        greetingRepository.delete(greeting);
    }
}
