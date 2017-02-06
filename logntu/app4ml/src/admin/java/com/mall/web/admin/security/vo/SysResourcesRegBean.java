package com.mall.web.admin.security.vo;

import java.io.Serializable;



/**
 * @功能说明：系统资源注册VO
 * @作者： 练书锋	
 * @创建日期： 2010-6-8
 * @
 */
@SuppressWarnings("serial")
public class SysResourcesRegBean implements Serializable{
	//资源id
	private String id;
	//资源名
	private String name;
	//资源描述
	private String description;
	//资源备注
	private String remark;
	//资源名ID 或组件的ID
	private String name_id;
	

	
	
	
	public String getName_id() {
		return name_id;
	}
	public void setName_id(String nameId) {
		name_id = nameId;
	}
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
