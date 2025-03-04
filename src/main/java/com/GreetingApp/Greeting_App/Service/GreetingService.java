package com.GreetingApp.Greeting_App.Service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public String getGreetingMessage() {
        return "Hello World from GET";
    }
}
