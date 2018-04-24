package com.ishant.springbootjwttoken.controller;

import com.ishant.springbootjwttoken.model.Token;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ishant.springbootjwttoken.model.JwtUser;
import com.ishant.springbootjwttoken.security.JwtGenerator;

@RestController
@RequestMapping("/token")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TokenController {


    private JwtGenerator jwtGenerator;

    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping
    public Token generate(@RequestBody final JwtUser jwtUser) {
        Token token = new Token();
        token.setToken(jwtGenerator.generate(jwtUser));
        return  token;

    }
}