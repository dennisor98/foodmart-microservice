package com.opensoft.foodmart.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class StoreEarning extends BaseFoodDomain implements Serializable{

	private static final long serialVersionUID = 8425528343600834076L;

	@ManyToOne()
	@JoinColumn(name="store_id")
	private Store store;
	
	@ManyToOne()
	@JoinColumn(name="order_id")
	private Order order;
	
	@Column()
	private Double amount;
}
