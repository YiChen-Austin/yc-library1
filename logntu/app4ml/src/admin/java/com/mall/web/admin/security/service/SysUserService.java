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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DESUtil;
import com.mall.web.admin.security.dao.SysOrganizationDao;
import com.mall.web.admin.security.dao.SysRoleDao;
import com.mall.web.admin.security.dao.SysUserDao;
import com.mall.web.admin.security.domain.SysMenu;
import com.mall.web.admin.security.domain.SysOrganization;
import com.mall.web.admin.security.domain.SysRole;
import com.mall.web.admin.security.domain.SysUser;
import com.mall.web.common.dynamicds.DataSource;

/**
 * @功能说明：系统人员业务层
 * @作者： 印鲜刚
 * @创建日期： 2010-5-6 @
 */
@Service("sysUserService")
public class SysUserService {
	@Resource(name = "sysUserDao")
	private SysUserDao sysUserDao;
	@Resource(name = "sysOrganizationDao")
	private SysOrganizationDao sysOrganizationDao;
	@Resource(name = "sysRoleDao")
	private SysRoleDao sysRoleDao;

	/**
	 * 通过角色代码获取相关人员ID
	 * 
	 * @param code
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public String findSysUserBySysRoleCode(String code)
			throws FrameworkException {
		List<SysUser> sysUsers = this.sysUserDao.findSysUserBySysRoleCode(code);
		String sysUserIds = "";
		for (SysUser sysUser : sysUsers) {
			sysUserIds += sysUser.getId() + ",";
		}
		if (!BaseUtil.isEmpty(sysUserIds))
			sysUserIds = sysUserIds.substring(0, sysUserIds.length() - 1);
		return sysUserIds;

	}

	/**
	 * 操作员登录
	 * 
	 * @param params
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional(readOnly=true)
	public Map<String, SysUser> login(Map<String, Object> params)
			throws FrameworkException {
		return this.sysUserDao.login(params);
	}

	@DataSource(value = "admin")
	public Map<String, SysUser> login(String userName, String password)
			throws FrameworkException {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("userName", userName);
		params.put("password", password);
		return this.sysUserDao.login(params);
	}

	/**
	 * 添加人员
	 * 
	 * @param sysUser
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void saveSysUser(SysUser sysUser) throws FrameworkException {
		this.sysUserDao.save(sysUser);
	}

	/**
	 * 修改人员
	 * 
	 * @param sysUser
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void modifySysUser(SysUser sysUser) throws FrameworkException {
		this.sysUserDao.update(sysUser);
	}

	/**
	 * 通过人员ID获取人员实体
	 * 
	 * @param sysUserId
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysUser findSysUserById(String sysUserId) throws FrameworkException {
		return this.sysUserDao.get(sysUserId);
	}

	/**
	 * 通过人员ID删除人员实体
	 * 
	 * @param sysUserId
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void deleteSysUserById(String sysUserId) throws FrameworkException {
		SysUser sysUser = this.findSysUserById(sysUserId);
		List<SysOrganization> sysOrganizations = sysUser.getSysOrganizations();
		for (SysOrganization sysOrganization : sysOrganizations) {
			sysOrganization.getSysUsers().remove(sysUser);
		}
		List<SysRole> sysRoles = sysUser.getSysRoles();
		for (SysRole sysRole : sysRoles) {
			sysRole.getSysUsers().remove(sysUser);
		}
		this.sysUserDao.delete(sysUser);
	}

	/**
	 * 通过多人员ID删除多人员实体
	 * 
	 * @param sysUserIds
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void deleteSysUsers(String[] sysUserIds) throws FrameworkException {
		for (String sysUserId : sysUserIds) {
			// this.deleteSysUserById(sysUserId);
			SysUser sysUser = this.findSysUserById(sysUserId);
			sysUser.setDisabled(false);
			this.modifySysUser(sysUser);

		}
	}

	/**
	 * 根据条件查询人员
	 * 
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public List<SysUser> findAllSysUsers(Map<String, Object> params,
			int currentPage, int pageSize) throws FrameworkException {
		return this.sysUserDao.findAllSysUsers(params, currentPage, pageSize);

	}

	/**
	 * 根据条件统计人员总数
	 * 
	 * @param params
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public int getSysUserTotalRows(Map<String, Object> params)
			throws FrameworkException {
		return this.sysUserDao.getSysUserTotalRows(params);

	}

	/**
	 * 根据人员ID设置机构
	 * 
	 * @param sysUserId
	 * @param sysOrganizationIds
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public SysUser setSysOrganization(String sysUserId,
			String[] sysOrganizationIds) throws FrameworkException {
		SysUser sysUser = this.findSysUserById(sysUserId);
		List<SysOrganization> sysOrganizations = sysUser.getSysOrganizations();
		for (SysOrganization sysOrganization : sysOrganizations) {
			sysOrganization.getSysUsers().remove(sysUser);
		}
		for (String sysOrganizationId : sysOrganizationIds) {
			SysOrganization sysOrganization = this.sysOrganizationDao
					.get(sysOrganizationId);
			sysOrganization.getSysUsers().add(sysUser);
			sysUser.getSysOrganizations().add(sysOrganization);
		}
		this.modifySysUser(sysUser);
		return sysUser;
	}

	/**
	 * 根据人员ID设置权限
	 * 
	 * @param sysUserId
	 * @param sysRoleIds
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public SysUser setSysRole(String sysUserId, String[] sysRoleIds)
			throws FrameworkException {
		SysUser sysUser = this.findSysUserById(sysUserId);
		List<SysRole> sysRoles = sysUser.getSysRoles();
		for (SysRole sysRole : sysRoles) {
			sysRole.getSysUsers().remove(sysUser);
		}
		if (!BaseUtil.isEmpty(sysRoleIds)) {
			for (String sysRoleId : sysRoleIds) {
				SysRole sysRole = this.sysRoleDao.get(sysRoleId);
				sysRole.getSysUsers().add(sysUser);
				sysUser.getSysRoles().add(sysRole);
			}
		}
		this.modifySysUser(sysUser);
		return sysUser;
	}

	/**
	 * 通过组织机构ID查询未与该组织机构关联的人员总数
	 * 
	 * @param params
	 * @return
	 */
	@DataSource(value = "admin")
	public int getNotSelectedSysUserTotalRows(Map<String, Object> params)
			throws FrameworkException {

		return this.sysUserDao.getNotSelectedSysUserTotalRows(params);
	}

