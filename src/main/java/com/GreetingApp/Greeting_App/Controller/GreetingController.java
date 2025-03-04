package com.GreetingApp.Greeting_App.Controller;


import com.GreetingApp.Greeting_App.Service.GreetingService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.GreetingApp.Greeting_App.Entity.Greeting;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    // CREATE a new greeting
    @PostMapping
    public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting) {
        Greeting savedGreeting = greetingService.createGreeting(greeting);
        return new ResponseEntity<>(savedGreeting, HttpStatus.CREATED);
    }

    // READ all greetings
    @GetMapping
    public ResponseEntity<List<Greeting>> getAllGreetings() {
        List<Greeting> greetings = greetingService.getAllGreetings();
        return ResponseEntity.ok(greetings);
    }

    // READ a single greeting by ID
    @GetMapping("/{id}")
    public ResponseEntity<Greeting> getGreetingById(@PathVariable Long id) {
        Greeting greeting = greetingService.getGreetingById(id);
        return ResponseEntity.ok(greeting);
    }

    // UPDATE a greeting by ID
    @PutMapping("/{id}")
    public ResponseEntity<Greeting> updateGreeting(@PathVariable Long id, @RequestBody Greeting updatedGreeting) {
        Greeting updated = greetingService.updateGreeting(id, updatedGreeting);
        return ResponseEntity.ok(updated);
    }

    // DELETE a greeting by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGreeting(@PathVariable Long id) {
        greetingService.deleteGreeting(id);
        return ResponseEntity.noContent().build();
    }
}


//http://localhost:8080/greetings
//curl -X POST http://localhost:8080/greetings -H "Content-Type: application/json" -d "{\"message\": \"Hello, World!\"}"

//http://localhost:8080/greeting?firstName=Nomicy&lastName=Gupta
