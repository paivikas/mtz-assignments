package com.monetize360;

import com.monetize360.quiz.service.QuizServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello user!");
        System.out.println("Enter your name:");
        String name =sc.next();
        QuizServiceImpl quizService=new QuizServiceImpl();
        quizService.startQuiz(name);
        sc.close();
    }
}