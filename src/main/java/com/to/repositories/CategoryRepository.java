package com.to.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.to.entities.Category;

public interface CategoryRepository  extends JpaRepository<Category,Integer>{

	
}
