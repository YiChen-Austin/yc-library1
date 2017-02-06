/**
 * 
 */
package com.mall.web.admin.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.security.dao.SysOrganizationDao;
import com.mall.web.admin.security.dao.SysRoleDao;
import com.mall.web.admin.security.dao.SysUserDao;
import com.mall.web.admin.security.domain.SysOrganization;
import com.mall.web.admin.security.domain.SysRole;
import com.mall.web.admin.security.domain.SysUser;
import com.mall.web.common.dynamicds.DataSource;

/**
 * @功能说明：地区字典业务层
 * @作者： xgyin
 * @创建日期： 2010-10-17 @
 */
@Service("sysAreaDictionaryService")
public class SysAreaDictionaryService {
	private static Logger logger = Logger
			.getLogger(SysAreaDictionaryService.class);
	@Resource(name = "sysUserDao")
	private SysUserDao sysUserDao;
	@Resource(name = "sysOrganizationDao")
	private SysOrganizationDao sysOrganizationDao;
	@Resource(name = "sysRoleDao")
	private SysRoleDao sysRoleDao;


	/**
	 * 根据用户ID获取公司信息
	 * 
	 * @param loginUserId
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysOrganization findBranchByLoginUserId(String loginUserId)
			throws FrameworkException {
		SysUser sysUser = this.sysUserDao.get(loginUserId);
		List<SysOrganization> loginSysOrgans = sysUser.getSysOrganizations();
		if (BaseUtil.isEmpty(loginSysOrgans) || loginSysOrgans.size() == 0)
			return null;
		return loginSysOrgans.get(0);
		// SysOrganization sysOrgan = loginSysOrgans.get(0);
		// if (BaseUtil.isEmpty(sysOrgan.getSysOrganization()))
		// return null;
		// else {
		// List<SysOrganization> sysOrgans = sysOrgan.getSysOrganizations();
		// if ((!BaseUtil.isEmpty(sysOrgans) && sysOrgans.size() > 0)
		// ||
		// (BaseUtil.isEmpty(sysOrgan.getSysOrganization().getSysOrganization())))
		// {
		// return null;
		// } else {
		// return sysOrgan;
		// }
		// }
	}

	/**
	 * 根据登录用户ID获取公司类别,电信，移动，联通 ，共建共享办公室，通信管理局
	 * 
	 * 特别说明,这是共建共享机构(而非驻地网)
	 * 
	 * @param loginUserId
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysOrganization findCommpanByLoginUserId(String loginUserId)
			throws FrameworkException {
		SysUser sysUser = this.sysUserDao.get(loginUserId);
		List<SysOrganization> loginSysOrgans = sysUser.getSysOrganizations();
		if (BaseUtil.isEmpty(loginSysOrgans) || loginSysOrgans.size() == 0)
			return null;
		SysOrganization sysOrgan = loginSysOrgans.get(0);
		if (BaseUtil.isEmpty(sysOrgan.getSysOrganization()))
			return sysOrgan;
		else {
			List<SysOrganization> sysOrgans = sysOrgan.getSysOrganizations();
			if ((!BaseUtil.isEmpty(sysOrgans) && sysOrgans.size() > 0)
					|| (BaseUtil.isEmpty(sysOrgan.getSysOrganization()
							.getSysOrganization()))) {
				return sysOrgan;

			} else
				return sysOrgan.getSysOrganization();
		}
		// List<SysOrganization> loginSysOrgans = sysOrganizationDao
		// .findSysOrganizationUnZdwByUserId(loginUserId);
		// return loginSysOrgans.size() > 0 ? loginSysOrgans.get(0) : null;
	}


	/**
	 * 根据角色名称获取角色
	 * 
	 * @param roleName
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysRole findSysRole(String roleName) throws FrameworkException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", roleName);
		List<SysRole> sysRoles = this.sysRoleDao
				.findAllEntitiesByCondition(params);
		if (BaseUtil.isEmpty(sysRoles) || sysRoles.size() == 0)
			return null;
		return sysRoles.get(0);

	}



	/**
	 * 根据登录名获取组织机构
	 * 
	 * @param loginUserId
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysOrganization findSysOrganization(String loginUserId)
			throws FrameworkException {
		SysUser sysUser = this.sysUserDao.get(loginUserId);
		if (BaseUtil.isEmpty(sysUser))
			return null;
		List<SysOrganization> sysOrgs = sysUser.getSysOrganizations();
		if (BaseUtil.isEmpty(sysOrgs) || sysOrgs.size() == 0)
			return null;
		return sysOrgs.get(0);

	}

	/**
	 * 通过地区角色代码获取用户信息
	 * 
	 * @param areaRoleCode
	 *            -地区角色代码
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public String findMessageUsersByAreaRoldCode(String areaRoleCode)
			throws FrameworkException {
		String[] areaRole = areaRoleCode.split("_");
		if (areaRole.length != 2)
			throw new FrameworkException("不正确的输入参数!");
		String areaCode = areaRole[0];
		String roleCode = areaRole[1];
		Map<String, Object> params = new HashMap<String, Object>();
		if (areaCode.equals("00000") || areaCode.equals("11111")) {
			params.put("roleCode", roleCode);
			params.put("onlyRoleCode", "onlyRoleCode");
		} else {
			params.put("areaCode", areaCode);
			params.put("roleCode", roleCode);
		}
		List<SysUser> sysUsers = this.sysUserDao
				.findSysUsersByCondition(params);
		String sysUserIds = "";
		for (SysUser sysUser : sysUsers) {
			sysUserIds += sysUser.getId() + ",";
		}
		if (!BaseUtil.isEmpty(sysUserIds))
			sysUserIds = sysUserIds.substring(0, sysUserIds.length() - 1);
		return sysUserIds;

	}

	/**
	 * 获取市公司及设计院组织机构
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public List<SysOrganization> findCompanyAndDesignInstitute()
			throws FrameworkException {
		return this.sysOrganizationDao.findCompanyAndDesignInstitute();

	}
	
}
