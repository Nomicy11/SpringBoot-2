package com.GreetingApp.Greeting_App.Repository;


import com.GreetingApp.Greeting_App.Entity.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long> {
}
