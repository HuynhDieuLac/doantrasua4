package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Category;
import com.example.demo.entities.Food;
import com.example.demo.service.CategoryService;
import com.example.demo.service.FoodService;

@Controller
public class IndexController {
	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private CategoryService categoryService;
		
	@GetMapping("/about")
	public String aboutPage() {
		return "about";
	}
	
	@GetMapping("/contact")
	public String contactPage() {
		return "contact";
	}
	
	@GetMapping("/privacy")
	public String privacyPage() {
		return "privacy";
	}
	
	@GetMapping("/products")
	public String productsPage(@RequestParam(name = "page", required = false, defaultValue = "1") int pageNo,
			@RequestParam(name="sortField", required=false, defaultValue = "id") String sortField,
			@RequestParam(name="sortDir", required=false, defaultValue="asc" ) String sortDir,
			Model model) {
		int pageSize=12;
		Page<Food> pageFood = foodService.findAll(pageNo, pageSize, sortField, sortDir);
		List<Food> foods = pageFood.getContent();
		List<Category> categories = categoryService.getCategories();
		model.addAttribute("foods", foods);
		model.addAttribute("categories", categories);

		return "products";
	}
}
