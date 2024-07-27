package com.lernen.blogproject.service;

import com.lernen.blogproject.dto.LoginRequest;
import com.lernen.blogproject.dto.RegisterRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AuthService {
    public void signup(RegisterRequest registerRequest);

    public String login(LoginRequest loginRequest);

    public Optional<User> getCurrentUser();
}
