package com.to.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.to.entities.User;
import com.to.exceptions.EtAuthException;
import com.to.exceptions.EtResourceNotFoundException;
import com.to.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	//getting the current user details
	public User getCurrentUserDetails(int uid) {
		return repository.findById(uid).orElse(null);
	}
	
	//method for saving the user details
	public User saveUser(User user) {
		//checking the mail id already used or not
		User user1=repository.findByEmail(user.getEmail());
		
		//if user already exist with the given email id return null
		if(user1!=null)
			throw new EtAuthException("Email already in use");
		
		//create the new user and return the save user details with the id.
		else {
			return repository.save(user);
		}
		
	}
	
	//method for checking the login credentials
	public User userLoginCheck(String emailId,String password) {
		//getting the user by email id
		User user1=repository.findByEmail(emailId);
		
		//if user present then checking the password
		if(user1!=null)
		{
			if(password.equals(user1.getPassword()))
			return user1;		  
		  //if password not matches throw an EtAtuException
			else 
				throw new EtAuthException("Invalid EmailId/Password ");
			
	 } 	
		
		//if user not found with the given email id the throw and exception
		else {
			throw new EtAuthException("Invalid EmailId/Password ");
		}		
	}

	//method for change the user details
	public User changeUserDetails(int currentUserId, User user) {
		User user1;
		//getting the current user details;
		user1=repository.findById(currentUserId).orElse(null);
		//updating with the new user details
		if(user1!=null) {
		 user1.setAddress(user.getAddress());
		 user1.setAge(user.getAge());
		 user1.setEmail(user.getEmail());
		 user1.setGender(user.getGender());
		 user1.setName(user.getName());
		 user1.setPhone(user.getPhone());
		 user1.setPincode(user.getPincode());
		//update the user
		 return repository.save(user1);
		}
		else {
			throw new EtResourceNotFoundException("User Does not exist ");
		}
	}
	
	//method for getting the all user details
	public List<User> getAllUsers(){
		return repository.findAll();
	}
	
}
