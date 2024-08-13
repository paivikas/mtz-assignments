package com.monetize360.ipljdbc.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
public class PlayerDetails {
    private String name;
    private String role;
    private double  price;
}
