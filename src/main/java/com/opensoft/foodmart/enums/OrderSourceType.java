package com.opensoft.foodmart.enums;

public enum OrderSourceType {
  ONLINE("ONLINE"),
  INPURCHASE("INPURCHASE");
	String source;
	private OrderSourceType(String source) {
		 this.source = source; 
	}
	
	public String getSource() {
		return this.source;
	}
}
