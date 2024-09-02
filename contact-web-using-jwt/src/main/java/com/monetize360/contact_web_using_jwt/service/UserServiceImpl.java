package com.monetize360.contact_web_using_jwt.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monetize360.contact_web_using_jwt.dao.UserRepository;
import com.monetize360.contact_web_using_jwt.domain.User;
import com.monetize360.contact_web_using_jwt.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public UserDto getUserById(UUID userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + userId);
        }

        User user = userOptional.get();
        return objectMapper.convertValue(user, UserDto.class);
    }
}
