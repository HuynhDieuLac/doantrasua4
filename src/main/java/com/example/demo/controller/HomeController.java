package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("home")
public class HomeController {
	@GetMapping("/")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "Home") String name,
			Model model) {
		model.addAttribute("name", name);
		return "home";
	}
}
