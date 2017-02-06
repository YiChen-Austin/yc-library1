/**
 * 
 */
package com.mall.web.admin.category.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @功能说明：系统菜单VO对象
 * @作者： 印鲜刚
 * @创建日期： 2010-5-15 @
 */
public class BaseCategoryBean implements Serializable {
	private static final long serialVersionUID = -4325266559871305360L;
	private String id;
	// 菜单名称
	private String name;
	// 菜单备注
	private String remark;
	// 链接地址
	private String pageUrl;
	// 显示顺序号
	private Integer orderNo;
	// 菜单节点图片
	private String nodeImage;
	// 父Id
	private String parentId;
	private String parentName;
	private String flag;
	private String internalCommand;
	/******************************/
    private List<BaseCategoryBean> listBaseCategoryBean=null;
    
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

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getNodeImage() {
		return nodeImage;
	}

	public void setNodeImage(String nodeImage) {
		this.nodeImage = nodeImage;
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

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<BaseCategoryBean> getListBaseCategoryBean() {
		return listBaseCategoryBean;
	}

	public void setListBaseCategoryBean(List<BaseCategoryBean> listBaseCategoryBean) {
		this.listBaseCategoryBean = listBaseCategoryBean;
	}
	
}
