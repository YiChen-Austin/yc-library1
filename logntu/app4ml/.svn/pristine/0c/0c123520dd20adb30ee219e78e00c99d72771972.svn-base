package com.mall.web.admin.security.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 页面组件实体类
 * 
 * @author Administrator
 * 
 */

@Entity
@Table(name = "sys_resources")
public class SysResources extends SysPageResouce {
	private static final long serialVersionUID = -2830652976117676035L;
	// 页面组件ID
	private String res_id;

	// 资源名称
	private String name;

	// 是否可见
	private String visile;

	@ManyToOne
	@JoinColumn(name = "sys_page_res_id")
	private SysPageRes pageres;
	@OneToMany(mappedBy = "resources", fetch = FetchType.LAZY)
	private List<SysAuthorize> authorize;
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH })
	private List<SysRole> sysRoles = new ArrayList<SysRole>();

	public String getRes_id() {
		return res_id;
	}

	public void setRes_id(String resId) {
		res_id = resId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SysPageRes getPageres() {
		return pageres;
	}

	public void setPageres(SysPageRes pageres) {
		this.pageres = pageres;
	}

	public List<SysAuthorize> getAuthorize() {
		return authorize;
	}

	public void setAuthorize(List<SysAuthorize> authorize) {
		this.authorize = authorize;
	}

	public List<SysRole> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(List<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

	public String getVisile() {
		return visile;
	}

	public void setVisile(String visile) {
		this.visile = visile;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof SysResources))
			return false;
		SysResources castOther = (SysResources) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId())
				.isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

}
