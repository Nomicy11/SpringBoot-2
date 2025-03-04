package com.GreetingApp.Greeting_App.Service;
import com.GreetingApp.Greeting_App.Entity.Greeting;
import com.GreetingApp.Greeting_App.Repository.GreetingRepository;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class GreetingService {
    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    // ✅ Add missing method to save a Greeting
    public Greeting saveGreeting(Greeting greeting) {
        return greetingRepository.save(greeting);
    }

    // ✅ Method to fetch all greetings
    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }
}
