package com.monetize.learning_spring_core.di;

import org.springframework.stereotype.Component;

@Component
public class SeasonalGreeting implements GreetingService{
    @Override
    public void greet(String userName) {
        System.out.println("Happy Christmas "+userName);
    }
}
