package com.GreetingApp.Greeting_App.Controller;


import com.GreetingApp.Greeting_App.Service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.GreetingApp.Greeting_App.Entity.Greeting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/greetings")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    // Create Greeting
    @PostMapping
    public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting) {
        return ResponseEntity.ok(greetingService.saveGreeting(greeting));
    }

    // Get Greeting by ID
    @GetMapping("/{id}")
    public ResponseEntity<Greeting> getGreeting(@PathVariable Long id) {
        Optional<Greeting> greeting = greetingService.getGreetingById(id);
        return greeting.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete Greeting by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGreeting(@PathVariable Long id) {
        boolean deleted = greetingService.deleteGreeting(id);
        if (deleted) {
            return ResponseEntity.ok("Greeting with ID " + id + " has been deleted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


//(POST) http://localhost:8080/greetings
// {
//        "message": "Hello, Nomicy!"
//  }

//(PUT) http://localhost:8080/greetings/1

// {
//        "message": "Hello, Sanjana!"
//  }

//(GET) http://localhost:8080/greetings
//
//        {
//        "id": 1,
//        "message": "Hello, Sanjana!"
//        }






//http://localhost:8080/greetings (POST, GET)

//[
//        {
//        "id": 1,
//        "message": "Hello, Spring Boot!"
//        },
//        {
//        "id": 2,
//        "message": "Hello, Nomicy!"
//        },
//        {
//        "id": 3,
//        "message": "Hello, Sanjana!"
//        }
//        ]



//http://localhost:8080/greetings
//curl -X POST http://localhost:8080/greetings -H "Content-Type: application/json" -d "{\"message\": \"Hello, World!\"}"

//http://localhost:8080/greeting?firstName=Nomicy&lastName=Gupta
