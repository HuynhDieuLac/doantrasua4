package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Info;
import com.example.demo.entities.Employee;
import com.example.demo.repository.EmployeeRepository;

@Controller
public class WebController {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/profile")
	public String profile(Model model) {
		List<Info> profile = new ArrayList<>();
		profile.add(new Info("fullname","David"));
		profile.add(new Info("nickname","david"));
		profile.add(new Info("gmail","david@gmail.com"));

		model.addAttribute("lodaProfile", profile);
		
		return "profile";
	}
	
	@GetMapping("/employee")
	public String employees(Model model) {
		List<Employee> employeeList = employeeRepository.findAll();
		model.addAttribute("employeeList", employeeList);
		
		return "employees";		
	}
}
