/**
 * 
 */
package com.mall.web.mall.common.vo;

import java.io.Serializable;

/**
 * @功能说明：系统用户登录VO对象
 * @作者： 印鲜刚
 * @创建日期： 2010-5-7 @
 */
public class SysUserLoginBean implements Serializable {
	private static final long serialVersionUID = -8276036708096528006L;
	private String id;
	// 登录人员所在组织机构ID
	private String organizationId = "none";
	// 标志位0:系统管理人员;1:普通人员
	private String flag = "1";
	// 用户名
	private String userName;
	// 用户密码
	private String password;
	// 验证码
	private String verification;
	// 真实姓名
	private String realName;
	// 组织机构名称
	private String organizationName;
	// 登录IP
	private String ip;

	public String getId() {
		return id;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
