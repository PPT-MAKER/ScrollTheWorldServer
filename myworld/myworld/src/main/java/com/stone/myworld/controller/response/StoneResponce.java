package com.stone.myworld.controller.response;

public class StoneResponce<T> {
	private int hr;
	private String message;
	private T data;
	
	public int getHr() {
		return hr;
	}
	
	public void setHr(int hr) {
		this.hr = hr;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
}
