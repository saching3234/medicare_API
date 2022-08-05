package com.to.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.to.entities.Category;
import com.to.entities.Product;
import com.to.exceptions.EtBadRequestException;
import com.to.repositories.CategoryRepository;
import com.to.repositories.ProductRepository;


@Service
public class CategoryService {

	@Autowired
    private CategoryRepository categoryRepository;
	@Autowired
	private ProductService productService;
	
	public Category saveCat(Category category) {
		return categoryRepository.save(category);
	}
	
	public List<Category> getAllCat() {
		return categoryRepository.findAll();
	}
	
	public Category getCat(int cid) {
		return categoryRepository.findById(cid).orElse(null);
	}
	
	public Category updateCat(Category cat) {
		//get the existing category first from db
		Category existCategory=categoryRepository.findById(cat.getCid()).orElse(null);
		//set the new value to the existing category
		existCategory.setCname(cat.getCname());
		existCategory.setActive(cat.isActive());
		//update to the db and return the updated category		
		return categoryRepository.save(existCategory);
	}
	
	//method for getting the category by Id
	
	public void deleteCategory(Category cat) {
		
		//checking the category is already  in use or not in product table
		
		List <Product> product=productService.getAllCatProducts(cat.getCid());
	   // System.out.println(product);
		if(product.size()>0)
		throw new EtBadRequestException("Category in use can not detele it now");
		else 
		categoryRepository.deleteById(cat.getCid());	 
		 
	}
	
	
}
