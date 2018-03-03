package com.xxx.rbac.common;

public class PaginationVo {

	/**
	 * 当前页
	 */
	private int page ;
	
	/**
	 * 每页个数
	 */
	private int pageSizes;

	
	public PaginationVo() {
		super();
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSizes() {
		return pageSizes;
	}

	public void setPageSizes(int pageSizes) {
		this.pageSizes = pageSizes;
	}
	
	
}
