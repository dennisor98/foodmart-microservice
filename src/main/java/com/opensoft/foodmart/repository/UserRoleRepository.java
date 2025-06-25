package com.opensoft.foodmart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opensoft.foodmart.domain.Role;
import com.opensoft.foodmart.domain.User;
import com.opensoft.foodmart.domain.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole,String> {
   Optional<Role> findRoleByUser(User user);
   Optional<UserRole> findByRoleAndUser(Role role,User user);
   
}