	/**
	 * 通过组织机构ID查询未与该组织机构关联的人员
	 * 
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	@DataSource(value = "admin")
	public List<SysUser> findNotSelectedSysUsers(Map<String, Object> params,
			int currentPage, int pageSize) throws FrameworkException {
		return this.sysUserDao.findNotSelectedSysUsers(params, currentPage,
				pageSize);
	}

	/**
	 * 通过用户名查找用户
	 * 
	 * @param userName
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysUser findSysUserByName(String userName) throws FrameworkException {
		if (BaseUtil.isEmpty(userName))
			return null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", userName);
		params.put("disabled", true);
		List<SysUser> sysUsers = this.sysUserDao
				.findAllEntitiesByCondition(params);
		if (!BaseUtil.isEmpty(sysUsers) && sysUsers.size() > 0)
			return sysUsers.get(0);
		return null;
	}

	/**
	 * 通过用户ID及用户名查找用户
	 * 
	 * @param id
	 * @param userName
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public Object findSysUserByIdAndName(String id, String userName)
			throws FrameworkException {
		if (BaseUtil.isEmpty(userName))
			return null;
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("userName", userName);
		params.put("disabled", true);
		params.put("AND__id__ne", id);
		List<SysUser> sysUsers = this.sysUserDao
				.findAllEntitiesByCondition(params);
		if (!BaseUtil.isEmpty(sysUsers) && sysUsers.size() > 0)
			return sysUsers.get(0);
		return null;
	}

	/**
	 * 根据用户id 获取该用户的权限菜单
	 * 
	 * @param id
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional(readOnly=true)
	public List<SysMenu> queryRightsMenus(String id) throws FrameworkException {
		return this.sysUserDao.queryRightsMenus(id);
	}

	/**
	 * 恢复初始密码
	 * 
	 * @return
	 * @throws Exception
	 */
	@DataSource(value = "admin")
	@Transactional
	public void initPassword(String[] sysUserIds) throws Exception {
		for (String sysUserId : sysUserIds) {
			SysUser sysUser = this.findSysUserById(sysUserId);
			sysUser.setPassword(DESUtil.getInstance().encrypt("111111"));
			this.sysUserDao.update(sysUser);
		}

	}

