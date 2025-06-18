package com.to.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.to.entities.User;
import com.to.services.UserService;

import ch.qos.logback.classic.Logger;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users/change")
public class UserUpdateController {
	@Autowired
	UserService userService;
	
//	@Autowired
//	Logger logger;
	
	//method for getting the current user details
	@GetMapping("/getCurrentUser")
	public User getCurrentUserDetails(HttpServletRequest request){
		int currentUserId=(Integer)request.getAttribute("userId");		
		System.out.println("User Id in resource :"+currentUserId);
		return userService.getCurrentUserDetails(currentUserId);		
	}
	
	
	//method for updating the user details
	@PostMapping("/changeUserDetails")
	public User changeUserDetails(HttpServletRequest request,@RequestBody User user){
		int currentUserId=(Integer)request.getAttribute("userId");		
		System.out.println("User Id in resource :"+currentUserId);
		//logger.info("User Id in resource :"+currentUserId);
		return userService.changeUserDetails(currentUserId,user);		
	}

}
