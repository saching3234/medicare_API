package com.to.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.to.entities.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{
	
	public List<Orders> getOrdersByuserId(int userId);
	

}
