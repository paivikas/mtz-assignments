package com.monetize.learning_spring_core.di;

import org.springframework.stereotype.Component;

@Component
public class BirthdayGreeting implements GreetingService{
    @Override
    public void greet(String userName) {
        System.out.println("Happy Birthday "+userName);
    }
    public void hi(){
        System.out.println("HI");
    }
}
