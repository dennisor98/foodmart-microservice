package com.opensoft.foodmart.enums;


public enum JwtType {

	USER_ACCESS_TOKEN("user_access_token"),VENDOR_ACCESS_TOKEN("vendor_access_token"),USER_REFRESH_TOKEN("user_refresh_token"),VENDOR_REFRESH_TOKEN("vendor_refresh_token");

	String token;

	private JwtType(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}

}