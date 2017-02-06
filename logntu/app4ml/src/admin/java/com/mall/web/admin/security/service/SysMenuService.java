/**
 * 
 */
package com.mall.web.admin.security.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.mall.web.admin.security.dao.SysRoleDao;
import com.mall.web.admin.security.domain.SysMenu;
import com.mall.web.admin.security.domain.SysPageRes;
import com.mall.web.admin.security.domain.SysRole;
import com.mall.web.common.dynamicds.DataSource;



/**
 * @功能说明：系统菜单业务层
 * @作者： 印鲜刚
 * @创建日期： 2010-5-8 @
 */
@Service("sysMenuService")
public class SysMenuService {
	@Resource(name = "sysMenuDao")
	private SysMenuDao sysMenuDao;
	@Resource(name = "sysRoleDao")
	private SysRoleDao sysRoleDao;
	@Resource(name = "sysResourcesRegService")
	private SysResourcesRegService sysResourcesRegService;
	

	/**
	 * 通过菜单parentId获取其所有直接子菜单集合
	 * 
	 * @param parentId
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public List<SysMenu> findDirectSubSysMenus(String parentId) throws FrameworkException {
		return this.sysMenuDao.findDirectSubSysMenus(parentId);
	}

	/**
	 * 修改菜单
	 * 
	 * @param sysMenu
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void modifySysMenu(SysMenu sysMenu) throws FrameworkException {
		this.sysMenuDao.update(sysMenu);
	}

	/**
	 * 通过菜单ID删除菜单实体及其所有直接和间接子菜单实体
	 * 
	 * @param sysMenuId
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void deleteAllSysMenusById(String sysMenuId) throws FrameworkException {
		this.deleteSubSysMenusById(sysMenuId);
		//取得将要删除的菜单
		SysMenu sysMenu = sysMenuDao.get(sysMenuId);
		//解除与资源表之间的关联
		if(sysMenu.getPageres().size()>0){
			for(SysPageRes pageRes:sysMenu.getPageres()){
				//修改为多页面才能进行删除
				sysMenu.setInternalCommand("1");
				sysResourcesRegService.delJspUrl(pageRes.getId());
			}
		}
		this.deleteSysMenuById(sysMenuId);
	}

	/**
	 * 通过菜单ID获取菜单实体
	 * 
	 * @param sysMenuId
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysMenu findSysMenuById(String sysMenuId) throws FrameworkException {
		return this.sysMenuDao.get(sysMenuId);
	}

	/**
	 * 通过菜单ID递归删除所有子节点
	 * 
	 * @param sysMenuId
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	private void deleteSubSysMenusById(String sysMenuId) throws FrameworkException {
		List<SysMenu> subSysMenus = this.sysMenuDao.findDirectSubSysMenus(sysMenuId);
		for (SysMenu sysMenu : subSysMenus) {
			this.deleteSubSysMenusById(sysMenu.getId());
			List<SysRole> sysRoles = sysMenu.getSysRoles();
			for (SysRole sysRole : sysRoles) {
				sysRole.getSysMenus().remove(sysMenu);
			}
			 //如果已被资源注册关联过，需要先接触资源注册的关联
			if(sysMenu.getPageres().size()>0){
				for(SysPageRes pageRes:sysMenu.getPageres()){
					//修改为多页面才能进行修改删除
					sysMenu.setInternalCommand("1");
					sysResourcesRegService.delJspUrl(pageRes.getId());
				}
			}
			
			this.sysMenuDao.delete(sysMenu);
		}
	}

	/**
	 * 通过菜单ID删除菜单实体
	 * 
	 * @param sysMenuId
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	private void deleteSysMenuById(String sysMenuId) throws FrameworkException {
		SysMenu sysMenu = this.findSysMenuById(sysMenuId);
		int orderNo = sysMenu.getOrderNo();
		SysMenu parentSysMenu = sysMenu.getSysMenu();
		List<SysMenu> sysMenus = parentSysMenu.getSysMenus();
		for (SysMenu childSysMenu : sysMenus) {
			int oldOrderNo = childSysMenu.getOrderNo();
			// 如果兄弟节点的orderNo比新插入节点的orderNo大，就将原来的orderNo加1
			if (oldOrderNo > orderNo) {
				int newOrderNo = oldOrderNo - 1;
				childSysMenu.setOrderNo(newOrderNo);
				this.modifySysMenu(childSysMenu);
			}
		}
		List<SysRole> sysRoles = sysMenu.getSysRoles();
		for (SysRole sysRole : sysRoles) {
			sysRole.getSysMenus().remove(sysMenu);
		}
		this.sysMenuDao.delete(sysMenu);
	}

	/**
	 * 根据条件查询菜单实体
	 * 
	 * @param params
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public List<SysMenu> findAllSysMenus(Map<String, Object> params) throws FrameworkException {
		return this.sysMenuDao.findAllSysMenus(params);
	}

	/**
	 * 根据条件统计菜单总数
	 * 
	 * @param params
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public int getSysMenuTotalRows(Map<String, Object> params) throws FrameworkException {
		return this.sysMenuDao.getSysMenuTotalRows(params);
	}

	/**
	 * 根据菜单ID设置角色
	 * 
	 * @param sysMenuId
	 * @param sysRoleIds
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public SysMenu setSysRole(String sysMenuId, String[] sysRoleIds) throws FrameworkException {
		SysMenu sysMenu = this.findSysMenuById(sysMenuId);
		List<SysRole> sysRoles = sysMenu.getSysRoles();
		for (SysRole sysRole : sysRoles) {
			sysRole.getSysMenus().remove(sysMenu);
		}
		for (String sysRoleId : sysRoleIds) {
			SysRole sysRole = this.sysRoleDao.get(sysRoleId);
			sysRole.getSysMenus().add(sysMenu);
			sysMenu.getSysRoles().add(sysRole);
		}
		this.modifySysMenu(sysMenu);
		return sysMenu;
	}

	/**
	 * 同级菜单新增功能
	 * 
	 * @param sysMenu
	 * @param selectId
	 */
	@DataSource(value = "admin")
	@Transactional
	public void sameSaveSysMenu(SysMenu sysMenu, String selectId) throws FrameworkException {
		// 选中菜单节点
		SysMenu selectSysMenu = this.findSysMenuById(selectId);
		// 父菜单
		SysMenu parentSysMenu = selectSysMenu.getSysMenu();
		sysMenu.setSysMenu(parentSysMenu);
		// 选中菜单节点的排序号
		Integer orderNo = selectSysMenu.getOrderNo();
		sysMenu.setOrderNo(orderNo + 1);
		List<SysMenu> sysMenus = this.findDirectSubSysMenus(parentSysMenu.getId());
		for (SysMenu childSysMenu : sysMenus) {
			if (childSysMenu.getId().equalsIgnoreCase(selectSysMenu.getId()))
				continue;
			int oldOrderNo = childSysMenu.getOrderNo();
			if (oldOrderNo >= orderNo) {
				int newOrderNo = oldOrderNo + 1;
				childSysMenu.setOrderNo(newOrderNo);
				this.modifySysMenu(childSysMenu);
			}
		}
		// 添加菜单
		this.sysMenuDao.save(sysMenu);

	}

