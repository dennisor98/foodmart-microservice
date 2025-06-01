package com.opensoft.foodmart.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class PaymentApp extends BaseFoodDomain implements Serializable{
	private static final long serialVersionUID = 6964540794719365011L;
	
	@ManyToOne()
	@JoinColumn(name="store_id")
	private Store store;
	
	@Column()
	private String consumerKey;
	
	@Column()
	private String consumerSecret;
	
	@Column()
	private String shortCode;
	
	@Column()
	private String tillNumber;	
}
