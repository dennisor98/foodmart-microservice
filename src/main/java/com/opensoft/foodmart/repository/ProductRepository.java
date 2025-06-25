package com.opensoft.foodmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opensoft.foodmart.domain.Product;

public interface ProductRepository extends JpaRepository<Product,String>{

}
