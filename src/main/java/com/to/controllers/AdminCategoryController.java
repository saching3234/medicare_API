package com.to.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.to.entities.Category;
import com.to.entities.Product;
import com.to.services.CategoryService;

@CrossOrigin(origins = "*")
@RestController 
@RequestMapping("/api/admin/category")
public class AdminCategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	//getting the all category
	@GetMapping("/getCat")
	public List<Category> getAllCategory() {
		return categoryService.getAllCat();
	}
	
	
	//getting the category by cid
	  @PostMapping("/getCatById")
	  public Category getCat(@RequestBody Category cat){
		  //System.out.println(cat.getCid());
		 // int id=Integer.parseInt(product.getPid());
	   	return  categoryService.getCat(cat.getCid());
	}
	
	
	@PostMapping("/saveCat")
	public Category saveCategory(@RequestBody Category cat) {
		
		
		return categoryService.saveCat(cat);
	}
	
	@PostMapping("/deleteCat")
	public void deleteCat(@RequestBody Category cat) {
		System.out.println(cat.toString());
		 categoryService.deleteCategory(cat);
		
	}
	
	@PutMapping("/updateCat")
	public Category updatCategory(@RequestBody Category cat) {
	       return categoryService.updateCat(cat);	
	}

}
