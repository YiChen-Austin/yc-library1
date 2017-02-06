/**
 * 
 */
package com.mall.common.vo;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mall.common.util.BaseUtil;

/**
 * @功能说明：Grid分页控件封装Bean
 * @作者： 印鲜刚
 * @创建日期： 2010-5-11 @
 */
@SuppressWarnings("serial")
public class PageBean implements Serializable {
	// 每页显示的行数
	private int pageSize ;
	// 子列表每页显示行数
	private int innerPageSize = 10;
	// 请求的当前页
	private int curPage =1;
	// 子列表请求的当前页
	private int innerCurpage = 1;
	// 总页数
	private int totalPages;
	// 子列表总页数
	private int innerTotalPages;
	// 总记录数
	private int totalRecords;
	// 排序字段
	private String orderBy;
	// 排序方式
	private String order;

	private boolean chgCurPage = false;

	private String requestUrl;//

	private String prefixUrl;// 以分页符及其值为界，前面部分url
	private String suffixUrl;// 以分页符及其值为界，后面部分url
	private String baseUrl;// 除去动态部分的url

	private String queryString;
	private String contextPath;
	private String servletPath;

	public PageBean() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		if (request != null) {
			this.requestUrl = request.getRequestURL().toString();
			this.queryString = request.getQueryString();
			this.contextPath = request.getContextPath();
			this.servletPath = request.getServletPath();

			this.baseUrl = this.requestUrl.substring(0,
					this.requestUrl.indexOf(this.servletPath, 7));
			this.baseUrl = this.baseUrl + this.servletPath;
		}
	}

	/**
	 * @param dynLayer
	 *            动态数据共有几层，从后往前
	 * 
	 *            适合于链接拼接伪链接情况
	 */
	public PageBean(int dynLayer) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		if (request != null) {			
			try {
				this.requestUrl = URLDecoder.decode(request.getRequestURL().toString(),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			this.queryString = request.getQueryString();
			this.contextPath = request.getContextPath();
			this.servletPath = request.getServletPath();

			this.baseUrl = this.requestUrl.substring(0,
					this.requestUrl.indexOf(this.servletPath, 7));
			String[] v = this.servletPath.split("/");
			for (int i = 0; v != null && i < v.length - dynLayer; i++) {
				if (BaseUtil.isEmpty(v[i]))
					continue;
				this.baseUrl = this.baseUrl + "/" + v[i];
			}
		}
	}

	/**
	 * 初始化PageBean相关参数
	 * 
	 * @param totalRecords
	 */
	public void init(int totalRecords) {
		this.totalRecords = totalRecords;
		this.pageSize = this.pageSize == 0 ? 30 : this.pageSize;
		this.curPage = this.curPage == 0 ? 1 : this.curPage;
		if (this.getTotalRecords() > pageSize * 30) {
			this.setTotalPages(30);
		} else if (this.getTotalRecords() > 0) {
			this.setTotalPages((this.getTotalRecords() - 1)
					/ this.getPageSize() + 1);
		}
		if (this.getCurPage() > this.getTotalPages() && chgCurPage == false)
			this.setCurPage(this.getTotalPages());
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageSize(int pageSize, boolean chgCurPage) {
		this.pageSize = pageSize;
		this.chgCurPage = chgCurPage;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
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

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public int getInnerPageSize() {
		return innerPageSize;
	}

	public void setInnerPageSize(int innerPageSize) {
		this.innerPageSize = innerPageSize;
	}

	public int getInnerCurpage() {
		return innerCurpage;
	}

	public void setInnerCurpage(int innerCurpage) {
		this.innerCurpage = innerCurpage;
	}

	public int getInnerTotalPages() {
		return innerTotalPages;
	}

	public void setInnerTotalPages(int innerTotalPages) {
		this.innerTotalPages = innerTotalPages;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getServletPath() {
		return servletPath;
	}

	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}

	public String getPrefixUrl() {
		return prefixUrl;
	}

	public void setPrefixUrl(String prefixUrl) {
		this.prefixUrl = this.baseUrl + "/" + prefixUrl;
	}

	public void setPrefixUrl(String baseUrl, String prefixUrl) {
		this.prefixUrl = baseUrl + "/" + prefixUrl;
	}

	public String getSuffixUrl() {
		return suffixUrl;
	}

	public void setSuffixUrl(String suffixUrl) {
		this.suffixUrl = suffixUrl;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

}
