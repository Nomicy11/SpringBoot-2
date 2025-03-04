package com.GreetingApp.Greeting_App.Service;
import com.GreetingApp.Greeting_App.Entity.Greeting;
import com.GreetingApp.Greeting_App.Repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class GreetingService {

    @Autowired
    private GreetingRepository greetingRepository;

    public Greeting saveGreeting(Greeting greeting) {
        return greetingRepository.save(greeting);
    }

    public Optional<Greeting> findGreetingById(Long id) {
        return greetingRepository.findById(id);
    }

    public List<Greeting> findAllGreetings() {
        return greetingRepository.findAll();
    }

    public Greeting updateGreeting(Long id, Greeting greetingDetails) {
        Greeting greeting = greetingRepository.findById(id).orElseThrow();
        greeting.setMessage(greetingDetails.getMessage());
        return greetingRepository.save(greeting);
    }

    public void deleteGreeting(Long id) {
        greetingRepository.deleteById(id);
    }
}
