/**
 * 
 */
package com.mall.web.admin.system.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.common.exception.FrameworkException;
import com.mall.web.admin.common.annotation.Auth;
import com.mall.web.admin.common.utils.BaseAction;
import com.mall.web.admin.common.utils.SessionUtils;
import com.mall.web.admin.common.utils.TreeUtil;
import com.mall.web.admin.security.domain.SysMenu;
import com.mall.web.admin.security.vo.SysMngUserLoginBean;
import com.mall.web.admin.security.vo.TreeNodeBean;
import com.mall.web.admin.system.service.SysShortcutMenuService;

/**
 * @功能说明:用户自定义快捷菜单控制层
 * @author: 刘庆海
 * @创建日期: 2010-6-7
 */

@Controller
@RequestMapping("/admin/")
public class ShortcutMenuAction extends BaseAction {
	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private SysShortcutMenuService sysShortcutMenuService;

	/**
	 * 显示树形快捷菜单
	 * 
	 * @return
	 * @throws FrameworkException
	 *             ,Exception
	 */
	@Auth(verifyLogin = true,verifyURL=false)
	@RequestMapping("shortcutMenu")
	@ResponseBody
	public Map<String, Object> shortcutMenu(HttpServletRequest request)
			throws FrameworkException, Exception {
		// 登陆用户信息
		SysMngUserLoginBean loginUser = SessionUtils.getUser(request);

		// 获取该用户能操作的菜单
		Map<String, Object> menu = sysShortcutMenuService
				.selectTreeMenu(loginUser);

		List<SysMenu> list = loginUser.getMenuList();
		// 获取该用户已经添加的快捷菜单
		List<SysMenu> lists = (List<SysMenu>) menu.get("rightCutMenus");
		// 向页面传送数据
		List<Map<String, String>> mainMenuList = new ArrayList<Map<String, String>>();
		for (SysMenu sysmenu : lists) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("menuID", sysmenu.getId());
			mainMenuList.add(map);
		}
		TreeNodeBean treenodebean = TreeUtil.convertMenuTreeNode(list);

		Map<String, Object> context = new HashMap<String, Object>();
		context.put("data", treenodebean.getData());
		context.put("children", treenodebean.getChildren());
		context.put("attributes", treenodebean.getAttributes());
		context.put("mainMenuList", mainMenuList);
		return context;
	}

	/**
	 * 自定义快捷方式
	 * 
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@Auth(verifyLogin = true)
	@RequestMapping("addmenu")
	@ResponseBody
	public String addShortcutMenu(HttpServletRequest request, String ids)
			throws FrameworkException {
		// 登陆用户信息
		SysMngUserLoginBean loginUser = SessionUtils.getUser(request);
		sysShortcutMenuService.addMenu(ids, loginUser.getId());
		return SUCCESS;
	}

	/**
	 * 显示快捷方式
	 * 
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@Auth(verifyLogin = true,verifyURL=false)
	@RequestMapping("selecshortcutMenu")
	@ResponseBody
	public Map<String, Object> selectShortcutMenu(HttpServletRequest request)
			throws FrameworkException {
		// 登陆用户信息
		SysMngUserLoginBean loginUser = SessionUtils.getUser(request);
		Map<String, Object> menu = sysShortcutMenuService
				.selectTreeMenu(loginUser);
		// 获取该用户已经添加的快捷菜单
		List<SysMenu> lists = (List<SysMenu>) menu.get("rightCutMenus");
		// 向页面传送数据
		List<Map<String, String>> mainMenuList = new ArrayList<Map<String, String>>();
		for (SysMenu sysmenu : lists) {

			Map<String, String> map = new HashMap<String, String>();
			map.put("menuName", sysmenu.getName());
			map.put("menuUrl", sysmenu.getPageUrl());

			mainMenuList.add(map);
		}

		Map<String, Object> context = new HashMap<String, Object>();
		context.put("mainMenuList", mainMenuList);

		return context;
	}
}