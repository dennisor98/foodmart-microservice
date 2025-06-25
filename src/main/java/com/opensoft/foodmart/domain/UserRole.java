package com.opensoft.foodmart.domain;

import java.io.Serializable;

import com.opensoft.foodmart.enums.OrderSourceType;
import com.opensoft.foodmart.enums.OrderStatus;
import com.opensoft.foodmart.enums.OrderType;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class UserRole extends BaseFoodDomain implements Serializable{

	private static final long serialVersionUID = -842591995836166444L;
	
	@ManyToOne()
	@JoinColumn(name="role_id")
	private Role role;
	
	@OneToOne()
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne()
	@JoinColumn(name="creator_user_id")
	private User creator;
	
	
}