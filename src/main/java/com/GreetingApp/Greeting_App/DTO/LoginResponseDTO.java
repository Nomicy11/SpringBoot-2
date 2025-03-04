package com.GreetingApp.Greeting_App.DTO;



public class LoginResponseDTO {
    private String message;
    private String token;

    public LoginResponseDTO(String message, String token) {
        this.message = message;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
}
