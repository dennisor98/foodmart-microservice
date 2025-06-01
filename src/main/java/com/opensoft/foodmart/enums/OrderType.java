package com.opensoft.foodmart.enums;

public enum OrderType {
  DINEIN("Dine-in"),
  TAKEOUT("Takeout"),
  DELIVERY("Delivery");
	
	String orderType;
	private OrderType(String ordertype) {
		this.orderType = ordertype;
	}
	
	public String getOrderType() {
		return this.orderType;
	}
}
