package com.xxx.rbac.model;

import java.sql.Timestamp;

public class Resource {

	
	private Long resourceId ;
	private String resourceName;
	private String type;
	private String icon ;
	private String product;
	private String url ;
	private Long parentId;
	private String treePath ;
	private String permissionKey;
	private Integer rowStatus;
	private Timestamp rowVersion;
	public Long getResourceId() {
		return resourceId;
	}
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getTreePath() {
		return treePath;
	}
	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}
	public String getPermissionKey() {
		return permissionKey;
	}
	public void setPermissionKey(String permissionKey) {
		this.permissionKey = permissionKey;
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
		return "Resource [resourceId=" + resourceId + ", resourceName=" + resourceName + ", type=" + type + ", icon="
				+ icon + ", product=" + product + ", url=" + url + ", parentId=" + parentId + ", treePath=" + treePath
				+ ", permissionKey=" + permissionKey + ", rowStatus=" + rowStatus + ", rowVersion=" + rowVersion + "]";
	}
	public Resource(Long resourceId, String resourceName, String type, String icon, String product, String url,
			Long parentId, String treePath, String permissionKey, Integer rowStatus, Timestamp rowVersion) {
		super();
		this.resourceId = resourceId;
		this.resourceName = resourceName;
		this.type = type;
		this.icon = icon;
		this.product = product;
		this.url = url;
		this.parentId = parentId;
		this.treePath = treePath;
		this.permissionKey = permissionKey;
		this.rowStatus = rowStatus;
		this.rowVersion = rowVersion;
	}
	
	
}
