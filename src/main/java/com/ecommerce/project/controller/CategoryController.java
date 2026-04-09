package com.ecommerce.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import com.ecommerce.project.service.CategoryServiceImpl;

@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
//	@GetMapping("/api/public/getCategories")
	@RequestMapping(value = "/public/getCategories", method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getAllCategories(){
		List<Category> allCategory = categoryService.getAllCategory();
		return ResponseEntity.ok(allCategory);
	}
	
//	@PostMapping("/api/public/addCategories")
	@RequestMapping(value = "/public/addCategories", method = RequestMethod.POST)
	public ResponseEntity<String> createCategory(@RequestBody Category category){
		categoryService.createCategory(category);
		return ResponseEntity.ok("Category added successfully");
	}
	
//	@DeleteMapping("/api/admin/delCategories/{categoryId}")
	@RequestMapping(value = "/admin/delCategories/{categoryId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
		try{
		String status=categoryService.delCategory(categoryId);
//		return new ResponseEntity<>(status,HttpStatus.OK);
		
		return ResponseEntity.ok(status);
		}
		catch (ResponseStatusException e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getReason(),e.getStatusCode());
		}
	}
	
//	@PutMapping("/api/admin/updateCategories/{categoryId}")
	@RequestMapping(value = "/admin/updateCategories/{categoryId}",method = RequestMethod.PUT)
	public ResponseEntity<String> updateCategory(@RequestBody Category category, @PathVariable Long categoryId){
		try{
			Category status=categoryService.updateCategory(category,categoryId);
			return ResponseEntity.ok("category with "+categoryId+" updated");
			
		}
		catch (ResponseStatusException e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(),e.getStatusCode());
		}
		
	}
}
