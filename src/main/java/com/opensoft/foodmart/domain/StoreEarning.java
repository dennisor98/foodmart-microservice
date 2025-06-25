package com.opensoft.foodmart.domain;

import java.io.Serializable;

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


//@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
