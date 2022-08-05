package com.to.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.to.entities.Category;
import com.to.services.CategoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users/category")
public class UserCatResource {
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/getCat")
	public List<Category> getAllCat() {
		return categoryService.getAllCat();
	}

}
