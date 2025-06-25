package com.opensoft.foodmart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opensoft.foodmart.domain.Role;

public interface RoleRepository extends JpaRepository<Role,String>{
  Optional<Role> findByName(String name);
}
