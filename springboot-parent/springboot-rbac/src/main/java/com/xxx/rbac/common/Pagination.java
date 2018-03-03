package com.xxx.rbac.common;

import java.util.List;

/**
 * @author zhou
 * 分页类
 * @param <T>
 */
public class Pagination<T> {

	/**
	 * 总记录
	 */
	private int totals;
	
	/**
	 * 总页数
	 */
	private int pages;
	
	/**
	 * 当前页
	 */
	private int page ;
	
	/**
	 * 每页个数
	 */
	private int pageSizes;
	
	private List<T> datas;

	public Pagination(int totals, int pages, int page, int pageSizes, List<T> datas) {
		super();
		this.totals = totals;
		this.pages = pages;
		this.page = page;
		this.pageSizes = pageSizes;
		this.datas = datas;
	}

	public Pagination() {
		super();
	}

	public int getTotals() {
		return totals;
	}

	public void setTotals(int totals) {
		this.totals = totals;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
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

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	
}
