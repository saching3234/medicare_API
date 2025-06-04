package com.to.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.to.entities.Product;
import com.to.entities.TempProduct;
import com.to.services.ProductService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/admin/products")
public class AdminProductResource {
	@Autowired
	ProductService productService;
	
	//End point for getting the all products details
	@GetMapping("/getProducts")
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	
	//End point for saving the product details
	@PostMapping("/saveProduct")
	public Product saveProduct(@RequestParam("file") MultipartFile file,@RequestParam("product") String prod) {
		Product p=null;
		try {
			//converting the product string details in the product entity
			p=new ObjectMapper().readValue(prod,Product.class);
			//saving the file 
			File saveFile=new ClassPathResource("static/images").getFile();
			Path path=Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
			System.out.println("File path is :"+path);
			Files.copy(file.getInputStream(),path,StandardCopyOption.REPLACE_EXISTING);		
			System.out.println("File Saved Successfully");
			
			//setting the file name into the db
			p.setImg_name("images/"+file.getOriginalFilename());
			
		}	catch (Exception e) {
			System.out.println("Error While Saving the file"+e.toString());
		}
		System.out.println(p.toString());
		System.out.println(file.getOriginalFilename());
		return productService.saveProduct(p);		
	}
	
	  //get the product details by pid
	  @PostMapping("/getProduct")
	  public Product getProduct(@RequestBody Product product){
		  System.out.println(product.getPid());
		 // int id=Integer.parseInt(product.getPid());
	   	return  productService.getProduct(product.getPid());
	}
	
	
	//End point for deleting the product
	@DeleteMapping("/deleteProduct")
	public String deleteProduct(@RequestBody Product product) {
		return productService.deleteProduct(product);
	}
	
	//End point for updating the product details
	@PutMapping("/updateProduct")
	public Product updateProduct(@RequestBody Product product) {
		return productService.updtaeProduct(product);
	}
	
	//End point for updating the product details when image is changed
		@PutMapping("/updateProductImg")
		public Product updateProductImg(@RequestParam("file") MultipartFile file,@RequestParam("product") String prod) {
			Product p=null;
			try {
				//converting the product string details in the product entity
				p=new ObjectMapper().readValue(prod,Product.class);
				//saving the file 
				File saveFile=new ClassPathResource("static/images").getFile();
				Path path=Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
				System.out.println("File path is :"+path);
				Files.copy(file.getInputStream(),path,StandardCopyOption.REPLACE_EXISTING);		
				System.out.println("File Saved Successfully");
				
				//setting the file name into the db
				p.setImg_name("images/"+file.getOriginalFilename());
				
			}	catch (Exception e) {
				System.out.println("Error While Saving the file"+e.toString());
			}
			System.out.println(p.toString());
			System.out.println(file.getOriginalFilename());
			//updating the details into the db and returning the updated product
			return productService.updtaeProduct(p);		
		}
	

}
