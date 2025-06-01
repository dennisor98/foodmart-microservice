package com.opensoft.foodmart.domain;

import java.io.Serializable;
import java.util.List;

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
public class OrderDetails extends BaseFoodDomain implements Serializable{
	private static final long serialVersionUID = -8646885304284971112L;
	
	@OneToOne()
	@JoinColumn(name="order_id")
	Order order;
	
	List<Product> items;

}
