package com.to.resources;
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
public class UserProductResource {
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
     	return  productService.getAllCatProducts(category.getCid());
  }  
  
 
  
}
