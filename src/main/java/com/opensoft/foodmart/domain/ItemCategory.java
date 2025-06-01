package com.opensoft.foodmart.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemCategory extends BaseFoodDomain implements Serializable{

	private static final long serialVersionUID = -2798267203650437153L;
	
	@Column()
	String name;
	
	@Column
	Boolean isActive;
   
}
