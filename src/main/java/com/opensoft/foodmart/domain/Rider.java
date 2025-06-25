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
public class Rider extends BaseFoodDomain implements Serializable{

	private static final long serialVersionUID = -7632056763254545433L;
	
	@OneToOne()
	@JoinColumn(name="address_id",nullable=true)
	@OnDelete(action = OnDeleteAction.SET_NULL)
	RiderAdress address;
	
	@Column()
	String firstName;
	
	@Column()
	String lastName;
	
	@Column()
	String email;
	
	@Column()
	String phone;
	
	@Column()
	String idNumber;
	
	@Column
	Boolean isActive;
	
	

}
