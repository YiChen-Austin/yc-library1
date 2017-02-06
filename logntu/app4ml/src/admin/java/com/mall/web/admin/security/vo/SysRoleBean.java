/**
 * 
 */
package com.mall.web.admin.security.vo;

import java.io.Serializable;

/**
 * @功能说明：系统角色VO对象
 * @作者： 印鲜刚
 * @创建日期： 2010-5-9
 * @
 */
public class SysRoleBean implements Serializable {
	private static final long serialVersionUID = 1820507106996987829L;
	private String id;
	//删除多条记录时用
	private String deleteIDs;
	//角色名称
	private String name;
	//角色代码
	private String code;
	//角色备注
	private String remark;
	//当前页面
	private Integer currentPage;
	//每页记录数
	private Integer pageSize;
	//设置角色包含菜单ID信息
	private String sysMenuIDs;
	//设置角色包含人员ID信息
	private String sysUserIDs;
	private String flag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeleteIDs() {
		return deleteIDs;
	}

	public void setDeleteIDs(String deleteIDs) {
		this.deleteIDs = deleteIDs;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getSysMenuIDs() {
		return sysMenuIDs;
	}

	public void setSysMenuIDs(String sysMenuIDs) {
		this.sysMenuIDs = sysMenuIDs;
	}

	public String getSysUserIDs() {
		return sysUserIDs;
	}

	public void setSysUserIDs(String sysUserIDs) {
		this.sysUserIDs = sysUserIDs;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
