/**
 * 
 */
package com.mall.web.admin.system.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.HqlUtil;
import com.mall.common.vo.PageBean;
import com.mall.web.admin.common.utils.BaseAction;
import com.mall.web.admin.common.utils.SessionUtils;
import com.mall.web.admin.security.domain.SysMenu;
import com.mall.web.admin.security.domain.SysRole;
import com.mall.web.admin.security.service.SysRoleService;
import com.mall.web.admin.security.vo.SysMenuBean;
import com.mall.web.admin.security.vo.SysMngUserLoginBean;
import com.mall.web.admin.security.vo.SysRoleBean;

/**
 * @功能说明：系统角色控制层
 * @作者： 印鲜刚
 * @创建日期： 2010-5-9 @
 */

@Controller
@RequestMapping("/admin/")
public class SysRoleAction extends BaseAction {
	private static Logger logger = Logger.getLogger(SysRoleAction.class);
	@Autowired
	private SysRoleService sysRoleService;

	/**
	 * 进入角色添加页面
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("createSysRole")
	public ModelAndView createSysRole() throws FrameworkException {
		return forword("system/sys_role_detail", null);
	}

	/**
	 * 添加角色
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("saveSysRole")
	public ModelAndView saveSysRole(SysRoleBean sysRoleBean)
			throws FrameworkException {
		if (!BaseUtil.isEmpty(sysRoleService.findSysRoleByName(sysRoleBean
				.getName()))) {
			Map<String, Object> context = new HashMap<String, Object>();
			context.put("name", "已有该角色名称!");
			return forword("system/sys_role_detail", context);
		}
		SysRole sysRole = new SysRole();
		BeanUtils.copyProperties(sysRoleBean, sysRole);
		sysRole.setFlag("1");
		sysRoleService.saveSysRole(sysRole);
		return forword("system/sys_role_list", null);
	}

	/**
	 * 修改角色
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("modifySysRole")
	public ModelAndView modifySysRole(SysRoleBean sysRoleBean)
			throws FrameworkException {
		if (!BaseUtil.isEmpty(sysRoleService.findSysRoleByIdAndName(
				sysRoleBean.getId(), sysRoleBean.getName()))) {
			Map<String, Object> context = new HashMap<String, Object>();
			context.put("name", "已有该角色名称!");
			return forword("system/sys_role_modify", context);
		}
		SysRole sysRole = sysRoleService.findSysRoleById(sysRoleBean.getId());
		BeanUtils.copyProperties(sysRoleBean, sysRole);
		sysRoleService.modifySysRole(sysRole);
		return forword("system/sys_role_list", null);
	}

	/**
	 * 通过角色ID获取获取角色信息
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("findSysRole")
	public ModelAndView findSysRole(SysRoleBean sysRoleBean)
			throws FrameworkException {
		SysRole sysRole = sysRoleService.findSysRoleById(sysRoleBean.getId());
		BeanUtils.copyProperties(sysRole, sysRoleBean);

		Map<String, Object> context = new HashMap<String, Object>();
		context.put("sysRoleBean", sysRoleBean);
		return forword("system/sys_role_modify", context);
	}

	/**
	 * 通过角色ID删除角色信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("deleteSysRole")
	@ResponseBody
	public Map<String, Object> deleteSysRole(HttpServletRequest request,
			SysRoleBean sysRoleBean, PageBean pageBean) throws Exception {
		sysRoleService.deleteSysRoleById(sysRoleBean.getId());
		return findAllSysRoles(request, sysRoleBean, pageBean);
	}

	/**
	 * 通过多个角色ID，系统删除多角色信息
	 * 
	 * @throws Exception
	 */
	@RequestMapping("deleteSysRoles")
	@ResponseBody
	public Map<String, Object> deleteSysRoles(HttpServletRequest request,
			SysRoleBean sysRoleBean, PageBean pageBean) throws Exception {
		sysRoleService.deleteSysRoles(sysRoleBean.getDeleteIDs().split(","));
		return findAllSysRoles(request, sysRoleBean, pageBean);
	}

