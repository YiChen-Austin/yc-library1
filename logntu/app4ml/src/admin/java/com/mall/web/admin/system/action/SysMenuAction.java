/**
 * 
 */
package com.mall.web.admin.system.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.common.utils.BaseAction;
import com.mall.web.admin.common.utils.TreeUtil;
import com.mall.web.admin.security.domain.SysMenu;
import com.mall.web.admin.security.service.SysMenuService;
import com.mall.web.admin.security.vo.SysMenuBean;
import com.mall.web.admin.security.vo.TreeNodeBean;

/**
 * @功能说明：菜单管理控制层
 * @作者： 印鲜刚
 * @创建日期： 2010-5-15 @
 */
@Controller
@RequestMapping("/admin/")
public class SysMenuAction extends BaseAction {
	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	private SysMenuService sysMenuService;

	/**
	 * 显示树形菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findAllSysMenus")
	@ResponseBody
	public Map<String, Object> findAllSysMenus() throws FrameworkException,
			Exception {
		List<SysMenu> sysMenus = sysMenuService.findAllSysMenus(null);
		TreeNodeBean treeNodeBean = TreeUtil.convertMenuTreeNode(sysMenus);
		List<TreeNodeBean> children = treeNodeBean.getChildren();
		// 父节点
		String data = treeNodeBean.getData();
		Map<String, String> attributes = treeNodeBean.getAttributes();

		Map<String, Object> context = new HashMap<String, Object>();
		context.put("data", data);
		context.put("attributes", attributes);
		context.put("children", children);
		return context;
	}

	/**
	 * 修改菜单功能
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("modifySysMenu")
	@ResponseBody
	public Map<String, Object> modifySysMenu(SysMenuBean sysMenuBean)
			throws FrameworkException {
		String newId = null;
		if (!BaseUtil.isEmpty(sysMenuService.findSysMenuByIdAndName(
				sysMenuBean.getId(), sysMenuBean.getName()))) {
			newId = "none";

			Map<String, Object> context = new HashMap<String, Object>();
			context.put("newId", newId);
			return context;
		}
		if (BaseUtil.isEmpty(sysMenuBean.getInternalCommand())) {
			sysMenuBean.setInternalCommand("0");
		}

		logger.debug("getInternalCommand:\t" + sysMenuBean.getInternalCommand());
		SysMenu sysMenu = sysMenuService.findSysMenuById(sysMenuBean.getId());
		sysMenuBean.setOrderNo(sysMenu.getOrderNo());
		sysMenuBean.setFlag(sysMenu.getFlag());
		BeanUtils.copyProperties(sysMenuBean, sysMenu);
		sysMenuService.modifySysMenu(sysMenu);

		Map<String, Object> context = new HashMap<String, Object>();
		context.put("result", true);
		context.put("newId", newId);
		return context;
	}

	/**
	 * 同级菜单新增功能
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("sameSaveSysMenu")
	@ResponseBody
	public Map<String, Object> sameSaveSysMenu(SysMenuBean sysMenuBean,
			String selectId) throws FrameworkException {
		String newId = null;
		if (!BaseUtil.isEmpty(sysMenuService.findSysMenuByName(sysMenuBean
				.getName()))) {
			newId = "none";
			Map<String, Object> context = new HashMap<String, Object>();
			context.put("newId", newId);
			return context;
		}
		if (BaseUtil.isEmpty(sysMenuBean.getInternalCommand())) {
			sysMenuBean.setInternalCommand("0");
		}
		SysMenu sysMenu = new SysMenu();
		BeanUtils.copyProperties(sysMenuBean, sysMenu);
		sysMenu.setFlag("1");
		logger.debug("selectId=" + selectId);
		sysMenuService.sameSaveSysMenu(sysMenu, selectId);
		newId = sysMenu.getId();
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("result", true);
		context.put("newId", newId);
		return context;
	}

	/**
	 * 次级菜单新增功能
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("secondarySaveSysMenu")
	@ResponseBody
	public Map<String, Object> secondarySaveSysMenu(SysMenuBean sysMenuBean,
			String selectId) throws FrameworkException {
		String newId = null;
		if (!BaseUtil.isEmpty(sysMenuService.findSysMenuByName(sysMenuBean
				.getName()))) {
			newId = "none";
			Map<String, Object> context = new HashMap<String, Object>();
			context.put("newId", newId);
			return context;
		}
		if (BaseUtil.isEmpty(sysMenuBean.getInternalCommand())) {
			sysMenuBean.setInternalCommand("0");
		}
		SysMenu sysMenu = new SysMenu();
		BeanUtils.copyProperties(sysMenuBean, sysMenu);
		sysMenu.setFlag("1");
		logger.debug("selectId=" + selectId);
		sysMenuService.secondarySaveSysMenu(sysMenu, selectId);
		newId = sysMenu.getId();

		Map<String, Object> context = new HashMap<String, Object>();
		context.put("result", true);
		context.put("newId", newId);
		return context;
	}

	/**
	 * 菜单树删除功能
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("deleteSysMenu")
	@ResponseBody
	public Map<String, Object> deleteSysMenu(SysMenuBean sysMenuBean)
			throws FrameworkException {
		sysMenuService.deleteAllSysMenusById(sysMenuBean.getId());
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("result", true);
		return context;

	}

	/**
	 * 菜单树移动功能
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("moveSysMenu")
	@ResponseBody
	public Map<String, Object> moveSysMenu(String oldNodeId, String newNodeId,
			String changeType) throws FrameworkException {
		boolean result = false;
		logger.debug("→→→↓↓→→拖动测试←←↓↓←←←←←");
		logger.debug("oldid:" + oldNodeId);
		logger.debug("newid:" + newNodeId);
		logger.debug("TYPE:" + changeType);
		logger.debug("→→→↑↑→→拖动测试←←↑↑←←←←←");
		// 做校验，判断变更类型
		if ("inside".equals(changeType) == false
				&& "before".equals(changeType) == false
				&& "after".equals(changeType) == false) {
			Map<String, Object> context = new HashMap<String, Object>();
			context.put("result", false);
			return context;
		}

		result = sysMenuService.moveMenu(oldNodeId, newNodeId, changeType);
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("result", result);
		return context;

	}
}
