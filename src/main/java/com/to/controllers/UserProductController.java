package com.to.controllers;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.to.entities.Category;
import com.to.entities.Product;
import com.to.services.ProductService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users/products")
public class UserProductController {
@Autowired
ProductService productService;

  //get the all product details
  @GetMapping("/getAllProducts")
  public List<Product> getAllProducst(){
	  return productService.getAllProducts();
  }
  
  //get the products by category Id
  @PostMapping("/getCatProduct")
  public List<Product> getCatProducts(@RequestBody Category category){
	  List<Product> activeProducts =new ArrayList<Product>();
	  //getting the all products of selected category
     	 List<Product> products= productService.getAllCatProducts(category.getCid());
     	//filter out the active  products
     	 for(Product product:products) {
     		 if(product.isActive())
     		activeProducts.add(product);
     	 }
     	  
     	  return activeProducts;
      }  
  
  
  
//get the products by brandName
  @PostMapping("/getProductByBrandName")
  public List<Product> getProductByBrandName(@RequestBody Product product){ 
	  
	  System.out.println(product.getBrand());
	  
	 
	  List<Product> brandProducts =new ArrayList<Product>();
	  //getting the all products 
     	 List<Product> products= productService.getAllProducts();
     	//filter out the  products which are equal to the brand name provided
     	 for(Product tempproduct:products) {
     		 if(tempproduct.getBrand().equalsIgnoreCase(product.getBrand()))
     			brandProducts.add(tempproduct);
     	 }    	  
     	  
	  
	   return  brandProducts; 
	   
      }   
 
  
}
