package com.monetize.learning_spring_core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GreetingManager{
    private GreetingService greetingService;

    @Autowired
    public GreetingManager(@Qualifier("birthdayGreetings") GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public void greet(String username) {
        greetingService.greet(username);
    }
}