package com.to.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.to.entities.Orders;
import com.to.entities.Product;
import com.to.repositories.OrdersRepository;

@Service
public class OrdersService {
	@Autowired
	OrdersRepository ordersRepository;

	// method for updating the delivery status

	public Orders updateOrder(Orders order) {
		// finding the product
		Orders existingOrder = ordersRepository.findById(order.getOrderId()).orElse(null);
		long millis = System.currentTimeMillis();
		existingOrder.setDeliveryDate(new Date(millis));
		existingOrder.setDeliveryStatus(order.getDeliveryStatus());
		// updating the status and returning back the order
		return ordersRepository.save(existingOrder);
	}

	// method for saving the oder details into db
	public Orders saveOrder(Orders order) {
		return ordersRepository.save(order);
	}

	public List<Orders> saveOrders(List<Orders> orders) {
		return ordersRepository.saveAll(orders);
	}

	// getting the user's all orders
	public List<Orders> getCurrentUsersOrders(int userId) {
		return ordersRepository.getOrdersByuserId(userId);
	}

	// method for getting the all order
	// get All product details
	public List<Orders> getAllOrders() {
		return ordersRepository.findAll();
	}

	// method for getting order by id
	public Orders getOrderById(int oid) {
		return ordersRepository.findById(oid).orElse(null);
	}

}
