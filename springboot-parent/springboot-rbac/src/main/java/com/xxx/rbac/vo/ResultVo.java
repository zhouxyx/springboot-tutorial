package com.xxx.rbac.vo;

public class ResultVo {

	/**
	 * 消息
	 */
	private String message;
	/**
	 * 状态码
	 */
	private String status;

	/**
	 * error , succ
	 */
	private String type;

	/**
	 * 返回的数据
	 */
	private Object data;

	public ResultVo() {
		super();
	}

	public ResultVo(String message, String status, String type, Object data) {
		super();
		this.message = message;
		this.status = status;
		this.type = type;
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
