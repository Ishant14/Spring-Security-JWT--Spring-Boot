package com.ishant.springbootjwttoken.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/hello")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HelloWorld {
	
	@GetMapping
	public String hello() {
		return "Hello World";
	}

}
