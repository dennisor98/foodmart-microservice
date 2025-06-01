package com.opensoft.foodmart.domain;

import java.io.Serializable;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class RolePermission extends BaseFoodDomain implements Serializable{

	private static final long serialVersionUID = -2945825806456775054L;

	
	@ManyToOne()
	@JoinColumn(name="role_id")
	private Role role;
	
	@ManyToOne()
	@JoinColumn(name="permission_id")
	private Permission permission;
	
	@ManyToOne()
	@JoinColumn(name="user_id")
	private User user;		
}
