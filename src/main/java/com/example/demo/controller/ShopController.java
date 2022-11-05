package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Category;
import com.example.demo.entities.Food;
import com.example.demo.service.CategoryService;
import com.example.demo.service.FoodService;

@Controller
public class ShopController {
	@Autowired
	private FoodService foodService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/")
	public String home(@RequestParam(name = "page", required = false, defaultValue = "1") int pageNo,
			@RequestParam(name = "sortField", required = false, defaultValue = "id") String sortField,
			@RequestParam(name = "sortDir", required = false, defaultValue = "asc") String sortDir, Model model) {

		int pageSize = 9;

		Page<Food> pageFood = this.foodService.findAll(pageNo, pageSize, sortField, sortDir);
		List<Food> foods = this.foodService.getAll();
		List<Category> categories = categoryService.getCategories();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", pageFood.getTotalPages());
		model.addAttribute("totalItems", pageFood.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("categories", categories);
		model.addAttribute("foods", foods);
		return "indexcart";
	}

	@RequestMapping(value = "/shop/foodInfo", method = RequestMethod.GET)
	public String getFood(Long foodId, HttpServletRequest request, Model model) {
		Food foods = foodService.get(foodId);

		model.addAttribute("foods", foods);
		return "food-information";

	}

	@GetMapping("/shop/viewCategory")
	public String showFoodCategory(@RequestParam(name = "page", required = false, defaultValue = "1") int pageNo,
			@RequestParam(name = "sortField", required = false, defaultValue = "id") String sortField,
			@RequestParam(name = "sortDir", required = false, defaultValue = "asc") String sortDir, Model model,
			@RequestParam(name = "categoryId") Long id, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("menuSelected", session);

		Category category = categoryService.findCategoryById(id);
		categoryService.findCategoryById(id);
		

		int pageSize = 12;

		Page<Food> pageFood = foodService.getFoodByCategoryId(pageNo, pageSize, sortField, sortDir, id);
		List<Food> foods = pageFood.getContent();
		List<Category> categories = categoryService.getCategories();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", pageFood.getTotalPages());
		model.addAttribute("totalItems", pageFood.getTotalElements());
		model.addAttribute("food2s", foods);
		model.addAttribute("category", category);
		model.addAttribute("categories", categories);

		return "view-category";
	}

}
