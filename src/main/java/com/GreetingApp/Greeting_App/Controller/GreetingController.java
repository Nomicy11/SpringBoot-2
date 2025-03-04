package com.GreetingApp.Greeting_App.Controller;


import com.GreetingApp.Greeting_App.Service.GreetingService;
import org.springframework.web.bind.annotation.*;
import com.GreetingApp.Greeting_App.Entity.Greeting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;



@RestController
@RequestMapping("/greetings")
public class GreetingController {
    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    // ✅ GET method to retrieve all greetings
    @GetMapping
    public List<Greeting> getAllGreetings() {
        return greetingService.getAllGreetings();
    }

    // ✅ POST method to add a new greeting
    @PostMapping
    public Greeting createGreeting(@RequestBody Greeting greeting) {
        return greetingService.saveGreeting(greeting);
    }
}


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
