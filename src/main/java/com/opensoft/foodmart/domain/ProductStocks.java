package com.opensoft.foodmart.domain;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductStocks extends BaseFoodDomain implements Serializable {

	private static final long serialVersionUID = 7883819322705369828L;

	@OneToOne()
	@JoinColumn(name="product_id")
	@OnDelete(action = OnDeleteAction.SET_NULL)
	Product product;
	
	@Column()
	Integer availableQuantity;
	
	@Column()
	Integer reservedQuantity;
	
	@Column()
	Integer alertThreshHold;
	
	@Column()
	Integer orderThreshHold;
	
}
