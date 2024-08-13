package com.monetize360.contactapp.dto;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

public class ContactDto {
    private int id;
    private String name;
    private String email;
    private String dob;
    private String mobile;
}
