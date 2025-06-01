package com.opensoft.foodmart.domain;

import java.io.Serializable;

import jakarta.persistence.Column;

public class User extends BaseFoodDomain implements Serializable{

	private static final long serialVersionUID = -4762142029527540880L;

	
	@Column()
	private String firstName;
	
	@Column()
	private String lastName;
	
	@Column()
	private String mobileNumber;
	
	@Column(nullable=true)
	private String email;	
}
