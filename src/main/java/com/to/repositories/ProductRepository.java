package com.to.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.to.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findBycid(int cid);
}
