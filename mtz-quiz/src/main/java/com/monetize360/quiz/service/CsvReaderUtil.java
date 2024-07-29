package com.monetize360.quiz.service;

import com.monetize360.quiz.domain.Question;

import java.io.*;
import java.util.*;

public final class CsvReaderUtil {

    private CsvReaderUtil() {

    }

    public static List<Question> loadQuestion() {
        List<Question> qAndA = new ArrayList<Question>();
        try {
            InputStream inputStream = CsvReaderUtil.class.getResourceAsStream("/quiz.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            int count = 0;
            String line;
            int id = 1;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#")) {
                    String questionText = reader.readLine();
                    List<String> options = new ArrayList<>();
                    for (int i = 0; i < 4; i++) {
                        options.add(reader.readLine());
                    }
                    String answerLine = reader.readLine();
                    int answer = answerLine.charAt(answerLine.length() - 1)-'0';

                    Question question = new Question();
                    question.setId(id++);
                    question.setName(questionText);
                    question.setOptions(options);
                    question.setAnswer(answer);
                    qAndA.add(question);
                }
            }

            return qAndA;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
