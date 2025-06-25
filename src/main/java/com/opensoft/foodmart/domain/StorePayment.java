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


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StorePayment extends BaseFoodDomain implements Serializable{

	private static final long serialVersionUID = 5829966070275748715L;

	@ManyToOne()
	@JoinColumn()
	private Store store;
	
	@Column()
	private Double amount;
}
