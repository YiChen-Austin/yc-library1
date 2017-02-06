/**
 * 
 */
package com.mall.web.admin.common.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.web.admin.common.domain.SysShortcutMenu;
import com.mall.web.admin.security.domain.SysMenu;
import com.mall.web.admin.security.domain.SysRole;
import com.mall.web.admin.security.domain.SysUser;


/** 
 * @功能说明:快捷菜单数据访问层
 * @author: 刘庆海
 * @创建日期: 2010-6-7
 */
@Repository("sysShortcutMenuDao")
public class SysShortcutMenuDao extends BaseDao<SysShortcutMenu> {
	/**
	 * 根据用户id 获取该用户的权限菜单
	 * @param id
	 * @return
	 */
	public List<SysMenu> queryRightsMenus(SysUser user) {
		List<SysRole> roles = user.getSysRoles();
		//存放用户权限菜单
		Set<SysMenu> menus = new HashSet<SysMenu>();
		for (SysRole sysRole : roles) {
			List<SysMenu> tempMenus = sysRole.getSysMenus();
			for (SysMenu sysMenu : tempMenus) {
				menus.add(sysMenu);
			}
		}
		List<SysMenu> list = new ArrayList<SysMenu>();
		Iterator<SysMenu> Imenus = menus.iterator();
		while (Imenus.hasNext()) {
			SysMenu sysMenu = Imenus.next();
			list.add(sysMenu);
		}
		return list;
	}
}
