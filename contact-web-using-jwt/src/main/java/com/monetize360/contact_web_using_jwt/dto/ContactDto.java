package com.monetize360.contact_web_using_jwt.dto;

import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ContactDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private boolean deleted;
}
