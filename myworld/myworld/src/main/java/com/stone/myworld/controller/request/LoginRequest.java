package com.stone.myworld.controller.request;

import org.springframework.util.StringUtils;

public class LoginRequest {
	private Integer userId;
	private String password;
	
	public boolean valid() {
		return userId != null && !StringUtils.isEmpty(password);
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
