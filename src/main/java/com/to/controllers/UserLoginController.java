package com.to.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.to.Constants;
import com.to.entities.User;
import com.to.services.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UserLoginController {
	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) {
		User user2 = userService.saveUser(user);
		return new ResponseEntity<>(generatJWTToken(user2), HttpStatus.OK);
	}
	@CrossOrigin(origins="*")
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap) {
		// Extracting the email id and password provided by user
		String emailId = (String) userMap.get("email");
		String password = (String) userMap.get("password");
		// checking the user email and password is correct or not

		User user = userService.userLoginCheck(emailId, password);
		// if we got the user then returning the token
		return new ResponseEntity<>(generatJWTToken(user), HttpStatus.OK);
	}
	

	
	
	
	
	
	
	
	
	

	// method for generating the token
	private Map<String, String> generatJWTToken(User user) {
		// getting the current time in milliseconds
		long timestamp = System.currentTimeMillis();

		// creating the token
		String tokenString = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY_STRING)
				.setIssuedAt(new Date(timestamp)).setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
				.claim("userId", user.getUid()).claim("email", user.getEmail()).claim("name", user.getName()).compact();
		// creating the new hash map object
		Map<String, String> map = new HashMap<>();
		map.put("token", tokenString);
		map.put("userName", user.getName());
		map.put("userId",String.valueOf(user.getUid()));
		return map;
	}
	
	
	

}
