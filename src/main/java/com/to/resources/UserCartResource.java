package com.to.resources;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.to.entities.Cart;
import com.to.services.CartService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users/cart")
public class UserCartResource {
	
	@Autowired
	CartService cartService;
	
	@GetMapping("/getCartDetails")
	public List<Cart> getCartDetails(HttpServletRequest request){
		//getting the current user id to fecth all cart products 
		int userId=(Integer)request.getAttribute("userId");
		
		
		
		return cartService.getCartsByUserId(userId);		
	}
	
	
	//saving the product details into the card
	@PostMapping("/saveCart")
	public Cart saveCart(HttpServletRequest request,@RequestBody Cart cart ) {
		//getting the current user id to fecth all cart products 
		System.out.println(request.getAttribute("userId"));
		
		int userId=(Integer)request.getAttribute("userId");
		String userName=(String) request.getAttribute("userName");
		
		//setting the current user id and name
		cart.setUserId(userId);
		cart.setUserName(userName);
		//setting the quantity to one at first time
		cart.setQuantity(1);
		return cartService.saveProductIntoCart(cart,userId);
	//	return null;
	}
	
	//end point for removing the specified product from cart
	@DeleteMapping("/deleteFromCart/{cartID}")
	public void deleteFromCart(@PathVariable int cartID) {
		 cartService.deleteProductFromCart(cartID);
	}
	
	//end point for deleted all product from cart of specified user
	@DeleteMapping("/deleteAllProductsFromCart")
	public void deleteAllProductsFromCart(HttpServletRequest request) {
		//getting the current user id to fetch all cart products 
				int userId=(Integer)request.getAttribute("userId");
				 cartService.removeFromCart(userId);
				
				 // return "product deleted";
	}
	
	//end point for updating the quantity of existing product
	public Cart updateQuantity(HttpServletRequest request,@RequestBody Cart cart) {
		//getting the current user id to fecth all cart products 
		int userId=(Integer)request.getAttribute("userId");
		cart.setUserId(userId);
		return cartService.updateQty(cart);
	}

}
