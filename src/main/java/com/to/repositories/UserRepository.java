package com.to.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.to.entities.User;

public interface UserRepository extends JpaRepository<User,Integer> {

	public User findByEmail(String email);

}
