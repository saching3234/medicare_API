package com.to.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.to.entities.Admin;

public interface AdminLoginRepository extends JpaRepository<Admin, Integer>{
	
	Admin findByAdminId(String AdminId);

}
