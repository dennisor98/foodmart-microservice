package com.opensoft.foodmart.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Role extends BaseFoodDomain implements Serializable{

	private static final long serialVersionUID = 8712745583612301181L;

	
	@Column()
	private String name;
	
	@Column()
	private String description;
	
	@ManyToOne()
	@JoinColumn(name="store_id")
	private Store store;
}
