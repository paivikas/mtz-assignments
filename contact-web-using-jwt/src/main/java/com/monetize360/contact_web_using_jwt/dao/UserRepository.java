package com.monetize360.contact_web_using_jwt.dao;

import com.monetize360.contact_web_using_jwt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
