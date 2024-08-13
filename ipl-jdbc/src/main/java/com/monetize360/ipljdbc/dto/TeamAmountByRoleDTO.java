package com.monetize360.ipljdbc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TeamAmountByRoleDTO {
    private String label;
    private String role;
    private double amount;
}
