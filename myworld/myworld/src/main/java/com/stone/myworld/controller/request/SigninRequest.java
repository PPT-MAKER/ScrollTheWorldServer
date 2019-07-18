package com.stone.myworld.controller.request;

import org.springframework.util.StringUtils;

public class SigninRequest {
	private String nickName;
	private String password;
	
	public boolean valid() {
		return !StringUtils.isEmpty(nickName) && !StringUtils.isEmpty(password);
	}
	
	public String getNickName() {
		return nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
