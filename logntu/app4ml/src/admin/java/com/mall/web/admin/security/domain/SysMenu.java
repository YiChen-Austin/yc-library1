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
 * @功能说明：系统菜单实体类
 * @作者： 印鲜刚
 * @创建日期： 2010-5-4 @
 */
@Entity
@Table(name = "sys_menu")
public class SysMenu implements Serializable {
	private static final long serialVersionUID = -2830652976117676035L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	// 菜单名称
	@Column(nullable = false, unique = true)
	private String name;
	// 菜单备注
	@Column(length = 4000)
	private String remark;
	// 链接地址
	private String pageUrl;
	// 显示顺序号
	private Integer orderNo;
	// 标志位0:系统菜单权限;1:普通菜单权限
	@Column(nullable = false, length = 1)
	private String flag = "1";
	//内部命令标志位，0:非内部命令；1：内部命令
	@Column(nullable = false, length = 1)
	private String internalCommand = "0";
	//菜单节点图片
	private String nodeImage;
	@ManyToOne
	private SysMenu sysMenu;

	@OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
	private List<SysPageRes> pageres = new ArrayList<SysPageRes>();

	@OneToMany(mappedBy = "sysMenu", fetch = FetchType.LAZY)
	private List<SysMenu> sysMenus = new ArrayList<SysMenu>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<SysRole> sysRoles = new ArrayList<SysRole>();

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

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getParentId() {
		if (BaseUtil.isEmpty(this.getSysMenu()))
			return null;
		else
			return this.getSysMenu().getId();
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getNodeImage() {
		return nodeImage;
	}

	public void setNodeImage(String nodeImage) {
		this.nodeImage = nodeImage;
	}

	public SysMenu getSysMenu() {
		return sysMenu;
	}

	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}

	public List<SysMenu> getSysMenus() {
		return sysMenus;
	}

	public void setSysMenus(List<SysMenu> sysMenus) {
		this.sysMenus = sysMenus;
	}

	public List<SysRole> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(List<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof SysMenu))
			return false;
		SysMenu castOther = (SysMenu) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public String getInternalCommand() {
		return internalCommand;
	}

	public void setInternalCommand(String internalCommand) {
		this.internalCommand = internalCommand;
	}

	public List<SysPageRes> getPageres() {
		return pageres;
	}

	public void setPageres(List<SysPageRes> pageres) {
		this.pageres = pageres;
	}

}
