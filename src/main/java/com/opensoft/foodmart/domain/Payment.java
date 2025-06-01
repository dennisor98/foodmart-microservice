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
public class Payment extends BaseFoodDomain implements Serializable{

	private static final long serialVersionUID = 8430412917236016052L;
	
	@OneToOne()
	@JoinColumn(name="order_id")
	@OnDelete(action = OnDeleteAction.SET_NULL)
	Order order;
	
	@Column()
	String checkoutId;
	
	@Column(nullable=true)
	String initiatedTime;
	
	@Column(nullable=true)
	String finalizedTime;
	
	@Column(nullable=true)
	String receiptNo;
	
	@Column()
	Boolean completed;
	

}
