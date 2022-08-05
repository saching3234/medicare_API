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

}
