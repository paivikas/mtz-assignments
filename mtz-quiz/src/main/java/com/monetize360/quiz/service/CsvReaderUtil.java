package com.monetize360.quiz.service;

import com.monetize360.quiz.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class CsvReaderUtil {

    private CsvReaderUtil() {
    }

    public static List<Question> loadQuestion() {
        List<Question> qAndA = new ArrayList<>();

        try (InputStream inputStream = CsvReaderUtil.class.getResourceAsStream("/quiz.txt")) {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

                String line;
                int id = 1;

                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("#")) {
                        String questionText = reader.readLine();
                        List<String> options = Stream.generate(() -> {
                            try {
                                return reader.readLine();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }).limit(4).collect(Collectors.toList());

                        String answerLine = reader.readLine();
                        int answer = answerLine.charAt(answerLine.length() - 1) - '0';

                        Question question = new Question();
                        question.setId(id++);
                        question.setName(questionText);
                        question.setOptions(options);
                        question.setAnswer(answer);
                        qAndA.add(question);
                    }
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return qAndA;
    }
}
