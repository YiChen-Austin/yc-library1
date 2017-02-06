package com.mall.web.admin.security.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 页面资源
 * 
 * @author Administrator
 * 
 */

@Entity
@Table(name = "sys_page_res")
public class SysPageRes extends SysPageResouce {

	private static final long serialVersionUID = 1L;

	// 页面的ID，即页面的url
	private String page_id;

	@OneToMany(mappedBy = "pageres", fetch = FetchType.EAGER)
	private List<SysResources> resouce;

	@ManyToOne
	private SysMenu menu;

	public String getPage_id() {
		return page_id;
	}

	public void setPage_id(String pageId) {
		page_id = pageId;
	}

	public List<SysResources> getResouce() {
		return resouce;
	}

	public void setResouce(List<SysResources> resouce) {
		this.resouce = resouce;
	}

	public SysMenu getMenu() {
		return menu;
	}

	public void setMenu(SysMenu menu) {
		this.menu = menu;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof SysPageRes))
			return false;
		SysPageRes castOther = (SysPageRes) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

}
