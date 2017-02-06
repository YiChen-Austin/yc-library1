package com.mall.web.admin.system.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.web.admin.common.utils.BaseAction;
import com.mall.web.admin.security.domain.SysMenu;
import com.mall.web.admin.security.domain.SysResources;
import com.mall.web.admin.security.domain.SysRole;
import com.mall.web.admin.security.service.SysResourceService;
import com.mall.web.admin.security.service.SysRoleService;

/**
 * @功能说明：页面资源选择
 * @作者： 聂庆童
 * @创建日期： 2010-6-10 @
 */

@Controller
@RequestMapping("/admin/")
public class ResourceAction extends BaseAction {
	private static Logger logger = Logger.getLogger(ResourceAction.class);

	@Resource(name = "sysResourceService")
	private SysResourceService sysResourceservice;
	@Resource(name = "sysRoleService")
	private SysRoleService sysRoleService;

	/**
	 * 通过页面ID查找组件资源
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findResourcesByPage")
	@ResponseBody
	public Map<String, Object> findResourcesByPage(SysMenu sysmenu)
			throws Exception {
		logger.debug("~~~~~~~~~~findResourcesByPage~~~~~~~~~~");
		logger.debug(sysmenu.getId());

		List<String> li = sysResourceservice.findPageBySysMenu(sysmenu.getId());
		List<SysResources> reslist = new ArrayList<SysResources>();
		for (SysResources res : sysResourceservice.findResourceByPage(li)) {
			SysResources re = new SysResources();
			re.setId(res.getId());
			re.setName(res.getName());
			re.setRemark(res.getRemark());
			re.setDescription(res.getDescription());
			reslist.add(re);
		}
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("reslist", reslist);
		return context;
	}

	/**
	 * 修改角色组件权限
	 * 
	 * @return
	 * @throws FrameworkException
	 * @throws Exception
	 */
	@RequestMapping("updatePower")
	public ModelAndView updatePower(SysMenu sysmenu, SysRole sysrole, String res)
			throws Exception {
		logger.debug("~~~~~~~~~~updatePower~~~~~~~~~~");
		String[] re = res.split(",");
		List<String> li = new ArrayList<String>();
		List<SysResources> reslist = new ArrayList<SysResources>();
		// 判断选择权限是否为空
		if (res.equals("")) {
			// 选择权限为空的情况
			sysmenu = sysResourceservice.findMenuByID(sysmenu.getId());
			li = sysResourceservice.findPageBySysMenu(sysmenu.getId());
			reslist = sysResourceservice.findResourceByPage(li);
			sysRoleService.removeResources(sysrole.getId(), reslist);
		} else {
			// 选择权限不为空的情况
			sysmenu = sysResourceservice.findMenuByID(sysmenu.getId());
			li = sysResourceservice.findPageBySysMenu(sysmenu.getId());
			reslist = sysResourceservice.findResourceByPage(li);
			sysRoleService.setResources(sysrole.getId(), re, reslist);
		}
		return forword("system/resources", null);
	}

	/**
	 * 查询角色对应组件
	 * 
	 * @return
	 * @throws FrameworkException
	 * @throws Exception
	 */
	@RequestMapping("selectResources")
	@ResponseBody
	public Map<String, Object> selectResources(SysRole sysrole)
			throws Exception {
		logger.debug("~~~~~~~~~~selectResources~~~~~~~~~~");
		// 获取需要选中的资源 (有权限的资源)
		List<SysResources> reslist = sysResourceservice.selectPower(sysrole
				.getId());
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("reslist", reslist);
		return context;
	}

}
