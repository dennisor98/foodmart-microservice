package com.opensoft.foodmart.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.opensoft.foodmart.domain.OnSaleItem;
import com.opensoft.foodmart.domain.Store;

public interface OnSaleItemRepository extends JpaRepository<OnSaleItem,String>{
   Page<OnSaleItem> findByStore(Store store,Pageable pageable);   
}
