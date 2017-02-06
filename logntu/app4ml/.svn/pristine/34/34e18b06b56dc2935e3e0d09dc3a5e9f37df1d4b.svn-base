/**
 * 
 */
package com.mall.web.admin.security.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import com.mall.web.admin.common.domain.SysShortcutMenu;

/**
 * @功能说明：人员实体类
 * @作者： 印鲜刚
 * @创建日期： 2010-5-4
 * @
 */
@Entity
@Table(name = "sys_user")
public class SysUser implements Serializable {
	private static final long serialVersionUID = -2830652976117676035L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	//传真
	private String fax;
	//真实姓名
	@Column(nullable = false)
	private String realName;
	//身份证号
	private String identityCard;
	//性别:
	private String gender;
	//工作年限
	private Float workYears;
	//出身日期
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
	private Date effectStartDate;
	//生效结束日期
	private Date effectEndDate;
	//状态(true:启用；false:禁止)
	private Boolean disabled;
	//标志位0:系统管理人员;1:普通人员
	@Column(nullable = false, length = 1)
	private String flag = "1";
	//用户创建人员
	@ManyToOne
	private SysUser createUser;
	//创建机构
	@ManyToOne
	private SysOrganization createOrg;
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<SysOrganization> sysOrganizations = new ArrayList<SysOrganization>();
	@ManyToMany(mappedBy = "sysUsers", fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private List<SysRole> sysRoles = new ArrayList<SysRole>();

	@OneToMany(cascade = CascadeType.ALL)
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	private List<SysShortcutMenu> sysShortcutMenus = new ArrayList<SysShortcutMenu>();

	public List<SysShortcutMenu> getSysShortcutMenus() {
		return sysShortcutMenus;
	}

	public void setSysShortcutMenus(List<SysShortcutMenu> sysShortcutMenus) {
		this.sysShortcutMenus = sysShortcutMenus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
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

	public Float getWorkYears() {
		return workYears;
	}

	public void setWorkYears(Float workYears) {
		this.workYears = workYears;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
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

	public Date getEffectStartDate() {
		return effectStartDate;
	}

	public void setEffectStartDate(Date effectStartDate) {
		this.effectStartDate = effectStartDate;
	}

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
		this.disabled = disabled;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStaffKind() {
		return staffKind;
	}

	public void setStaffKind(String staffKind) {
		this.staffKind = staffKind;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public SysUser getCreateUser() {
		return createUser;
	}

	public void setCreateUser(SysUser createUser) {
		this.createUser = createUser;
	}

	public List<SysOrganization> getSysOrganizations() {
		return sysOrganizations;
	}

	public void setSysOrganizations(List<SysOrganization> sysOrganizations) {
		this.sysOrganizations = sysOrganizations;
	}

	public List<SysRole> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(List<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

	public SysOrganization getCreateOrg() {
		return createOrg;
	}

	public void setCreateOrg(SysOrganization createOrg) {
		this.createOrg = createOrg;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof SysUser))
			return false;
		SysUser castOther = (SysUser) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

}
