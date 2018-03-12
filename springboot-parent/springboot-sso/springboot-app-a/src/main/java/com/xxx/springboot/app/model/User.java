package com.xxx.springboot.app.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long userId;
	private String userName;
	private String password;
	private String phone ; 
	private Integer rowStatus;
	private Timestamp rowVersion;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Long userId, String userName, String password, String phone, Integer rowStatus, Timestamp rowVersion) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.phone = phone;
		this.rowStatus = rowStatus;
		this.rowVersion = rowVersion;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", phone=" + phone
				+ ", rowStatus=" + rowStatus + ", rowVersion=" + rowVersion + "]";
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getRowStatus() {
		return rowStatus;
	}
	public void setRowStatus(Integer rowStatus) {
		this.rowStatus = rowStatus;
	}
	public Timestamp getRowVersion() {
		return rowVersion;
	}
	public void setRowVersion(Timestamp rowVersion) {
		this.rowVersion = rowVersion;
	}
	
}
