/**
 * 
 */
package com.mall.web.admin.security.vo;

import java.io.Serializable;

/**
 * @功能说明：友情链接
 * @作者： xgyin
 * @创建日期： 2010-12-23
 */

public class FriendlyLinkBean implements Serializable {
	private static final long serialVersionUID = 8372688992274556864L;
	private String wwwurl;
	private String name;
	private String operator;

	public String getWwwurl() {
		return wwwurl;
	}

	public void setWwwurl(String wwwurl) {
		this.wwwurl = wwwurl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}