	/**
	 * 次级菜单新增功能
	 * 
	 * @param sysMenu
	 * @param selectId
	 */
	@DataSource(value = "admin")
	@Transactional
	public void secondarySaveSysMenu(SysMenu sysMenu, String selectId) throws FrameworkException {
		// 选中菜单节点作为新增菜单节点的父节点
		SysMenu selectSysMenu = this.findSysMenuById(selectId);
		sysMenu.setSysMenu(selectSysMenu);
		List<SysMenu> sysMenus = this.findDirectSubSysMenus(selectSysMenu.getId());
		int size = sysMenus.size();
		if (sysMenus.size() > 0) {
			sysMenus = orderSort(sysMenus);
			sysMenu.setOrderNo(sysMenus.get(size - 1).getOrderNo() + 1);
		} else {
			sysMenu.setOrderNo(1);
		}
		// 添加菜单
		this.sysMenuDao.save(sysMenu);

	}

	/**
	 * 获取头部菜单列表
	 * 
	 * @param loginUserId
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public List<SysMenu> findOneLayerSysMenuByLoginUserId(String loginUserId) throws FrameworkException {
		SysMenu root = this.findDirectSubSysMenus(null).get(0);
		return this.sysMenuDao.findSubSysMenusByLoginUserId(root.getId(), loginUserId);
	}

	/**
	 * 根据菜单ID获取菜单及其子菜单
	 * 
	 * @param sysMenuId
	 * @param loginUserId
	 * @return
	 */
	@DataSource(value = "admin")
	public List<SysMenu> findSysMenuAndSubSysMenus(String sysMenuId, String loginUserId) throws FrameworkException {
		List<SysMenu> sysMenus = this.findAllSubSysMenus(sysMenuId, loginUserId);
		sysMenus.add(this.sysMenuDao.get(sysMenuId));
		return sysMenus;
	}

