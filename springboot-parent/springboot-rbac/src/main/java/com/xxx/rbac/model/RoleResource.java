package com.xxx.rbac.model;

import java.sql.Timestamp;

public class RoleResource {
	
	private Long roleResourceId;

	private Long roleId;
	private Long resourceId;
	private Integer rowStatus;
	private Timestamp rowVersion;

	@Override
	public String toString() {
		return "RoleResource [roleResourceId=" + roleResourceId + ", roleId=" + roleId + ", resourceId=" + resourceId
				+ ", rowStatus=" + rowStatus + ", rowVersion=" + rowVersion + "]";
	}

	public RoleResource(Long roleResourceId, Long roleId, Long resourceId, Integer rowStatus, Timestamp rowVersion) {
		super();
		this.roleResourceId = roleResourceId;
		this.roleId = roleId;
		this.resourceId = resourceId;
		this.rowStatus = rowStatus;
		this.rowVersion = rowVersion;
	}

	public Long getRoleResourceId() {
		return roleResourceId;
	}

	public void setRoleResourceId(Long roleResourceId) {
		this.roleResourceId = roleResourceId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
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
