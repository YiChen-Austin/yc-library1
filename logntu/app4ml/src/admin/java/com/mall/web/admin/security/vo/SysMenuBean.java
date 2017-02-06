/**
 * 
 */
package com.mall.web.admin.security.vo;

import java.io.Serializable;

/**
 * @功能说明：系统菜单VO对象
 * @作者： 印鲜刚
 * @创建日期： 2010-5-15
 * @
 */
public class SysMenuBean implements Serializable {
	private static final long serialVersionUID = -4325266559871305360L;
	private String id;
	//菜单名称
	private String name;
	//菜单备注
	private String remark;
	//链接地址
	private String pageUrl;
	//显示顺序号
	private Integer orderNo;
	//菜单节点图片
	private String nodeImage;
	//父Id
	private String parentId;
	private String flag;
	private String internalCommand;

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
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getInternalCommand() {
		return internalCommand;
	}

	public void setInternalCommand(String internalCommand) {
		this.internalCommand = internalCommand;
	}

	public String getNodeImage() {
		return nodeImage;
	}

	public void setNodeImage(String nodeImage) {
		this.nodeImage = nodeImage;
	}

}
