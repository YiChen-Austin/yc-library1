package com.mall.web.admin.system.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.common.exception.FrameworkException;
import com.mall.web.admin.common.annotation.Auth;
import com.mall.web.admin.common.utils.BaseAction;
import com.mall.web.admin.common.utils.SessionUtils;
import com.mall.web.admin.common.utils.TreeEasyUtil;
import com.mall.web.admin.security.domain.SysMenu;
import com.mall.web.admin.security.service.SysMenuService;
import com.mall.web.admin.security.vo.SysMngUserLoginBean;
import com.mall.web.admin.security.vo.TreeNodeEasyBean;

/**
 * 
 * 功能说明：根据登录用户ID显示该用户首页菜单
 * 
 * @作者： 张可贵 创建日期： 2010-5-13
 */
@Controller
@RequestMapping("/admin/")
public class MenuTreeAction extends BaseAction {
	private static Logger logger = Logger.getLogger(MenuTreeAction.class);

	@Autowired
	private SysMenuService sysMenuService;

	/**
	 * 根据选择的头部菜单，取得其相应的左侧菜单
	 * 
	 * @return
	 */
	@Auth(verifyLogin = true,verifyURL=false)
	@RequestMapping("findLeftMenu/{parentId:.*}")
	@ResponseBody
	public List<TreeNodeEasyBean> findLeftMenu(@PathVariable String parentId,
			HttpServletRequest request) throws FrameworkException, Exception {
		// 登陆用户信息
		SysMngUserLoginBean loginUser = SessionUtils.getUser(request);
		logger.debug("loginUser=" + loginUser.getId());
		List<SysMenu> sysMenus = this.sysMenuService.findSysMenuAndSubSysMenus(
				parentId, loginUser.getId());
		TreeNodeEasyBean treeNodeBean = TreeEasyUtil.convertMenuTreeNode(
				this.sysMenuService.findSysMenuById(parentId), sysMenus);
		// 一级菜单的所有子节点
		List<TreeNodeEasyBean> children = treeNodeBean.getChildren();

		return children;
	}

	/**
	 * 取得头部菜单列表
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@Auth(verifyLogin = true,verifyURL=false)
	@RequestMapping("initTopMenu")
	@ResponseBody
	public  Map<String, Object>initTopMenu(HttpServletRequest request)
			throws FrameworkException {
		Map<String, Object> result = new HashMap<String, Object>();
		// 登陆用户信息
		SysMngUserLoginBean loginUser = SessionUtils.getUser(request);
		// 该用户对应的一级菜单list
		List<Map<String, String>> mainMenuList = new ArrayList<Map<String, String>>();
		List<SysMenu> sysMenus = this.sysMenuService
				.findOneLayerSysMenuByLoginUserId(loginUser.getId());
		for (SysMenu sysMenu : sysMenus) {
			Map<String, String> oneLevel = new HashMap<String, String>();
			oneLevel.put("menuId", sysMenu.getId());
			oneLevel.put("menuName", sysMenu.getName());
			mainMenuList.add(oneLevel);

		}
		result.put("topMenu", mainMenuList);
		result.put("subWeb",loginUser.getOrganizationName());
		return result;
	}

}
