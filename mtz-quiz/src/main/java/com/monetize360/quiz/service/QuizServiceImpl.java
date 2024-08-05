package com.monetize360.quiz.service;

import com.monetize360.quiz.domain.Question;

import java.util.List;
import java.util.Scanner;

public class QuizServiceImpl implements QuizService{

    @Override
    public void startQuiz(String username) {
        int noOfCorrect=0;
        Scanner sc =new Scanner(System.in);
        System.out.println("Hello "+username+"! Welcome to the quiz!");
        List<Question> questions= CsvReaderUtil.loadQuestion();
        for (Question obj : questions) {
            System.out.println("Question:"+obj.getId());
            System.out.println(obj.getName());
            int opNo=1;
            for (String item : obj.getOptions()) {
                System.out.println((opNo++)+": "+item);
            }
            System.out.println("Enter your answer:");
            int userAns=sc.nextInt();
            if(userAns== obj.getAnswer())
            {
                System.out.println("Your answer is correct!");
                noOfCorrect++;
            }
            else {
                System.out.println("Your answer is incorrect");
                System.out.println("Correct option is:"+obj.getAnswer());
            }
        }
        System.out.println("Total no of correct answer is:"+noOfCorrect);
        System.out.println("Total no of incorrect answer is:"+(questions.size()-noOfCorrect));
        System.out.println("Thank You");
    }
}
