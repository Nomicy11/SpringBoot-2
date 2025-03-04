package com.GreetingApp.Greeting_App.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/register", "/h2-console/**").permitAll() // Allow register API and H2 Console
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())  // Disable CSRF for APIs
                .headers(headers -> headers.frameOptions(frame -> frame.disable())); // Allow H2 Console frame embedding

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
