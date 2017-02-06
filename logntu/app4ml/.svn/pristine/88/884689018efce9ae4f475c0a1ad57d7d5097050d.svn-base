/**
 * 
 */
package com.mall.common.vo;

import java.io.Serializable;

/**
 * @功能说明：Grid分页控件封装Bean
 * @作者： 印鲜刚
 * @创建日期： 2010-5-11 @
 */
@SuppressWarnings("serial")
public class PageBean4UI implements Serializable {
	// 每页显示的行数
	private int rows = 20;
	// 请求的当前页
	private int page = 1;
	// 总页数
	private int totalPages;
	// 总记录数
	private int totalRecords;
	// 排序字段
	private String sort;
	// 排序方式
	private String order;

	/**
	 * 初始化PageBean相关参数
	 * 
	 * @param totalRecords
	 */
	public void init(int totalRecords) {
		this.totalRecords = totalRecords;
		this.rows = this.rows == 0 ? 20 : this.rows;
		this.page = this.page == 0 ? 1 : this.page;
		if (this.getTotalRecords() > 0) {
			this.setTotalPages((this.getTotalRecords() - 1) / this.getRows()
					+ 1);
		}
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
