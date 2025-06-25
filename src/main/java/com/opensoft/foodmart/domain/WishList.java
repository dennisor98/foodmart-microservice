package com.opensoft.foodmart.domain;

import java.io.Serializable;
import java.util.List;

import com.opensoft.foodmart.enums.OrderSourceType;
import com.opensoft.foodmart.enums.OrderStatus;
import com.opensoft.foodmart.enums.OrderType;

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
public class WishList extends BaseFoodDomain implements Serializable{

	private static final long serialVersionUID = 151491639216920742L;
	
	@ManyToOne()
	@JoinColumn(name="user_id")
	private User user;
	
	@Column()
	private List<OnSaleItem> items;

}
