package com.to.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.to.entities.Admin;
import com.to.exceptions.EtAuthException;
import com.to.repositories.AdminLoginRepository;

@Service
public class AdminLoginService {

	@Autowired
	AdminLoginRepository adminLoginRepository;

   public Admin adminLoginCheck(Admin admin) {

		// getting the admin details from db
		Admin dbAdmin = adminLoginRepository.findByAdminId(admin.getAdminId());

		// if admin present then checking the password
		if (dbAdmin != null) {
			if (admin.getPassword().equals(dbAdmin.getPassword())) {
				return dbAdmin;
			}
			// if password not matched throw an exception
			else {
				throw new EtAuthException("Invalid Password ");
			}
		}
		// if user not found with the given email id the throw and exception
		else {
			throw new EtAuthException("Invalid Admin Id Enter Correct Admin Id ");
		}

	}

public boolean isAdminAlreadyRegistered(String adminId) {
	// checking if the admin is already registered or not
	Admin dbAdmin = adminLoginRepository.findByAdminId(adminId);	
	if (dbAdmin != null) {
		return true;
	}
	// if admin not found then returning false	
	return false;
}

public void registerAdmin(Admin admin) {
	
	// registering the admin
	admin.setId(null); // setting id to null to let the database generate it
	adminLoginRepository.save(admin);
	// if admin registered successfully then returning true
	if (adminLoginRepository.findByAdminId(admin.getAdminId()) != null) {
		return;
	}
	// if admin not registered then throw an exception
	else {
		throw new EtAuthException("Admin not registered");
	}
	
}

}
