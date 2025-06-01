package com.opensoft.foodmart.domain;

import java.io.Serializable;

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
public class RiderAdress extends BaseFoodDomain implements Serializable{

	private static final long serialVersionUID = -1431640993966576885L;
	
	@Column()
	String coordinates;
	
	@Column()
	String landMark;
		
	@OneToOne()
	@JoinColumn(name="rider_id")
	@OnDelete(action = OnDeleteAction.SET_NULL)
	Rider rider;	

}
