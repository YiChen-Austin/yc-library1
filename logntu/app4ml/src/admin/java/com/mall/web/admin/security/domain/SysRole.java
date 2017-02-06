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
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

/**
 * @功能说明：系统角色实体类
 * @作者： 印鲜刚
 * @创建日期： 2010-5-4 @
 */
@Entity
@Table(name = "sys_role")
public class SysRole implements Serializable {
	private static final long serialVersionUID = -2830652976117676035L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	// 角色名称
	@Column(nullable = false, unique = true)
	private String name;
	// 角色代码
	private String code;
	// 角色备注
	@Column(length = 4000)
	private String remark;
	// 标志位0:系统管理人员权限;1:普通人员权限
	@Column(nullable = false, length = 1)
	private String flag = "1";
	@ManyToMany(mappedBy = "sysRoles", fetch = FetchType.LAZY, cascade = {
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<SysMenu> sysMenus = new ArrayList<SysMenu>();
	@ManyToMany(mappedBy = "sysRoles", fetch = FetchType.LAZY, cascade = {
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<SysResources> resources = new ArrayList<SysResources>();
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<SysMenu> getSysMenus() {
		return sysMenus;
	}

	public void setSysMenus(List<SysMenu> sysMenus) {
		this.sysMenus = sysMenus;
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
		if (!(other instanceof SysRole))
			return false;
		SysRole castOther = (SysRole) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId())
				.isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public List<SysResources> getResources() {
		return resources;
	}

	public void setResources(List<SysResources> resources) {
		this.resources = resources;
	}

}
