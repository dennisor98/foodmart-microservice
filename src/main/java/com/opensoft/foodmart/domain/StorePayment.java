package com.opensoft.foodmart.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class StorePayment extends BaseFoodDomain implements Serializable{

	private static final long serialVersionUID = 5829966070275748715L;

	@ManyToOne()
	@JoinColumn()
	private Store store;
	
	@Column()
	private Double amount;
}
