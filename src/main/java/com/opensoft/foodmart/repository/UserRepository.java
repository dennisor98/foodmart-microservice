package com.opensoft.foodmart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opensoft.foodmart.domain.User;

public interface UserRepository extends JpaRepository<User,String>{
   Optional<User> findByMobileNumber(String mobileNumber);
}
