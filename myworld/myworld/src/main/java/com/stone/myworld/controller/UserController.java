package com.stone.myworld.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("UserController")
@RequestMapping(value = "/user")
public class UserController{
	
	@PostMapping(value = "/login")
	public String login() {
		return null;
	}
}
