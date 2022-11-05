package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Category;
import com.example.demo.entities.Food;
import com.example.demo.service.CategoryService;
import com.example.demo.service.FoodService;

@Controller
@RequestMapping(value = "/admin")
public class FoodController {

	@Autowired
	private FoodService foodService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/foods")
	public String showFoodList(@RequestParam(name = "page", required = false, defaultValue = "1") int pageNo,
			@RequestParam(name="sortField", required=false, defaultValue = "id") String sortField,
			@RequestParam(name="sortDir", required=false, defaultValue="asc" ) String sortDir,
			Model model) {
		int pageSize = 12;
		
		Page<Food> pageFood = foodService.findAll(pageNo, pageSize, sortField, sortDir);
		List<Food> foods = pageFood.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", pageFood.getTotalPages());
		model.addAttribute("totalItems", pageFood.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("foods", foods);
		return "foods";
	}
	
	@GetMapping("/addfood")
	public String showSignUpForm(Model model) {
		List<Category> categories = categoryService.getCategories();		
		model.addAttribute("food", new Food());
		model.addAttribute("categories", categories);
		return "add-food";
	}

	@GetMapping("/editfood")
	public String showEditForm(@RequestParam(name = "foodId") Long id, Model model) {
		List<Category> categories = categoryService.getCategories();
		Food food = foodService.findFoodById(id);
		model.addAttribute("food", food);
		model.addAttribute("categories", categories);
		return "edit-food";
	}
	
	@GetMapping("/deletefood")
	public String deleteUser(@RequestParam(name = "foodId") Long id) {
		foodService.deleteFood(id);
		return "redirect:/admin/foods";
	}

	@PostMapping("/addfood")
	public String addUser(@Valid Food food, BindingResult result, Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			return "add-food";
		}		
		String uploadRootPath = request.getServletContext().getRealPath("upload");
		System.out.println("uploadRootPath=" + uploadRootPath);	
		foodService.saveFood(food, uploadRootPath);

		return "redirect:/admin/foods";
	}

	@PostMapping("/updatefood")
	public String updateFood(@RequestParam(name = "foodId") Long id, @Valid Food food, BindingResult result,
			Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			food.setId(id);
			List<Category> categories = categoryService.getCategories();
			model.addAttribute("categories", categories);
			return "edit-food";
		}
		String uploadRootPath = request.getServletContext().getRealPath("upload");
		System.out.println("uploadRootPath=" + uploadRootPath);	
		foodService.updateFood(food, id, uploadRootPath);
		return "redirect:/admin/foods";
	}
}
