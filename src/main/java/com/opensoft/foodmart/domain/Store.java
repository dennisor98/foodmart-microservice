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
public class Store extends BaseFoodDomain implements Serializable{

	private static final long serialVersionUID = 3561863385311094600L;
	
	@Column()
	String storeNumber;
	
	@Column()
	String name;
	
	@Column()
	String businessType;
	
	@Column()
	String permitNumber;
	
	@Column()
	private Boolean active;
	
	@OneToOne()
	@JoinColumn(name="user_id")
	@OnDelete(action = OnDeleteAction.SET_NULL)
	User user;
	
	@OneToOne()
	@JoinColumn(name="address_id")
	@OnDelete(action = OnDeleteAction.SET_NULL)
	StoreAddress address;

}
