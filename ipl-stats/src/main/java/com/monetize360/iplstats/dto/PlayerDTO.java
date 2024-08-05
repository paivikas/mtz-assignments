package com.monetize360.iplstats.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private String name;
    private long price;
    private String role;
}
