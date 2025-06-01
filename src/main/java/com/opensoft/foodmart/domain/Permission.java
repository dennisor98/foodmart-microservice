package com.opensoft.foodmart.domain;

import java.io.Serializable;

import jakarta.persistence.Column;

public class Permission extends BaseFoodDomain implements Serializable{

	private static final long serialVersionUID = 5094998179410550139L;

	@Column()
	private String name;
	
	@Column()
	private String description;
}
