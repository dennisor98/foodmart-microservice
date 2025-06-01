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
public class StoreAddress extends BaseFoodDomain implements Serializable{

	private static final long serialVersionUID = -8597291631716058114L;
  
	@Column()
	String coordinates;
	
	@Column()
	String landMark;
	
	@Column()
	String postBox;
	

	@OneToOne()
	@JoinColumn(name="store_id")
	@OnDelete(action = OnDeleteAction.SET_NULL)
	Store store;
	
}
