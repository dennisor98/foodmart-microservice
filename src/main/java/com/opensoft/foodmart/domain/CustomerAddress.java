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
public class CustomerAddress extends BaseFoodDomain implements Serializable{

	private static final long serialVersionUID = 6545392263519112362L;
	
	@Column()
	String coordinates;
	
	@Column()
	String landMark;
	
	@OneToOne()
	@JoinColumn(name="customer_id")
	@OnDelete(action = OnDeleteAction.SET_NULL)
	Customer customer;

}
