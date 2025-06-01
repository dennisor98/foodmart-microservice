package com.opensoft.foodmart.domain;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseFoodDomain implements Serializable{

	private static final long serialVersionUID = -5209643487406608029L;
	
 @Column()
 String name;
 
 @Column()
 String description;
 
 @Column()
 Double buyingPrice;
 
 @Column()
 String skuNumber;
 
 @Column()
 Date expiryDate;
 
 @ManyToOne()
 @JoinColumn(name="store_id")
 @OnDelete(action = OnDeleteAction.SET_NULL)
 Store store;
 
 @Column()
 Boolean isActive;
 
}
