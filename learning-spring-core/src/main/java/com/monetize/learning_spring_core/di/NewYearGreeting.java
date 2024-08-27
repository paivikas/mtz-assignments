package com.monetize.learning_spring_core.di;

import org.springframework.stereotype.Component;

@Component
public class NewYearGreeting implements GreetingService{
    @Override
    public void greet(String userName) {
        System.out.println("Happy New Year "+userName);
    }
}
