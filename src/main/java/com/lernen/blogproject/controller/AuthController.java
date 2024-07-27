package com.lernen.blogproject.controller;

import com.lernen.blogproject.dto.LoginRequest;
import com.lernen.blogproject.dto.RegisterRequest;
import com.lernen.blogproject.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(method = RequestMethod.POST,path="/signup")
    public ResponseEntity signUp(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path="/login")
    public String logIn(@RequestBody LoginRequest loginRequest){

        return authService.login(loginRequest);
    }
}
