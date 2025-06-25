package com.opensoft.foodmart.services;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.opensoft.foodmart.domain.OnSaleItem;
import com.opensoft.foodmart.domain.Store;
import com.opensoft.foodmart.repository.OnSaleItemRepository;
import com.opensoft.foodmart.repository.ProductRepository;
import com.opensoft.foodmart.repository.StoreRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    
	public Object createProduct() {
		return null;
	}
	
	public Object getAllProducts(Pageable pageable) {
		return null;
	}
	
	
	
	
}
