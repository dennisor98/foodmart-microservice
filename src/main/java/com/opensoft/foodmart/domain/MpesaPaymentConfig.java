package com.opensoft.foodmart.domain;

import java.io.Serializable;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;



public class MpesaPaymentConfig extends BaseFoodDomain implements Serializable{
	
	private static final long serialVersionUID = -15998096999425119L;
	
	@OneToOne()
	@JoinColumn(name="store_id")
	@OnDelete(action = OnDeleteAction.SET_NULL)
	Store store;
	
	@Column
	String shortCode;
	
	@Column()
	String tillNumber;
	
	@Column()
	String consumerKey;
	
	@Column()
	String consumerSecret;
	
	@Column()
	Boolean isActive;

}
