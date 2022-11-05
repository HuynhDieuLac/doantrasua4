package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Category;

import com.example.demo.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<String> getAllCategories(){
		List<Category> categories = categoryRepository.findAll();
		List<String> categoryList = new ArrayList<>();
		for (Category category : categories) {
			categoryList.add(category.getName());			
		}
		return categoryList;
	}
	
	public List<Category> getCategories() {
		return this.categoryRepository.findAll();
	}

	public Category saveCategory(Category category) {
		Category savedCategory = categoryRepository.save(category);
		return savedCategory;
	}

	public Category updateCategory(Category category, Long id) {
		Category currentCategory = findCategoryById(id);
		currentCategory.setName(category.getName());
		currentCategory.setTitle(category.getTitle());
		currentCategory.setDescription(category.getDescription());
		
		return categoryRepository.save(currentCategory);
	}

	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);
	}

	public Category findCategoryById(Long id) {
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		return optionalCategory.get();
	}

	public Category getByCategoryName(String categoryName) {
		return categoryRepository.findByCategoryName(categoryName);

	}

	public Page<Category> findAll(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		Page<Category> pageCategory = categoryRepository.findAll(pageable);
		return pageCategory;
	}
	
	public Category findByCategoryName(String Name)
	{
		return categoryRepository.findByCategoryName(Name);
	}
}
