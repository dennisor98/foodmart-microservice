package com.opensoft.foodmart.domain;

import java.io.Serializable;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


public class Rider extends BaseFoodDomain implements Serializable{

	private static final long serialVersionUID = -7632056763254545433L;
	
	@OneToOne()
	@JoinColumn(name="address_id",nullable=true)
	@OnDelete(action = OnDeleteAction.SET_NULL)
	RiderAdress address;
	
	@Column()
	String firstName;
	
	@Column()
	String lastName;
	
	@Column()
	String email;
	
	@Column()
	String phone;
	
	@Column()
	String idNumber;
	
	@Column
	Boolean isActive;
	
	

}
