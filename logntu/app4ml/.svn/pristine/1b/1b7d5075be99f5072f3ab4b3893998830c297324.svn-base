/**
 * 
 */
package com.mall.web.admin.common.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mall.web.admin.security.domain.SysUser;

/**
 * @功能说明：自定义桌面维护
 * @作者： xgyin
 * @创建日期： 2010-12-22
 */
@Entity
@Table(name = "sys_self_desktop_defend")
public class SysSelfDesktopDefend implements Serializable {
	private static final long serialVersionUID = -3534493043229906659L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	//桌面自身维护人员
	@ManyToOne
	private SysUser defendUser;
	//放置位置 1:左边2:右边
	private String defaultPosition = "1";
	//关联桌面维护
	@ManyToOne
	private SysDesktopDefend desktopDefend;
	//排序号
	private Integer orderNo = 1;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SysUser getDefendUser() {
		return defendUser;
	}

	public void setDefendUser(SysUser defendUser) {
		this.defendUser = defendUser;
	}

	public String getDefaultPosition() {
		return defaultPosition;
	}

	public void setDefaultPosition(String defaultPosition) {
		this.defaultPosition = defaultPosition;
	}

	public SysDesktopDefend getDesktopDefend() {
		return desktopDefend;
	}

	public void setDesktopDefend(SysDesktopDefend desktopDefend) {
		this.desktopDefend = desktopDefend;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

}
