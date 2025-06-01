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
public class OnSaleItems extends BaseFoodDomain implements Serializable{
	private static final long serialVersionUID = -1172627225709229952L;
	
	@OneToOne()
	@JoinColumn(name="store_id")
	@OnDelete(action = OnDeleteAction.SET_NULL)
	private Store store;
	
	@OneToOne()
	@JoinColumn(name="category_id")
	@OnDelete(action = OnDeleteAction.SET_NULL)
	private ItemCategory category;
		
	@Column()
	private String name;
	
	@Column()
	private String description;
	
	@Column()
	private String imageLink;
	
	@Column()
	private Boolean isActive;
	
	@Column()
	private Integer availableQuantity;
	
	@Column()
	private Integer soldOutThreshHold;
	
	@Column()
	private Integer alertThreshHold;
	

}
