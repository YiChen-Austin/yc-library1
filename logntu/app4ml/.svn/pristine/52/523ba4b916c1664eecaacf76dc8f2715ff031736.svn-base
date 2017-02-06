/**
 * 
 */
package com.mall.web.admin.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.web.admin.common.dao.SysShortcutMenuDao;
import com.mall.web.admin.common.domain.SysShortcutMenu;
import com.mall.web.admin.security.dao.SysMenuDao;
import com.mall.web.admin.security.dao.SysUserDao;
import com.mall.web.admin.security.domain.SysMenu;
import com.mall.web.admin.security.domain.SysUser;
import com.mall.web.admin.security.vo.SysMngUserLoginBean;
import com.mall.web.common.dynamicds.DataSource;


/** 
 * @功能说明:快捷菜单业务层
 * @author: 刘庆海
 * @创建日期: 2010-6-7
 */
@Service("sysShortcutMenuService")
public class SysShortcutMenuService {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SysShortcutMenuService.class);
	@Resource(name = "sysShortcutMenuDao")
	private SysShortcutMenuDao sysShortcutMenuDao;
	@Resource(name = "sysUserDao")
	private SysUserDao sysUserDao;

	@Resource(name = "sysMenuDao")
	private SysMenuDao sysMenuDao;

	/*
	  * 查询菜单树
	  */
	@DataSource(value = "admin")
	@Transactional
	public Map<String, Object> selectTreeMenu(SysMngUserLoginBean loginUser) throws FrameworkException {
		//存放用户的权限菜单和快捷菜单信息
		Map<String, Object> menu = new HashMap<String, Object>();
		//获取用户
		SysUser user = this.sysUserDao.get(loginUser.getId());
		//存放用户权限菜单
		List<SysMenu> menus = this.sysShortcutMenuDao.queryRightsMenus(user);

		//查询快捷菜单
		List<SysShortcutMenu> list = user.getSysShortcutMenus();

		//存放权限快捷菜单相应的菜单信息
		List<SysMenu> rightsCutMenus = new ArrayList<SysMenu>();

		//存放在快捷菜单中,但没有权限的快捷菜单
		List<SysShortcutMenu> noRightsCutMenus = new ArrayList<SysShortcutMenu>();

		for (SysShortcutMenu sysShortcutMenu : list) {
			if (menus.contains(sysShortcutMenu.getSysMenu())) {
				rightsCutMenus.add(sysShortcutMenu.getSysMenu());
			} else {
				noRightsCutMenus.add(sysShortcutMenu);

			}
		}
		//存放有权限的快捷菜单信息
		menu.put("rightCutMenus", rightsCutMenus);
		//删除没有权限的快捷菜单
		list.removeAll(noRightsCutMenus);
		for (SysShortcutMenu sysShortcutMenu : noRightsCutMenus) {
			this.sysShortcutMenuDao.delete(sysShortcutMenu);
		}
		user.setSysShortcutMenus(list);
		this.sysUserDao.update(user);

		return menu;
	}

	/*
	 * 自定义快捷菜单
	 */
	@DataSource(value = "admin")
	@Transactional
	public void addMenu(String ids, String userid) throws FrameworkException {
		SysUser user = this.sysUserDao.get(userid);

		//截取字符串，获取ID
		String[] str = ids.split(";", (ids.length() - 2) / 3 + 1);

		user.getSysShortcutMenus().clear();
		for (String id : str) {
			SysMenu sysMenu = this.sysMenuDao.get(id);
			SysShortcutMenu shortcut = new SysShortcutMenu();
			shortcut.setSysMenu(sysMenu);
			user.getSysShortcutMenus().add(shortcut);
		}
		this.sysUserDao.update(user);

	}
}
