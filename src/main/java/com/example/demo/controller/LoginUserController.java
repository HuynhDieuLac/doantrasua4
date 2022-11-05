package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginUserController {
	
	@GetMapping("/loginuser")
	public String loginUserPage()
	{
		return "/loginuser";
	}
}
