package com.lernen.blogproject.serviceImpl;

import com.lernen.blogproject.Security.JwtProvider;
import com.lernen.blogproject.dto.LoginRequest;
import com.lernen.blogproject.dto.RegisterRequest;
import com.lernen.blogproject.model.User;
import com.lernen.blogproject.repository.UserRepository;
import com.lernen.blogproject.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    private final JwtProvider jwtProvider;
    @Override
    public void signup(RegisterRequest request){
        User userRegistering = new User();
        userRegistering.setUserName(request.getUsername());
        userRegistering.setPassword(encodePassword(request.getPassword()));
        userRegistering.setEmail(request.getEmail());

        userRepository.save(userRegistering);
    }

    @Override
    public String login(LoginRequest loginRequest) {
        log.info("Login Creds : "+loginRequest.getUserName()+ " and "+loginRequest.getPassword());
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword());
        log.info("auth token :" + authToken.getCredentials());
        Authentication authenticate = authenticationManager.authenticate(authToken);
        log.info("Authentication of token: "+ authenticate.isAuthenticated());
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return jwtProvider.generateToken(authenticate);
    }

    @Override
    public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();

        return Optional.of(principal);
    }

    public String encodePassword(String password){
        return passwordEncoder.encode(password);
    }
}
