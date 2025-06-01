package com.opensoft.foodmart.enums;

public enum OrderStatus {
	NEW("NEW"),PREPARING("PREPARING"),
	READY("READY"),
	SHIPPED("SHIPPED"),
	DELIVERED("DELIVERED"),
	CANCELLED("CANCELLED"),
	RETURNED("RETURNED");
	String status;
	
	private OrderStatus(String status) {
	  this.status = status;	
	}

	public String getStatus() {
		return this.status;
	}
}
