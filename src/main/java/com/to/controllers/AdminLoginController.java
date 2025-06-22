package com.to.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.to.Constants;
import com.to.entities.Admin;
import com.to.services.AdminLoginService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/admin")
public class AdminLoginController {

	@Autowired
	AdminLoginService adminLoginService;

	
	
	@PostMapping("/register")
	
	public ResponseEntity<?> registerAdmin(@RequestBody Admin admin){
		//checking the admin is already registered or not
		if(adminLoginService.isAdminAlreadyRegistered(admin.getAdminId())) {
			return new ResponseEntity<>("Admin already registered",HttpStatus.UNAUTHORIZED);
		}
		//registering the admin
		adminLoginService.registerAdmin(admin);
		
		return new ResponseEntity<>(generatJWTToken(admin),HttpStatus.OK);			
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> loginAdmin(@RequestBody Admin admin){
		Admin admin2=adminLoginService.adminLoginCheck(admin);
	   //returning the token
		return new ResponseEntity<>(generatJWTToken(admin2),HttpStatus.OK);		
	}
	
	
	//method for generating the token
	private Map<String, String> generatJWTToken(Admin admin){
		//getting the current time in milliseconds
		long timestamp=System.currentTimeMillis();
		
		//creating the tocken
		String tokenString=Jwts.builder().signWith(SignatureAlgorithm.HS256,Constants.API_SECRET_KEY_STRING)
				           .setIssuedAt(new Date(timestamp)).setExpiration(new Date(timestamp+Constants.TOKEN_VALIDITY))
				           .claim("adminId", admin.getAdminId())
				           .compact();
		
		//creating the new hash map object 
		Map<String,String> map=new HashMap<>();
		map.put("token", tokenString);
		map.put("adminId", admin.getAdminId());
		return map;		
	}

	
}
