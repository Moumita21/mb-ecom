package com.ecommerce.project.service;

import java.util.List;

import com.ecommerce.project.model.Category;

public interface CategoryService {
	List<Category> getAllCategory();
	void createCategory (Category category);
	String delCategory(Long categoryId);
	Category updateCategory(Category category,Long categoryId);

}
