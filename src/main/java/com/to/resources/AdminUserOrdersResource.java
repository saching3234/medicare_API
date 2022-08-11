package com.to.resources;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.to.entities.DeliveryStatus;
import com.to.entities.Orders;
import com.to.entities.TempUser;
import com.to.entities.User;
import com.to.services.DeliveryStatusService;
import com.to.services.OrdersService;
import com.to.services.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin/userOrders")
public class AdminUserOrdersResource {
	
	@Autowired
	OrdersService ordersService;	
	@Autowired
	UserService userService;
	@Autowired
	DeliveryStatusService deliveryStatusService;
	//getting the current user's order details
	
	@GetMapping("/getAllDeliveryStatus")
	public List<DeliveryStatus> getAllDeliveryStatus(){
		return deliveryStatusService.getAllDeliveryStatus();
	}
	
	@PutMapping("/updateDeliveryStatus")
	public Orders updateDeliveryStatus(@RequestBody Orders order) {
		return ordersService.updateOrder(order);
	}
	
	
	@GetMapping("/getAllOrders")
	public List<Orders> getAllOrders(){
		return ordersService.getAllOrders();
	}
	
	//method for getting the order details by order id
	@GetMapping("/getOrderById/{oid}")
	public Orders getOrderById(@PathVariable int oid) {
		return ordersService.getOrderById(oid);
	}
		
	//method for getting the all users details
	@GetMapping("/getAllUsers")
	public List<TempUser> getAllUsers(){
		//getting the all users
		List<User> users;
		List<TempUser> tempUsers=new ArrayList<TempUser>();
		users=userService.getAllUsers();
		for (int i=0;i<users.size();i++) {
			TempUser tUser=new TempUser();			
			tUser.setUid(users.get(i).getUid());
			tUser.setName(users.get(i).getName());
			tempUsers.add(tUser);			
		}	  
	  return tempUsers;	
	}

}


