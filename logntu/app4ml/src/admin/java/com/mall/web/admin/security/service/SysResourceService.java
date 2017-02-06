package com.mall.web.admin.security.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.util.BaseUtil;
import com.mall.web.admin.security.dao.SysMenuDao;
import com.mall.web.admin.security.dao.SysPageResDao;
import com.mall.web.admin.security.dao.SysResourcesDao;
import com.mall.web.admin.security.dao.SysRoleDao;
import com.mall.web.admin.security.domain.SysMenu;
import com.mall.web.admin.security.domain.SysPageRes;
import com.mall.web.admin.security.domain.SysResources;
import com.mall.web.admin.security.domain.SysRole;
import com.mall.web.common.dynamicds.DataSource;

/**
 * @页面资源管理
 * @author: 聂庆童
 * @创建日期: 2010-6-8
 */
@Service("sysResourceService")
public class SysResourceService {
	@Resource(name = "sysResourcesDao")
	private SysResourcesDao sysResourcesDao;
	@Resource(name = "sysPageResDao")
	private SysPageResDao sysPageResDao;
	@Resource(name = "sysRoleDao")
	private SysRoleDao sysRoleDao;
	@Resource(name = "sysMenuDao")
	private SysMenuDao sysMenuDao;

	/**
	 * 查询指点页面的页面组件
	 * 
	 * @param pageres_id
	 * @return
	 * @throws Exception
	 */
	@DataSource(value = "admin")
	public List<SysResources> findResourceByPage(List<String> pageres_id)
			throws Exception {
		List<SysResources> list = new ArrayList<SysResources>();
		List<SysResources> li = new ArrayList<SysResources>();
		for (String st : pageres_id) {
			li = this.sysResourcesDao.findResourceByPage(st);
			for (SysResources res : li) {
				list.add(res);
			}

		}
		return list;
	}

	/**
	 * 查询指点菜单的页面
	 * 
	 * @param SysMenuID
	 * @return
	 * @throws Exception
	 */
	@DataSource(value = "admin")
	public List<String> findPageBySysMenu(String SysMenuID) throws Exception {
		List<String> list = this.sysPageResDao.findPageBySysMenu(SysMenuID);
		return list;
	}

	/**
	 * 修改组件权限
	 * 
	 * @param role_id
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@DataSource(value = "admin")
	public void updatePower(String role_id, String res_id) throws Exception {
		SysResources resources = new SysResources();
		resources = this.sysResourcesDao.get(res_id);
		List<SysRole> role_list = resources.getSysRoles();
		SysRole sysrole = this.sysRoleDao.get(role_id);
		for (SysRole role : role_list) {
			resources.getSysRoles().remove(role);
		}
		sysrole.getResources().add(resources);
		resources.getSysRoles().add(sysrole);
		this.sysResourcesDao.update(resources);
	}

	/**
	 * 根据ID查询页面组件
	 * 
	 * @param resid
	 * @return
	 * @throws Exception
	 */
	@DataSource(value = "admin")
	public List<SysResources> findResourcesByID(String[] resid)
			throws Exception {
		List<SysResources> list = new ArrayList<SysResources>();
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < resid.length; i++) {
			List<SysResources> li = new ArrayList<SysResources>();
			map.put("id", resid[i]);
			li = this.sysResourcesDao.findAllEntitiesByCondition(map);
			for (SysResources res : li) {
				list.add(res);
			}
		}
		return list;
	}

	/**
	 * 修改权限跳转
	 * 
	 * @param role_id
	 * @param list
	 * @throws Exception
	 */
	@DataSource(value = "admin")
	public void updateChanger(String role_id, List<SysResources> list)
			throws Exception {
		for (SysResources res : list) {
			this.updatePower(role_id, res.getId());
		}
	}

	/**
	 * 获取已有权限的组件ID
	 * 
	 * @param role_id
	 * @return
	 * @throws Exception
	 */
	@DataSource(value = "admin")
	public List<SysResources> selectPower(String role_id) throws Exception {
		SysRole sysrole = this.sysRoleDao.get(role_id);
		List<SysResources> resources = sysrole.getResources();
		List<SysResources> res = new ArrayList<SysResources>();
		for (SysResources resource : resources) {
			SysResources re = new SysResources();
			re.setId(resource.getId());
			res.add(re);
		}
		return res;
	}

	/**
	 * 通过页面资源获取菜单
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	@DataSource(value = "admin")
	public SysMenu findMenuByID(String sysmenuid) throws Exception {
		SysMenu sysmenu = this.sysMenuDao.get(sysmenuid);
		return sysmenu;
	}

	/**
	 * 判断用户是否具有按钮权限
	 * 
	 * @param userId
	 * @param pageId
	 * @param resouceId
	 */
	@DataSource(value = "admin")
	public int getResouceCount(String userId, String pageId, String resourceId) {
		if (BaseUtil.isEmpty(pageId))
			return sysResourcesDao.getResouceCount(userId, resourceId);
		else
			return sysResourcesDao.getResouceCount(userId, pageId, resourceId);
	}

	/**
	 * @throws Exception
	 * @category 初始化(按钮)资源注入系统
	 * 
	 */
	@DataSource(value = "admin")
	@Transactional
	public void initResouceButton(String[] resourceId, String[] resourceName,
			String[] visiles, String jspUrl) throws Exception {
		SysPageRes pageRes = sysPageResDao.findPageByPageId(jspUrl);
		if (pageRes == null)
			return;
		for (int i = 0; i < resourceId.length; i++) {
			List<SysResources> list = sysResourcesDao.findResource(
					pageRes.getId(), resourceId[i], resourceName[i]);
			if (list.size() > 0)
				continue;
			SysResources resources = new SysResources();
			resources.setName(resourceName[i]);
			resources.setRes_id(resourceId[i]);
			resources.setDescription(resourceName[i]);
			resources.setVisile(visiles[i]);
			resources.setRemark(resourceName[i]);
			resources.setPageres(pageRes);
			sysResourcesDao.save(resources);
		}
	}

	/**
	 * @throws Exception
	 * @category 初始化(jsp)资源注入系统
	 * 
	 */
	@DataSource(value = "admin")
	@Transactional
	public void initResoucePage(String pMenuId, String menuUrl,
			String description, String jspUrl) throws Exception {
		SysPageRes pageRes = sysPageResDao.findPageByPageId(jspUrl);
		if (pageRes != null
				|| (BaseUtil.isEmpty(pMenuId) && BaseUtil.isEmpty(menuUrl)))
			return;
		List<SysMenu> list = sysMenuDao.findDirectSubSysMenus(pMenuId, menuUrl);
		if (list.size() <= 0)
			return;
		pageRes = new SysPageRes();
		pageRes.setMenu(list.get(0));
		pageRes.setPage_id(jspUrl);
		pageRes.setDescription(description);
		sysPageResDao.save(pageRes);
	}
}
