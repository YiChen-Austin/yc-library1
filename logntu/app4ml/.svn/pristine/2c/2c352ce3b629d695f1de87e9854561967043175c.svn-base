/**
 * 
 */
package com.mall.web.admin.security.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.mall.common.util.BaseUtil;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * @功能说明：系统用户VO对象
 * @作者： 印鲜刚
 * @创建日期： 2010-5-7
 * @
 */
public class SysUserBean implements Serializable {
	private static final long serialVersionUID = 8491023730881076034L;
	private String id;
	//删除多条记录时用
	private String deleteIDs;
	//真实姓名
	private String realName;
	//身份证号
	private String identityCard;
	//性别:
	private String gender;
	//工作年限
	private Float workYears;
	//出身日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDay;
	//人员性质 ：
	private String staffKind;
	//手机
	private String mobile;
	//是否是操作员
	private Boolean isOperator;
	//用户名
	private String userName;
	//用户密码
	private String password;
	//生效开始日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date effectStartDate;
	//生效结束日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date effectEndDate;
	//状态(true:启用；false:禁止)
	private Boolean disabled;
	//当前页面
	private Integer currentPage;
	//每页记录数
	private Integer pageSize;

	//设置人员所属组织机构ID信息
	private String sysOrganizationIDs;
	//设置人员所属组织机构名称信息
	private String orgNames;
	//设置人员拥有权限ID信息
	private String sysRoleIDs;
	//角色名称
	private String roleName;

	private String operatorFlag;
	private String disabledFlag;
	private String flag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeleteIDs() {
		return deleteIDs;
	}

	public void setDeleteIDs(String deleteIDs) {
		this.deleteIDs = deleteIDs;
	}

	public String getRealName() {
		return realName;
	}


	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Float getWorkYears() {
		return workYears;
	}

	public void setWorkYears(Float workYears) {
		this.workYears = workYears;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getStaffKind() {
		return staffKind;
	}

	public void setStaffKind(String staffKind) {
		this.staffKind = staffKind;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Boolean getIsOperator() {
		return isOperator;
	}

	public void setIsOperator(Boolean isOperator) {
		this.isOperator = isOperator;
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

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getEffectStartDate() {
		return effectStartDate;
	}

	public void setEffectStartDate(Date effectStartDate) {
		this.effectStartDate = effectStartDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getEffectEndDate() {
		return effectEndDate;
	}

	public void setEffectEndDate(Date effectEndDate) {
		this.effectEndDate = effectEndDate;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		if (BaseUtil.isEmpty(disabled))
			disabled = new Boolean(false);
		this.disabled = disabled;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getSysOrganizationIDs() {
		return sysOrganizationIDs;
	}

	public void setSysOrganizationIDs(String sysOrganizationIDs) {
		this.sysOrganizationIDs = sysOrganizationIDs;
	}

	public String getSysRoleIDs() {
		return sysRoleIDs;
	}

	public void setSysRoleIDs(String sysRoleIDs) {
		this.sysRoleIDs = sysRoleIDs;
	}

	public String getOperatorFlag() {
		return operatorFlag;
	}

	public void setOperatorFlag(String operatorFlag) {
		this.operatorFlag = operatorFlag;
	}

	public String getDisabledFlag() {
		return disabledFlag;
	}

	public void setDisabledFlag(String disabledFlag) {
		this.disabledFlag = disabledFlag;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getOrgNames() {
		return orgNames;
	}

	public void setOrgNames(String orgNames) {
		this.orgNames = orgNames;
	}

}
