package com.monetize360.contact_web_using_jwt.service;

import com.monetize360.contact_web_using_jwt.dto.UserDto;

import java.util.UUID;

public interface UserService {
    UserDto getUserById(UUID userId);
}
