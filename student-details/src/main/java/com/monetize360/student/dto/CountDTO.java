package com.monetize360.student.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class CountDTO{
    private int placedCount;
    private int notPlacedCount;
}