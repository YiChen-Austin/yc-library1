/**
 * 
 */
package com.mall.web.admin.security.vo;

import java.io.Serializable;

/**
 * @功能说明：桌面维护
 * @作者： xgyin
 * @创建日期： 2010-12-22
 */

public class DesktopDefendBean implements Serializable {
	private static final long serialVersionUID = -6831126713364797283L;
	private String id;
	//区域业务名称
	private String title;
	//具体业务放置的DIV标签的ID;
	private String divId;
	//备注
	private String remark;
	//删除多条记录时用
	private String deleteIDs;
	//放置位置 1:左边2:右边
	private String defaultPosition = "1";

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

	public String getDeleteIDs() {
		return deleteIDs;
	}

	public void setDeleteIDs(String deleteIDs) {
		this.deleteIDs = deleteIDs;
	}

	public String getDefaultPosition() {
		return defaultPosition;
	}

	public void setDefaultPosition(String defaultPosition) {
		this.defaultPosition = defaultPosition;
	}

}
