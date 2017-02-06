/**
 * 
 */
package com.mall.web.admin.common.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @功能说明：桌面维护
 * @作者： xgyin
 * @创建日期： 2010-12-22
 */
@Entity
@Table(name = "sys_desktop_defend")
public class SysDesktopDefend implements Serializable {
	private static final long serialVersionUID = 3238922700850668163L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	//区域业务名称
	@Column(nullable = false, unique = true)
	private String title;
	//具体业务放置的DIV标签的ID;
	private String divId;
	//备注
	@Column(length = 4000)
	private String remark;
	@OneToMany(mappedBy = "desktopDefend", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SysSelfDesktopDefend> selfDesktopDefends = new ArrayList<SysSelfDesktopDefend>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDivId() {
		return divId;
	}

	public void setDivId(String divId) {
		this.divId = divId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<SysSelfDesktopDefend> getSelfDesktopDefends() {
		return selfDesktopDefends;
	}

	public void setSelfDesktopDefends(List<SysSelfDesktopDefend> selfDesktopDefends) {
		this.selfDesktopDefends = selfDesktopDefends;
	}

}