	/**
	 * 根据菜单ID获取所有直接及间接子菜单
	 * 
	 * @param sysMenuId
	 * @param loginUserId
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public List<SysMenu> findAllSubSysMenus(String sysMenuId, String loginUserId) throws FrameworkException {
		List<SysMenu> tempSysMenus = new ArrayList<SysMenu>();
		List<SysMenu> sysMenus = this.sysMenuDao.findSubSysMenusByLoginUserId(sysMenuId, loginUserId);
		for (SysMenu sysMenu : sysMenus) {
			tempSysMenus.addAll(this.findAllSubSysMenus(sysMenu.getId(), loginUserId));
		}
		tempSysMenus.addAll(sysMenus);
		return tempSysMenus;
	}

	/**
	 * 调整菜单位置
	 * 
	 * @param oldId
	 * @param newId
	 * @param type
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public boolean moveMenu(String oldId, String newId, String type) throws FrameworkException {
		boolean flag = false;
		// 原菜单对象
		SysMenu oldSysMenu = this.sysMenuDao.get(oldId);
		// 新菜单对象
		SysMenu newSysMenu = this.sysMenuDao.get(newId);
		// 非法提交校验
		if (BaseUtil.isEmpty(newSysMenu.getSysMenu().getId())) {
			return flag;
		}
		// 插入后节点的位置
		int nowOrder = 0;
		// 重构满足条件的节点集合 new
		List<SysMenu> newList = new ArrayList<SysMenu>();

		nowOrder = getNewNodes(newSysMenu, type, newList);

		if ("inside".equals(type) == true) {
			oldSysMenu.setSysMenu(newSysMenu);
		} else {
			oldSysMenu.setSysMenu(newSysMenu.getSysMenu());
		}

		// 批量更新，满足条件的节点所有顺序号 +1
		this.sysMenuDao.updateAll(newList, 1);

		oldSysMenu.setOrderNo(nowOrder);
		this.sysMenuDao.update(oldSysMenu);

		flag = true;
		return flag;
	}

	/**
	 * 根据目标菜单，移动的类型，保存结果集，返回新节点的位置
	 * @param newSysMenu
	 * @param type
	 * @param newList
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public int getNewNodes(SysMenu newSysMenu, String type, List<SysMenu> newList) throws FrameworkException {
		//新节点的位置
		int nowOrder = 0;

		// 子节点的集合
		List<SysMenu> subList = new ArrayList<SysMenu>();

		// 取得父节点ID，如果不等于 inside 就说明是在同级节点更改
		String newParentId = "";
		if ("inside".equals(type) == false) {
			newParentId = newSysMenu.getParentId();
			int order = 0;
			if ("after".equals(type)) {
				nowOrder = newSysMenu.getOrderNo() + 1;
				order = 0;
			} else {
				nowOrder = newSysMenu.getOrderNo();
				order = -1;
			}

			List<SysMenu> newNodeList = this.sysMenuDao.getSubAllNode(newParentId);
			for (SysMenu sysMenu : newNodeList) {
				if (sysMenu.getOrderNo() > newSysMenu.getOrderNo() + order) {
					newList.add(sysMenu);
				}
			}

		} else {
			// 说明父节点ID就为new节点的ID
			newParentId = newSysMenu.getId();
			// 取出该节点的所有节点
			subList = this.sysMenuDao.getSubAllNode(newParentId);
			// 集合排序
			subList = orderSort(subList);
			if (subList.size() > 0) {
				nowOrder = subList.get(subList.size() - 1).getOrderNo() + 1;
			} else {
				nowOrder = 0;
			}
		}

		return nowOrder;
	}

	/**
	 * 排序功能
	 * 
	 * @param list
	 * @return
	 */
	@DataSource(value = "admin")
	private List<SysMenu> orderSort(List<SysMenu> list) {

		Collections.sort(list, new Comparator<SysMenu>() {
			public int compare(SysMenu arg0, SysMenu arg1) {
				Integer oldseat = (arg0.getOrderNo());
				Integer newseat = (arg1.getOrderNo());
				return oldseat.compareTo(newseat);
			}
		});
		return list;
	}

	/**
	 * 根据菜单名字查找菜单
	 * @param name
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysMenu findSysMenuByName(String name) throws FrameworkException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		List<SysMenu> sysMenus = this.sysMenuDao.findAllEntitiesByCondition(params);
		if (!BaseUtil.isEmpty(sysMenus) && sysMenus.size() > 0)
			return sysMenus.get(0);
		return null;

	}

	/**
	 * 根据菜单ID及名字查找菜单
	 * @param id
	 * @param name
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysMenu findSysMenuByIdAndName(String id, String name) throws FrameworkException {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("name", name);
		params.put("AND__id__ne", id);
		List<SysMenu> sysMenus = this.sysMenuDao.findAllEntitiesByCondition(params);
		if (!BaseUtil.isEmpty(sysMenus) && sysMenus.size() > 0)
			return sysMenus.get(0);
		return null;
	}
}
