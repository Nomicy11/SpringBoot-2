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


@RestController
@RequestMapping("/greetings")
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @PostMapping
    public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting newGreeting) {
        Greeting savedGreeting = greetingService.saveGreeting(newGreeting);
        URI location = URI.create(String.format("/greetings/%s", savedGreeting.getId()));
        return ResponseEntity.created(location).body(savedGreeting);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Greeting> getGreetingById(@PathVariable Long id) {
        return greetingService.findGreetingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Greeting> getAllGreetings() {
        return greetingService.findAllGreetings();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Greeting> updateGreeting(@PathVariable Long id, @RequestBody Greeting greetingDetails) {
        Greeting updatedGreeting = greetingService.updateGreeting(id, greetingDetails);
        return ResponseEntity.ok(updatedGreeting);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGreeting(@PathVariable Long id) {
        greetingService.deleteGreeting(id);
        return ResponseEntity.noContent().build();
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
