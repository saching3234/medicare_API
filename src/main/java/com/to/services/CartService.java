package com.to.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.to.entities.Cart;
import com.to.repositories.CartRepository;

@Service
@Transactional
public class CartService {

	@Autowired
	CartRepository cartRepository;
	@Autowired
	EntityManager em;
	
	//method for find by By userId And pi
   public	Cart findByuserIdAndpid(int userId,int pid) {
		return null;
	}
	
	//method for getting the cart details as per userId given
	public List<Cart> getCartsByUserId(int userId){
		return cartRepository.getCartsByuserId(userId);
	}
	
	//method for saving the product into the cart
	public Cart saveProductIntoCart(Cart cart,int userId) {
		TypedQuery<Cart> query= em.createQuery("SELECT c FROM Cart c where pid="+cart.getPid()+" and user_id="+userId,Cart.class);
		try {
		Cart c=query.getSingleResult();
		System.out.println(c.toString());
		
		c.setQuantity(c.getQuantity()+cart.getQuantity());
		return cartRepository.save(c);			
			
		}catch (Exception e) {
		
		
			return cartRepository.save(cart);	
		}
		
	}
	
	//method for removing the specific product from cart
	public String deleteProductFromCart(int cartId) {
		cartRepository.deleteById(cartId);
		return "Product Removed from cart: "+cartId;
	}
	
	//updating the quantity of selected product	
	public Cart updateQty(Cart cart) {
		//getting the existing cart product
		Cart existingCart=cartRepository.findById(cart.getCartId()).orElse(null);
		existingCart.setQuantity(cart.getQuantity());
		//update Quantity
		return cartRepository.save(existingCart);		
	}
	
	//removing the all product from cart of the specific user to move the cart into order placed
	
	public String removeFromCart(int userId) {
		cartRepository.deleteCartsByuserId(userId);
		return "Cart Moved to oder Placed. Your oder is placed successfully";
	}	
	
	
}
