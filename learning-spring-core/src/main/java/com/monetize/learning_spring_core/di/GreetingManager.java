package com.monetize.learning_spring_core.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GreetingManager {
    private final GreetingService greetingService;

    public GreetingManager(@Qualifier("birthdayGreeting") GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public void greet(String username) {
        greetingService.greet(username);
    }
}