package com.monetize360.student.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class Student {
    private String name;
    private String batch;
    private String completed;
    private String placed;
    private String qualification;
    private double score;

}