	/**
	 * 分公司查询角色
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("findBranchSysRoles")
	@ResponseBody
	public Map<String, Object> findBranchSysRoles(HttpServletRequest request,
			SysRoleBean sysRoleBean, PageBean pageBean)
			throws FrameworkException, Exception {
		SysMngUserLoginBean sysUserLoginBean = SessionUtils.getUser(request);
		Map<String, Object> searchCondition = new TreeMap<String, Object>();
		// 判断是否关联系统人员查询
		if (sysUserLoginBean.getFlag().equals("1")) {
			searchCondition.put("sysUser", sysUserLoginBean.getId());
		}
		List<SysRole> sysRoles = sysRoleService.findBranchSysRoles(
				searchCondition, sysUserLoginBean.getOrganizationName());
		List<SysRoleBean> sysRoleBeans = new ArrayList<SysRoleBean>();
		for (SysRole sysRole : sysRoles) {
			SysRoleBean bean = new SysRoleBean();
			BeanUtils.copyProperties(sysRole, bean);
			sysRoleBeans.add(bean);
		}
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("sysRoleBeans", sysRoleBeans);
		context.put("pageBean", pageBean);
		return context;
	}

	/**
	 * 查询角色
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("findAllSysRoles")
	@ResponseBody
	public Map<String, Object> findAllSysRoles(HttpServletRequest request,
			SysRoleBean sysRoleBean, PageBean pageBean)
			throws FrameworkException, Exception {
		SysMngUserLoginBean sysUserLoginBean = SessionUtils.getUser(request);

		// jqGrid查询转码-utf-8
		BaseUtil.decodeObject(sysRoleBean);
		Map<String, Object> searchCondition = HqlUtil.beanToMap(sysRoleBean,
				SysRole.class);
		// 判断是否关联系统人员查询
		if (sysUserLoginBean.getFlag().equals("1")) {
			searchCondition = BaseUtil.isEmpty(searchCondition) ? new TreeMap<String, Object>()
					: searchCondition;
			searchCondition.put("sysUser", sysUserLoginBean.getId());
		}
		pageBean.init(sysRoleService.getSysRoleTotalRows(searchCondition));
		// 判断是否关联系统人员查询
		if (sysUserLoginBean.getFlag().equals("1")) {
			searchCondition.put("sysUser", sysUserLoginBean.getId());
		}
		List<SysRole> sysRoles = sysRoleService.findAllSysRoles(
				searchCondition, pageBean.getCurPage(), pageBean.getPageSize());
		List<SysRoleBean> sysRoleBeans = new ArrayList<SysRoleBean>();
		for (SysRole sysRole : sysRoles) {
			SysRoleBean bean = new SysRoleBean();
			BeanUtils.copyProperties(sysRole, bean);
			sysRoleBeans.add(bean);
		}
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("sysRoleBeans", sysRoleBeans);
		context.put("pageBean", pageBean);
		return context;
	}

	/**
	 * 根据角色ID设置系统菜单
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("setSysMenu")
	@ResponseBody
	public Map<String, Object> setSysMenu(SysRoleBean sysRoleBean)
			throws FrameworkException {
		sysRoleService.setSysMenu(sysRoleBean.getId(), BaseUtil
				.isEmpty(sysRoleBean.getSysMenuIDs()) ? null : sysRoleBean
				.getSysMenuIDs().split(","));

		Map<String, Object> context = new HashMap<String, Object>();
		context.put("result", true);
		return context;
	}

	/**
	 * 通过角色ID设置人员
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("setSysUser")
	@ResponseBody
	public String setSysUser() throws FrameworkException {
		// sysRoleService.setSysUser(sysRoleBean.getId(),
		// sysRoleBean.getSysUserIDs());
		return SUCCESS;
	}

	/**
	 * 通过角色ID获取角色及其关联菜单信息
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("findSysMenusBySysRoleId")
	@ResponseBody
	public Map<String, Object> findSysMenusBySysRoleId(SysRoleBean sysRoleBean)
			throws FrameworkException {
		SysRole sysRole = sysRoleService.findSysRoleById(sysRoleBean.getId());
		BeanUtils.copyProperties(sysRole, sysRoleBean);
		List<SysMenu> sysMenus = sysRole.getSysMenus();
		List<SysMenuBean> sysMenuBeans = new ArrayList<SysMenuBean>();
		for (SysMenu sysMenu : sysMenus) {
			SysMenuBean sysMenuBean = new SysMenuBean();
			BeanUtils.copyProperties(sysMenu, sysMenuBean);
			sysMenuBeans.add(sysMenuBean);
		}
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("sysMenuBeans", sysMenuBeans);
		context.put("sysRoleBean", sysRoleBean);
		return context;
	}

}
