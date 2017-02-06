/**
 * 
 */
package com.mall.web.admin.security.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.security.dao.SysMenuDao;
import com.mall.web.admin.security.dao.SysResourcesDao;
import com.mall.web.admin.security.dao.SysRoleDao;
import com.mall.web.admin.security.dao.SysUserDao;
import com.mall.web.admin.security.domain.SysMenu;
import com.mall.web.admin.security.domain.SysResources;
import com.mall.web.admin.security.domain.SysRole;
import com.mall.web.admin.security.domain.SysUser;
import com.mall.web.common.dynamicds.DataSource;

/**
 * @功能说明：系统角色业务层
 * @作者： 印鲜刚
 * @创建日期： 2010-5-6 @
 */
@Service("sysRoleService")
public class SysRoleService {
	@Resource(name = "sysRoleDao")
	private SysRoleDao sysRoleDao;
	@Resource(name = "sysMenuDao")
	private SysMenuDao sysMenuDao;
	@Resource(name = "sysUserDao")
	private SysUserDao sysUserDao;
	@Resource(name = "sysResourcesDao")
	private SysResourcesDao sysResourcesDao;

	/**
	 * 添加角色
	 * 
	 * @param sysRole
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void saveSysRole(SysRole sysRole) throws FrameworkException {
		this.sysRoleDao.save(sysRole);

	}

	/**
	 * 修改角色
	 * 
	 * @param sysRole
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void modifySysRole(SysRole sysRole) throws FrameworkException {
		this.sysRoleDao.update(sysRole);
	}

	/**
	 * 通过角色ID获取角色实体
	 * 
	 * @param sysRoleId
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysRole findSysRoleById(String sysRoleId) throws FrameworkException {
		return this.sysRoleDao.get(sysRoleId);
	}

	/**
	 * 通过角色ID删除角色实体
	 * 
	 * @param sysRoleId
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void deleteSysRoleById(String sysRoleId) throws FrameworkException {
		SysRole sysRole = this.findSysRoleById(sysRoleId);
		List<SysMenu> sysMenus = sysRole.getSysMenus();
		for (SysMenu sysMenu : sysMenus) {
			sysMenu.getSysRoles().remove(sysRole);
		}
		List<SysUser> sysUsers = sysRole.getSysUsers();
		for (SysUser sysUser : sysUsers) {
			sysUser.getSysRoles().remove(sysRole);
		}
		this.sysRoleDao.delete(sysRole);
	}

	/**
	 * 通过多角色ID删除多角色实体
	 * 
	 * @param sysRoleIds
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void deleteSysRoles(String[] sysRoleIds) throws FrameworkException {
		for (String sysRoleId : sysRoleIds) {
			this.deleteSysRoleById(sysRoleId);
		}
	}

	/**
	 * 根据条件查询角色
	 * 
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public List<SysRole> findAllSysRoles(Map<String, Object> params,
			int currentPage, int pageSize) throws FrameworkException {
		return this.sysRoleDao.findAllSysRoles(params, currentPage, pageSize);
	}

	/**
	 * 根据条件统计角色总数
	 * 
	 * @param params
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public int getSysRoleTotalRows(Map<String, Object> params)
			throws FrameworkException {
		return this.sysRoleDao.getSysRoleTotalRows(params);
	}

	/**
	 * 根据角色ID设置人员
	 * 
	 * @param sysRoleId
	 * @param sysUserIds
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public SysRole setSysUser(String sysRoleId, String[] sysUserIds)
			throws FrameworkException {
		SysRole sysRole = this.findSysRoleById(sysRoleId);
		List<SysUser> sysUsers = sysRole.getSysUsers();
		for (SysUser sysUser : sysUsers) {
			sysUser.getSysRoles().remove(sysRole);
		}
		for (String sysUserId : sysUserIds) {
			SysUser sysUser = this.sysUserDao.get(sysUserId);
			sysUser.getSysRoles().add(sysRole);
			sysRole.getSysUsers().add(sysUser);
		}
		this.modifySysRole(sysRole);
		return sysRole;

	}

	/**
	 * 根据角色ID设置菜单
	 * 
	 * @param sysRoleId
	 * @param sysMenuIds
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public SysRole setSysMenu(String sysRoleId, String[] sysMenuIds)
			throws FrameworkException {
		SysRole sysRole = this.findSysRoleById(sysRoleId);
		List<SysMenu> sysMenus = sysRole.getSysMenus();
		for (SysMenu sysMenu : sysMenus) {
			sysMenu.getSysRoles().remove(sysRole);
		}
		if (!BaseUtil.isEmpty(sysMenuIds)) {
			for (String sysMenuId : sysMenuIds) {
				SysMenu sysMenu = this.sysMenuDao.get(sysMenuId);
				sysMenu.getSysRoles().add(sysRole);
				sysRole.getSysMenus().add(sysMenu);
			}
		}
		this.modifySysRole(sysRole);
		return sysRole;
	}

	/**
	 * 通过角色名字查找角色
	 * 
	 * @param name
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysRole findSysRoleByName(String name) throws FrameworkException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		List<SysRole> sysRoles = this.sysRoleDao
				.findAllEntitiesByCondition(params);
		if (!BaseUtil.isEmpty(sysRoles) && sysRoles.size() > 0)
			return sysRoles.get(0);
		return null;
	}

	/**
	 * 根据角色ID及名字查找角色
	 * 
	 * @param id
	 * @param name
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysRole findSysRoleByIdAndName(String id, String name)
			throws FrameworkException {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("name", name);
		params.put("AND__id__ne", id);
		List<SysRole> sysRoles = this.sysRoleDao
				.findAllEntitiesByCondition(params);
		if (!BaseUtil.isEmpty(sysRoles) && sysRoles.size() > 0)
			return sysRoles.get(0);
		return null;
	}

	/**
	 * 根据角色ID设置页面组件
	 * 
	 * @param sysRoleId
	 * @param sysMenuIds
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public SysRole setResources(String sysRoleId, String[] ResourcesIds,
			List<SysResources> Resources) throws FrameworkException {
		SysRole sysRole = this.findSysRoleById(sysRoleId);
		for (SysResources Resource : Resources) {
			Resource.getSysRoles().remove(sysRole);
			sysRole.getResources().remove(Resource);
		}
		if (!BaseUtil.isEmpty(ResourcesIds)) {
			for (String resourcesID : ResourcesIds) {
				SysResources resources = this.sysResourcesDao.get(resourcesID);
				resources.getSysRoles().add(sysRole);
				sysRole.getResources().add(resources);
			}
		}
		this.modifySysRole(sysRole);
		return sysRole;
	}

	/**
	 * 删除指定菜单的对应的页面资源
	 * 
	 * @param sysRoleId
	 * @param Resources
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void removeResources(String sysRoleId, List<SysResources> Resources)
			throws FrameworkException {
		SysRole sysRole = this.findSysRoleById(sysRoleId);
		for (SysResources Resource : Resources) {
			Resource.getSysRoles().remove(sysRole);
			sysRole.getResources().remove(Resource);
		}
	}

	/**
	 * 分公司查询角色
	 * 
	 * @param orgName
	 * @param searchCondition
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public List<SysRole> findBranchSysRoles(Map<String, Object> params,
			String orgName) throws FrameworkException {
		List<SysRole> sysRoles = new ArrayList<SysRole>();
		sysRoles.addAll(this.sysRoleDao.findBranchSysRoles(params));
		String orgType = "";
		if (!BaseUtil.isEmpty(orgName)) {
			if (orgName.indexOf("电信") > -1)
				orgType = "电信";
			else if (orgName.indexOf("移动") > -1)
				orgType = "移动";
			else if (orgName.indexOf("联通") > -1)
				orgType = "联通";
		}
		if (!BaseUtil.isEmpty(orgType)) {
			sysRoles.addAll(this.sysRoleDao.findOtherTwoSysRoles(orgType));
		}
		return sysRoles;
	}

}
