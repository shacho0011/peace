package com.mohit.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/m")
public class UserRestController {
	
	@GetMapping("")
	public String getGrittings() {
		return "Hello Rokomari";
	}

}
