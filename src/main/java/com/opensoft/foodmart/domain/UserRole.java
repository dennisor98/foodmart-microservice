package com.opensoft.foodmart.domain;

import java.io.Serializable;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

public class UserRole extends BaseFoodDomain implements Serializable{

	private static final long serialVersionUID = -842591995836166444L;
	
	@ManyToOne()
	@JoinColumn(name="role_id")
	private Role role;
	
	@OneToOne()
	@JoinColumn(name="user_id")
	private User user;
	
	
}