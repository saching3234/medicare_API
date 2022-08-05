package com.to.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.to.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	
	public List<Cart> getCartsByuserId(int userId);
	
	

	public void deleteCartsByuserId(int userId);
}
