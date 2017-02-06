/**
 * 
 */
package com.mall.web.admin.security.vo;

import java.io.Serializable;

/**
 * @功能说明：组织机构VO对象
 * @作者： 印鲜刚
 * @创建日期： 2010-5-18
 * @
 */
public class SysOrganizationBean implements Serializable {
	private static final long serialVersionUID = -3041206328456151432L;
	private String id;
	//组织机构名称
	private String name;
	//组织机构简称
	private String shortName;
	//组织机构备注
	private String remark;
	//组织机构号
	private String organizationNumber;
	//显示顺序号
	private Integer orderNo;
	//父Id
	private String parentId;
	//根据组织机构设置人员ID信息
	private String sysUserIDs;
	//组织机构节点图片
	private String nodeImage;

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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrganizationNumber() {
		return organizationNumber;
	}

	public void setOrganizationNumber(String organizationNumber) {
		this.organizationNumber = organizationNumber;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getSysUserIDs() {
		return sysUserIDs;
	}

	public void setSysUserIDs(String sysUserIDs) {
		this.sysUserIDs = sysUserIDs;
	}

	public String getNodeImage() {
		return nodeImage;
	}

	public void setNodeImage(String nodeImage) {
		this.nodeImage = nodeImage;
	}

}
