package com.mall.web.mall.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 自动登录数据
 */
@Entity
@Table(name = "ml_auto_login")
public class MemberAutoLogin implements Serializable {
	private static final long serialVersionUID = -7914159008194586780L;
	// 序号
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	// name
	@Column(name = "user_name")
	private String userName;
	@Column(name = "user_id")
	private String userId;
	// 内容
	@Column(name = "token_value")
	private String tokenValue;
	@Column(name = "domain")
	private String domain;
	@Column(name = "path")
	private String path;
	// 点击时间
	@Column(name = "auto_date")
	private Date autoDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTokenValue() {
		return tokenValue;
	}

	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getAutoDate() {
		return autoDate;
	}

	public void setAutoDate(Date autoDate) {
		this.autoDate = autoDate;
	}

}
