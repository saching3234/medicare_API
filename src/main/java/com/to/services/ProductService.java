package com.to.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.to.entities.Product;
import com.to.repositories.ProductRepository;
import java.util.List;


@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	//method for save the product details
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	
	//get All product details	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	//method for deleting the product
	public String deleteProduct(Product product) {
		productRepository.deleteById(product.getPid());
		return "Product deleted Id is :"+product.getPid();
	}
	
	
	//method for updating the product
	public Product updtaeProduct(Product product) {
		//finding the product 
		Product existingProduct=productRepository.findById(product.getPid()).orElse(null);
	    existingProduct.setActive(product.isActive());
	    existingProduct.setCid(product.getCid());
	    existingProduct.setAvailable_quantity(product.getAvailable_quantity());
	    existingProduct.setImg_name(product.getImg_name());
	    existingProduct.setPdescription(product.getPdescription());
	    existingProduct.setPname(product.getPname());
	    existingProduct.setPrice_per_unit(product.getPrice_per_unit());	 
	    existingProduct.setBrand(product.getBrand());
	    //save and return the updated product details
	    return productRepository.save(existingProduct);
	    
	    
	}

	public List<Product> getAllCatProducts(int cid) {
		// TODO Auto-generated method stub
		return productRepository.findBycid(cid);
	}
	
	//method for finding the product by id
	public Product getProduct(int pid) {
		// TODO Auto-generated method stub
		return productRepository.findById(pid).orElse(null);
	}
	

}
