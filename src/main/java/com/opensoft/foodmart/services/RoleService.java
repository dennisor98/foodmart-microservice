package com.opensoft.foodmart.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensoft.foodmart.domain.Role;
import com.opensoft.foodmart.repository.RoleRepository;


@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	 public void createSuperRole() {
		   String name = "SUPER_ADMIN";
		   Optional<Role> roleOpt =  this.roleRepository.findByName(name);
		   
		   if(roleOpt.isEmpty()) {
			   Role role =  Role.builder().active(true).description("Most super user in the system").name(name).build();
			   
			   try {
				   this.roleRepository.save(role);
				   this.roleRepository.flush();
			   }catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   }
	   }
}
