package com.xxx.springboot.shiro.model;

import java.sql.Timestamp;

public class UserRole {
	
	private Long userRoleId;
	private Long userId;
	private Long roleId;
	private Integer rowStatus;
	private Timestamp rowVersion;
	@Override
	public String toString() {
		return "UserRole [userRoleId=" + userRoleId + ", userId=" + userId + ", roleId=" + roleId + ", rowStatus="
				+ rowStatus + ", rowVersion=" + rowVersion + "]";
	}
	public UserRole(Long userRoleId, Long userId, Long roleId, Integer rowStatus, Timestamp rowVersion) {
		super();
		this.userRoleId = userRoleId;
		this.userId = userId;
		this.roleId = roleId;
		this.rowStatus = rowStatus;
		this.rowVersion = rowVersion;
	}
	public Long getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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
