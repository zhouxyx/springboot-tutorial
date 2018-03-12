package com.xxx.springboot.app.model;

import java.sql.Timestamp;

public class Role {


	private Long roleId;
	private String roleName;
	private String roleDesc;
	private String product;
	private Integer rowStatus;
	private Timestamp rowVersion;

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(Long roleId, String roleName, String roleDesc, String product, Integer rowStatus,
			Timestamp rowVersion) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.product = product;
		this.rowStatus = rowStatus;
		this.rowVersion = rowVersion;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
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

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", roleDesc=" + roleDesc + ", product=" + product
				+ ", rowStatus=" + rowStatus + ", rowVersion=" + rowVersion + "]";
	}

}