	/**
	 * 通过用户ID获取该用户所在的组织机构及其直接或间接子组织机构
	 * 
	 * @param loginUserId
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public String[] findSysOrganizationIdsByLoginUserId(String loginUserId)
			throws FrameworkException {
		List<SysOrganization> tempSo = new ArrayList<SysOrganization>();
		SysUser sysUser = this.sysUserDao.get(loginUserId);
		List<SysOrganization> sysOrganizations = sysUser.getSysOrganizations();
		for (SysOrganization so : sysOrganizations) {
			tempSo.addAll(this.findAllSubSysOrganizations(so));
		}
		tempSo.addAll(sysOrganizations);
		int len = tempSo.size();
		String[] tempIds = new String[len];
		int i = 0;
		for (SysOrganization so : tempSo) {
			tempIds[i] = so.getId();
			i++;
		}
		return tempIds;
	}

	/**
	 * 通过用户ID获取该用户所在的组织机构及其直接或间接子组织机构
	 * 
	 * @param loginUserId
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public List<SysOrganization> findSysOrganizationsByLoginUserId(
			String loginUserId) throws FrameworkException {
		List<SysOrganization> tempSo = new ArrayList<SysOrganization>();
		SysUser sysUser = this.sysUserDao.get(loginUserId);
		List<SysOrganization> sysOrganizations = sysUser.getSysOrganizations();
		for (SysOrganization so : sysOrganizations) {
			tempSo.addAll(this.findAllSubSysOrganizations(so));
		}
		tempSo.addAll(sysOrganizations);
		return tempSo;
	}

	/**
	 * 根据组织机构获取所有直接或间接子组织机构
	 * 
	 * @param sysOrganization
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public List<SysOrganization> findAllSubSysOrganizations(
			SysOrganization sysOrganization) throws FrameworkException {
		List<SysOrganization> tempSysOrganizations = new ArrayList<SysOrganization>();
		List<SysOrganization> sysOrganizations = sysOrganization
				.getSysOrganizations();
		for (SysOrganization so : sysOrganizations) {
			tempSysOrganizations.addAll(this.findAllSubSysOrganizations(so));
		}
		tempSysOrganizations.addAll(sysOrganizations);
		return tempSysOrganizations;
	}

	/**
	 * 通过登录用户ID获取该用户所拥有的角色信息
	 * 
	 * @param loginUserId
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public List<SysRole> findAllSysRolesByLoginUserId(String loginUserId)
			throws FrameworkException {
		SysUser sysUser = this.sysUserDao.get(loginUserId);
		return sysUser.getSysRoles();
	}

	/**
	 * 修改用户
	 * 
	 * @param sysUser
	 * @param sysOrganizationIDs
	 * @param sysRoleIDs
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void modifySysUserByCondition(SysUser sysUser,
			String sysOrganizationIDs, String sysRoleIDs)
			throws FrameworkException {
		if (!BaseUtil.isEmpty(sysOrganizationIDs)) {
			String[] orgIds = sysOrganizationIDs.split(",");
			for (String orgId : orgIds) {
				SysOrganization org = this.sysOrganizationDao.get(orgId);
				org.getSysUsers().add(sysUser);
				sysUser.getSysOrganizations().add(org);
			}
		}
		if (!BaseUtil.isEmpty(sysRoleIDs)) {
			String[] roleIds = sysRoleIDs.split(",");
			for (String roleId : roleIds) {
				SysRole role = this.sysRoleDao.get(roleId);
				role.getSysUsers().add(sysUser);
				sysUser.getSysRoles().add(role);
			}
		}
		this.modifySysUser(sysUser);

	}

	/**
	 * 
	 * @param sysUserId
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void removeSysOrgAndSysRole(String sysUserId)
			throws FrameworkException {
		this.sysUserDao.removeSysOrgAndSysRole(sysUserId);
	}
}
