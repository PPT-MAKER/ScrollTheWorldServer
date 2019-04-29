package com.stone.myworld.bean;

import java.util.Date;

public class UserTree {
	private Integer id;
	private Integer userId;
	private Integer treeId;
	private Date createdTime;
	private Date updatedTime;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getTreeId() {
		return treeId;
	}
	
	public void setTreeId(Integer treeId) {
		this.treeId = treeId;
	}
	
	public Date getCreatedTime() {
		return createdTime;
	}
	
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	public Date getUpdatedTime() {
		return updatedTime;
	}
	
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
}
