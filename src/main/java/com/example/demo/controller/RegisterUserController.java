package com.example.demo.controller;

import java.security.Permission;
import java.security.Permissions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entities.RegisterUser;
import com.example.demo.service.PermissionService;

import org.springframework.ui.Model;

@Controller
@RequestMapping(value ="/", method = RequestMethod.POST)
public class RegisterUserController {

	@Autowired
	private PermissionService permissionService;
	
	@GetMapping("/registeruser")
	public String registerUserPage(Model model)
	{
		RegisterUser registerUser = new RegisterUser();
		model.addAttribute("registerUser", registerUser);
		return "registeruser";
	}
	
}
