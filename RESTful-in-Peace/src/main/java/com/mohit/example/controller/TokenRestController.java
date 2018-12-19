package com.mohit.example.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.example.model.security.JwtUser;
import com.mohit.example.security.JwtGenerator;


@RestController
@RequestMapping("/token")
public class TokenRestController {
	
	private JwtGenerator jwtGenerator;

    public TokenRestController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping
    public String generate(@RequestBody final JwtUser jwtUser) {
        return jwtGenerator.generate(jwtUser);
    }

}
