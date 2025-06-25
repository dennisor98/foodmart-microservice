package com.opensoft.foodmart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opensoft.foodmart.domain.Store;
import com.opensoft.foodmart.domain.StoreKyc;

public interface StoreKycRepository extends JpaRepository<StoreKyc,String>{
  Optional<StoreKyc> findByStore(Store store);
}
