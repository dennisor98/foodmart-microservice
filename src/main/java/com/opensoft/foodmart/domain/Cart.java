package com.opensoft.foodmart.domain;

import java.io.Serializable;
import java.util.List;

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
public class Cart extends BaseFoodDomain implements Serializable{
	private static final long serialVersionUID = 6856212119560819422L;
	
	@ManyToOne()
	@JoinColumn()
	private User user;

	@Column()
	private List<OnSaleItem> items;
	
	@Column()
	private Double totalAmount;



}
