package com.example.demo.controller;

import java.util.List;

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
import com.example.demo.service.CategoryService;

@Controller
@RequestMapping(value = "/admin")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/categories")
	public String showCategoryList(@RequestParam(name = "page", required = false, defaultValue = "1") int pageNo,
			@RequestParam(name="sortField", required=false, defaultValue = "id") String sortField,
			@RequestParam(name="sortDir", required=false, defaultValue="asc" ) String sortDir,
			Model model) {
		int pageSize = 3;
		Page<Category> pageCategory = categoryService.findAll(pageNo, pageSize, sortField, sortDir);
		List<Category> categories = pageCategory.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", pageCategory.getTotalPages());
		model.addAttribute("totalItems", pageCategory.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("categories", categories);
		return "categories";
	}

	@GetMapping("/addcategory")
	public String showSignUpForm(Model model) {
		model.addAttribute("category", new Category());
		return "add-category";
	}

	@GetMapping("/editcategory")
	public String showEditForm(@RequestParam(name = "categoryId") Long id, Model model) {
		Category category = categoryService.findCategoryById(id);
		model.addAttribute("category", category);
		return "edit-category";
	}
	
	@GetMapping("/deletecategory")
	public String deleteCategory(@RequestParam(name = "categoryId") Long id) {
		categoryService.deleteCategory(id);
		return "redirect:/admin/categories";
	}

	@PostMapping("/addcategory")
	public String addCategory(@Valid Category category, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-category";
		}		
		categoryService.saveCategory(category);

		return "redirect:/admin/categories";
	}

	@PostMapping("/updatecategory")
	public String updateCategory(@RequestParam(name = "categoryId") Long id, @Valid Category category, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			category.setId(id);
			return "edit-category";
		}	
		categoryService.updateCategory(category, id);
		return "redirect:/admin/categories";
	}
}
