package com.opensoft.foodmart.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class PaymentMethod extends BaseFoodDomain implements Serializable{

	private static final long serialVersionUID = -4953411319514128736L;
	
	@ManyToOne()
	@JoinColumn(name="store_id")
	private Store store;
	
	@Column()
	private String name;
	
	@Column()
	private Boolean active;

}
