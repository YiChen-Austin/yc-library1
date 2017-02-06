/**
 * 
 */
package com.mall.web.admin.security.domain;

import java.io.Serializable;
import java.util.ArrayList;
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
import org.hibernate.annotations.GenericGenerator;

import com.mall.common.util.BaseUtil;


/**
 * @功能说明：组织机构实体类
 * @作者： 印鲜刚
 * @创建日期： 2010-5-4
 * @
 */
@Entity
@Table(name = "sys_organization")
public class SysOrganization implements Serializable {
	private static final long serialVersionUID = -2830652976117676035L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	//组织机构名称
	@Column(nullable = false, unique = true)
	private String name;
	//组织机构简称
	private String shortName;
	//组织机构备注
	@Column(length = 4000)
	private String remark;
	//组织机构号
	private String organizationNumber;
	//显示顺序号
	private Integer orderNo;
	//组织机构节点图片
	private String nodeImage;

	@ManyToOne
	private SysOrganization sysOrganization;
	@OneToMany(mappedBy = "sysOrganization", fetch = FetchType.LAZY)
	private List<SysOrganization> sysOrganizations = new ArrayList<SysOrganization>();

	@ManyToMany(mappedBy = "sysOrganizations", fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH })
	private List<SysUser> sysUsers = new ArrayList<SysUser>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getOrganizationNumber() {
		return organizationNumber;
	}

	public void setOrganizationNumber(String organizationNumber) {
		this.organizationNumber = organizationNumber;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getNodeImage() {
		return nodeImage;
	}

	public void setNodeImage(String nodeImage) {
		this.nodeImage = nodeImage;
	}

	public String getParentId() {
		if (BaseUtil.isEmpty(this.getSysOrganization()))
			return null;
		else
			return this.getSysOrganization().getId();
	}

	public SysOrganization getSysOrganization() {
		return sysOrganization;
	}

	public void setSysOrganization(SysOrganization sysOrganization) {
		this.sysOrganization = sysOrganization;
	}

	public List<SysOrganization> getSysOrganizations() {
		return sysOrganizations;
	}

	public void setSysOrganizations(List<SysOrganization> sysOrganizations) {
		this.sysOrganizations = sysOrganizations;
	}

	public List<SysUser> getSysUsers() {
		return sysUsers;
	}

	public void setSysUsers(List<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof SysOrganization))
			return false;
		SysOrganization castOther = (SysOrganization) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

}
