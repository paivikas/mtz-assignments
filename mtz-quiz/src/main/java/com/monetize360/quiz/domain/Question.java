package com.monetize360.quiz.domain;

import java.util.List;

import com.monetize360.quiz.service.CsvReaderUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Question {
    private long id;
    private String name;
    private List<String> options;
    private int answer;

}
