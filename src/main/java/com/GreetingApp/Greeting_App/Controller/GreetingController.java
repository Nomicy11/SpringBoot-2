package com.GreetingApp.Greeting_App.Controller;


import com.GreetingApp.Greeting_App.Service.GreetingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping
    public GreetingResponse getGreeting(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {

        String message = greetingService.getGreetingMessage(firstName, lastName);
        return new GreetingResponse(message);
    }

    public record GreetingResponse(String message) {}
}


//http://localhost:8080/greeting?firstName=Nomicy&lastName=Gupta
