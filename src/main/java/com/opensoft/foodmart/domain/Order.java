package com.opensoft.foodmart.domain;

import java.io.Serializable;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.opensoft.foodmart.enums.OrderSourceType;
import com.opensoft.foodmart.enums.OrderStatus;
import com.opensoft.foodmart.enums.OrderType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseFoodDomain implements Serializable{
	private static final long serialVersionUID = 7856011996033754186L; 
	
	@Column
	String orderRef;
	
	@ManyToOne()
	@JoinColumn(name = "customer_id",nullable=true)
	@OnDelete(action = OnDeleteAction.SET_NULL)
	Customer customer;
	
	@Column()
	OrderType type;
	
	@Column()
	OrderStatus status;
	
	@Column()
	Double orderAmount;
	
	@Column()
	Double deliveryFee;
	
	@Column()
	OrderSourceType source;
	
}
