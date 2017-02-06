/**
 * 
 */
package com.mall.web.admin.security.vo;

import java.io.Serializable;


/**
 * @功能：系统字典VO对象
 * @作者：印鲜刚
 * @创建日期：2010-04-20
 */
public class SysDictionaryBean implements Serializable {
	private static final long serialVersionUID = 5828497739119290624L;
	private String id;
	private String name;
	private String value;
	private String remark;
	private String cnName;
	private Character flag;
	//删除记录时用
	private String deleteIDs;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Character getFlag() {
		return flag;
	}

	public void setFlag(Character flag) {
		this.flag = flag;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getDeleteIDs() {
		return deleteIDs;
	}

	public void setDeleteIDs(String deleteIDs) {
		this.deleteIDs = deleteIDs;
	}

}
