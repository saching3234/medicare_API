package com.to.resources;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.to.entities.Orders;
import com.to.services.OrdersService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin/userOrders")
public class AdminUserOrdersResource {
	
	@Autowired
	OrdersService ordersService;
	
	
	
	//getting the current user's order details
	@GetMapping("/getAllOrders")
	public List<Orders> getAllOrders(){
		return ordersService.;
	}

}


