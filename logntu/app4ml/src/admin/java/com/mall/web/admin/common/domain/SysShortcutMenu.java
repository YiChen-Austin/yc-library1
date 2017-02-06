package com.mall.web.admin.common.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import com.mall.web.admin.security.domain.SysMenu;


/**
 * @功能说明：系统快捷菜单实体类
 * @作者： 沈国仿
 * @创建日期： 2010-5-25
 * @
 */
@Entity
@Table(name = "sys_shortcut_menu")
public class SysShortcutMenu implements Serializable {

	private static final long serialVersionUID = 3832630478031490887L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@ManyToOne
	//快捷菜单对应的真正菜单PO
	private SysMenu sysMenu;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SysMenu getSysMenu() {
		return sysMenu;
	}

	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof SysShortcutMenu))
			return false;
		SysShortcutMenu castOther = (SysShortcutMenu) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

}
