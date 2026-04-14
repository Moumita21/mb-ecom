package com.ecommerce.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

//	private List<Category> categories=new ArrayList<>();
//	private Long nextId=1L;
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Override
	public List<Category> getAllCategory() {
//		return categories;
		List<Category> categories=categoryRepository.findAll();
		if(categories.isEmpty())
			throw new APIException("No Category Exists");
		
		return categories;
	}

	@Override
	public void createCategory(Category category) {
//		category.setCategoryId(nextId++);
//		categories.add(category);
		Category savedCategory=categoryRepository.findByCategoryName(category.getCategoryName());
		if(savedCategory!=null){
			throw new APIException("Category with category name "+category.getCategoryName()+" already exists.");
		}
		categoryRepository.save(category);
		
	}

	@Override
	public String delCategory(Long categoryId) {
//		Category delCategory=categories.stream().filter(c->c.getCategoryId().equals(categoryId)).findFirst()
//				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found"));
//		
//		categories.remove(delCategory);
//		return "Category with Id "+categoryId+" deleted successfully!!";
		
//		List<Category> categories=categoryRepository.findAll();
		
		Category delCategory=categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","CategoryId",categoryId));
		categoryRepository.delete(delCategory);
		return "Category with category Id: "+categoryId+" had been deleted";
		
//		Category delCategory=categories.stream().filter(c->c.getCategoryId().equals(categoryId)).findFirst()
//				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Response Not Found"));
//		
//		categoryRepository.delete(delCategory);
//		return "Categrory with category Id "+categoryId+" has been deleted!.";
	}

	
	@Override
	public Category updateCategory(Category category, Long categoryId) {
		// TODO Auto-generated method stub
//		Optional<Category> optionalCategory=categories.stream().filter(c->c.getCategoryId().equals(categoryId)).findFirst();
//		
//		if(optionalCategory.isPresent()){
//			Category existingCategory=optionalCategory.get();
//			existingCategory.setCategoryName(category.getCategoryName());
//			return existingCategory;
//		}
//	else {
//		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found");
//	}
	
	Optional<Category> updateC=categoryRepository.findById(categoryId);
	
	if(!updateC.isEmpty()){
		Category cc=updateC.get();
		cc.setCategoryName(category.getCategoryName());
		
		categoryRepository.save(cc);
	
	return cc;
	}
	else {
		throw new ResourceNotFoundException("Category","category Id",categoryId);
	}

	}

}
