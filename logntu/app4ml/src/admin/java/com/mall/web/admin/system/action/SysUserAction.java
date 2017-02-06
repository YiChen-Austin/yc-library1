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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DESUtil;
import com.mall.common.util.HqlUtil;
import com.mall.common.vo.PageBean;
import com.mall.web.admin.common.utils.BaseAction;
import com.mall.web.admin.common.utils.SessionUtils;
import com.mall.web.admin.common.utils.SystemResourceUtil;
import com.mall.web.admin.security.domain.SysOrganization;
import com.mall.web.admin.security.domain.SysRole;
import com.mall.web.admin.security.domain.SysUser;
import com.mall.web.admin.security.service.SysOrganizationService;
import com.mall.web.admin.security.service.SysRoleService;
import com.mall.web.admin.security.service.SysUserService;
import com.mall.web.admin.security.vo.SysMngUserLoginBean;
import com.mall.web.admin.security.vo.SysRoleBean;
import com.mall.web.admin.security.vo.SysUserBean;
import com.mall.web.admin.system.service.SysAreaDictionaryService;

/**
 * @功能说明：系统用户控制层
 * @作者： 印鲜刚
 * @创建日期： 2010-5-7 @
 */
@Controller
@RequestMapping("/admin/")
public class SysUserAction extends BaseAction {
	private static Logger logger = Logger.getLogger(SysUserAction.class);
	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysOrganizationService sysOrganizationService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysAreaDictionaryService sysAreaDictionaryService;

	// public SysUserAction() throws FrameworkException {
	// genderTag =
	// SystemResourceUtil.getInstance().getResourceAsList("gender");
	// staffKindTag =
	// SystemResourceUtil.getInstance().getResourceAsList("staffKind");
	// }

	/**
	 * 进入用户添加页面
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("createSysUser")
	public ModelAndView createSysUser() throws FrameworkException {
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("genderTag", SystemResourceUtil.getInstance()
				.getResourceAsList("gender"));
		context.put("staffKindTag", SystemResourceUtil.getInstance()
				.getResourceAsList("staffKind"));
		context.put("sysUserBean", new SysUserBean());

		return forword("system/sys_user_detail", context);
	}

	/**
	 * 添加人员
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("saveSysUser")
	public ModelAndView saveSysUser(HttpServletRequest request,
			SysUserBean sysUserBean) throws Exception {
		SysMngUserLoginBean sysUserLoginBean = SessionUtils.getUser(request);
		if (!BaseUtil.isEmpty(sysUserService.findSysUserByName(sysUserBean
				.getUserName()))) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userName", "已有该用户名称!");
			return createSysUser();
		}
		SysUser sysUser = new SysUser();
		if (BaseUtil.isEmpty(sysUserBean.getPassword()))
			sysUserBean.setPassword("111111");
		BeanUtils.copyProperties(sysUserBean, sysUser);
		sysUser.setPassword(DESUtil.getInstance()
				.encrypt(sysUser.getPassword()));
		sysUser.setFlag("1");
		if (!BaseUtil.isEmpty(sysUserBean.getSysOrganizationIDs())) {
			String[] orgIds = sysUserBean.getSysOrganizationIDs().split(",");
			for (String orgId : orgIds) {
				SysOrganization org = sysOrganizationService
						.findSysOrganizationById(orgId);
				org.getSysUsers().add(sysUser);
				sysUser.getSysOrganizations().add(org);
			}
		}
		if (!BaseUtil.isEmpty(sysUserBean.getSysRoleIDs())) {
			String[] roleIds = sysUserBean.getSysRoleIDs().split(",");
			for (String roleId : roleIds) {
				SysRole role = sysRoleService.findSysRoleById(roleId);
				role.getSysUsers().add(sysUser);
				sysUser.getSysRoles().add(role);
			}
		}
		// if (sysUserLoginBean.getFlag().equals("1")) {
		SysUser createUser = sysUserService.findSysUserById(sysUserLoginBean
				.getId());
		SysOrganization createOrg = sysAreaDictionaryService
				.findCommpanByLoginUserId(sysUserLoginBean.getId());
		sysUser.setCreateOrg(createOrg);
		sysUser.setCreateUser(createUser);
		// }
		sysUserService.saveSysUser(sysUser);
		return forword("system/sys_user_list", null);
	}

	/**
	 * 修改人员
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("modifySysUser")
	public ModelAndView modifySysUser(HttpServletRequest request,
			SysUserBean sysUserBean) throws Exception {
		if (!BaseUtil.isEmpty(sysUserService.findSysUserByIdAndName(
				sysUserBean.getId(), sysUserBean.getUserName()))) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userName", "已有该用户名称!");
			return forword("system/sys_user_modify", params);
		}
		sysUserService.removeSysOrgAndSysRole(sysUserBean.getId());
		SysUser sysUser = sysUserService.findSysUserById(sysUserBean.getId());
		if (BaseUtil.isEmpty(sysUserBean.getPassword()))
			sysUserBean.setPassword("111111");
		BeanUtils.copyProperties(sysUserBean, sysUser);
		sysUser.setPassword(DESUtil.getInstance()
				.encrypt(sysUser.getPassword()));
		sysUserService.modifySysUserByCondition(sysUser,
				sysUserBean.getSysOrganizationIDs(),
				sysUserBean.getSysRoleIDs());
		return forword("system/sys_user_list", null);
	}

	/**
	 * 通过人员ID获取获取人员及其关联角色信息
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("findSysRolesBySysUserId")
	@ResponseBody
	public Map<String, Object> findSysRolesBySysUserId(SysUserBean sysUserBean)
			throws FrameworkException {
		SysUser sysUser = sysUserService.findSysUserById(sysUserBean.getId());
		BeanUtils.copyProperties(sysUser, sysUserBean);
		List<SysRole> sysRoles = sysUser.getSysRoles();
		List<SysRoleBean> sysRoleBeans = new ArrayList<SysRoleBean>();
		for (SysRole sysRole : sysRoles) {
			SysRoleBean sysRoleBean = new SysRoleBean();
			BeanUtils.copyProperties(sysRole, sysRoleBean);
			sysRoleBeans.add(sysRoleBean);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sysRoleBeans", sysRoleBeans);
		return params;
	}

	/**
	 * 通过人员ID获取人员信息
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("findSysUser")
	public ModelAndView findSysUser(SysUserBean sysUserBean)
			throws FrameworkException, Exception {
		SysUser sysUser = sysUserService.findSysUserById(sysUserBean.getId());
		BeanUtils.copyProperties(sysUser, sysUserBean);
		String orgIds = "";
		String orgNames = "";
		List<SysOrganization> orgs = sysUser.getSysOrganizations();
		if (!BaseUtil.isEmpty(orgs) && orgs.size() > 0) {
			for (SysOrganization org : orgs) {
				if (BaseUtil.isEmpty(orgIds)) {
					orgIds = org.getId();
					orgNames = org.getName();
				} else {
					orgIds += "," + org.getId();
					orgNames += "," + org.getName();
				}
			}
		}
		String roleIds = "";
		String roleNames = "";
		List<SysRole> roles = sysUser.getSysRoles();
		if (!BaseUtil.isEmpty(roles) && roles.size() > 0) {
			for (SysRole role : roles) {
				if (BaseUtil.isEmpty(roleIds)) {
					roleIds = role.getId();
					roleNames = role.getName();
				} else {
					roleIds += "," + role.getId();
					roleNames += "," + role.getName();
				}
			}
		}
		sysUserBean.setSysOrganizationIDs(orgIds);
		sysUserBean.setOrgNames(orgNames);
		sysUserBean.setSysRoleIDs(roleIds);
		sysUserBean.setRoleName(roleNames);
		sysUserBean.setPassword(DESUtil.getInstance().decrypt(
				sysUserBean.getPassword()));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sysUserBean", sysUserBean);
		params.put("genderTag", SystemResourceUtil.getInstance()
				.getResourceAsList("gender"));
		params.put("staffKindTag", SystemResourceUtil.getInstance()
				.getResourceAsList("staffKind"));
		return forword("system/sys_user_modify", params);
	}

	/**
	 * 通过人员ID删除人员信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("deleteSysUser")
	@ResponseBody
	public Map<String, Object> deleteSysUser(HttpServletRequest request,
			SysUserBean sysUserBean, PageBean pageBean) throws Exception {
		sysUserService.deleteSysUserById(sysUserBean.getId());
		return findAllSysUsers(request, sysUserBean, pageBean);
	}

	/**
	 * 通过多个人员ID，系统删除多人员信息
	 * 
	 * @throws Exception
	 */
	@RequestMapping("deleteSysUsers")
	@ResponseBody
	public Map<String, Object> deleteSysUsers(HttpServletRequest request,
			SysUserBean sysUserBean, PageBean pageBean) throws Exception {
		sysUserService.deleteSysUsers(sysUserBean.getDeleteIDs().split(","));
		return findAllSysUsers(request, sysUserBean, pageBean);
	}

	/**
	 * 查询人员
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("findAllSysUsers")
	@ResponseBody
	public Map<String, Object> findAllSysUsers(HttpServletRequest request,
			SysUserBean sysUserBean, PageBean pageBean)
			throws FrameworkException, Exception {
		SysMngUserLoginBean sysUserLoginBean = SessionUtils.getUser(request);

		// jqGrid查询转码-utf-8
		BaseUtil.decodeObject(sysUserBean);
		Map<String, Object> searchCondition = HqlUtil.beanToMap(sysUserBean,
				SysUser.class);
		// 手工构造操作员、状态查询条件
		searchCondition = buildDisabledAndOperatorCondition(searchCondition,
				sysUserBean);
		// 判断是否关联角色表查询
		if (!BaseUtil.isEmpty(sysUserBean)
				&& !BaseUtil.isEmpty(sysUserBean.getRoleName())) {
			searchCondition.put("sysRole", sysUserBean.getRoleName());
		}
		String[] sysOrgIds = null;
		// 判断是否关联系统组织机构查询
		// if (sysUserLoginBean.getFlag().equals("1")) {
		searchCondition = BaseUtil.isEmpty(searchCondition) ? new TreeMap<String, Object>()
				: searchCondition;
		sysOrgIds = sysUserService
				.findSysOrganizationIdsByLoginUserId(sysUserLoginBean.getId());
		searchCondition.put("sysOrganization", sysOrgIds);
		SysOrganization createOrg = sysAreaDictionaryService
				.findCommpanByLoginUserId(sysUserLoginBean.getId());
		searchCondition.put("createOrg", createOrg.getId());
		// }
		pageBean.init(sysUserService.getSysUserTotalRows(searchCondition));
		// 判断是否关联角色表查询
		if (!BaseUtil.isEmpty(sysUserBean)
				&& !BaseUtil.isEmpty(sysUserBean.getRoleName())) {
			searchCondition.put("sysRole", sysUserBean.getRoleName());
		}
		// 判断是否关联系统组织机构查询
		// if (sysUserLoginBean.getFlag().equals("1")) {
		searchCondition.put("sysOrganization", sysOrgIds);
		// }
		List<SysUser> sysUsers = sysUserService.findAllSysUsers(
				searchCondition, pageBean.getCurPage(), pageBean.getPageSize());
		List<SysUserBean> sysUserBeans = new ArrayList<SysUserBean>();
		for (SysUser sysUser : sysUsers) {
			SysUserBean bean = new SysUserBean();
			BeanUtils.copyProperties(sysUser, bean);
			List<SysOrganization> orgs = sysUser.getSysOrganizations();
			String tempOrgName = "";
			for (SysOrganization org : orgs) {
				if (BaseUtil.isEmpty(tempOrgName))
					tempOrgName = org.getName();
				else
					tempOrgName += ";" + org.getName();
			}
			bean.setOrgNames(tempOrgName);
			bean.setPassword(DESUtil.getInstance().decrypt(bean.getPassword()));
			bean.setDisabledFlag(BaseUtil.booleanToChineseString(
					bean.getDisabled(), "启用", "禁用"));
			bean.setOperatorFlag(BaseUtil.booleanToChineseString(bean
					.getIsOperator()));
			bean.setGender(SystemResourceUtil.getInstance()
					.getCnNameOfBusinessDictionary("gender", bean.getGender()));

			sysUserBeans.add(bean);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pageBean", pageBean);
		params.put("sysUserBeans", sysUserBeans);
		return params;
	}

	/**
	 * 通过组织机构ID查询未与该组织机构关联的人员
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("findNotSelectedSysUsers")
	@ResponseBody
	public Map<String, Object> findNotSelectedSysUsers(
			HttpServletRequest request, SysUserBean sysUserBean,
			PageBean pageBean) throws FrameworkException, Exception {
		SysMngUserLoginBean sysUserLoginBean = SessionUtils.getUser(request);

		// jqGrid查询转码-utf-8
		BaseUtil.decodeObject(sysUserBean);
		Map<String, Object> searchCondition = HqlUtil.beanToMap(sysUserBean,
				SysUser.class);
		// 人员与组织机构多对多关系
		// searchCondition.put("sysOrganizationId",
		// sysUserBean.getSysOrganizationIDs());
		// String[] sysOrgIds = null;
		// if (sysUserLoginBean.getFlag().equals("1")) {
		// sysOrgIds =
		// sysUserService.findSysOrganizationIdsByLoginUserId(sysUserLoginBean.getId());
		// searchCondition.put("sysOrgIds", sysOrgIds);
		// searchCondition.put("createUser", sysUserLoginBean.getId());
		// }
		// pageBean.init(sysUserService.getNotSelectedSysUserTotalRows(searchCondition));
		// //判断是否关联系统组织机构查询
		// if (sysUserLoginBean.getFlag().equals("1")) {
		// searchCondition.put("sysOrgIds", sysOrgIds);
		// }
		// 人员与组织机构为多对一关系
		searchCondition = BaseUtil.isEmpty(searchCondition) ? new TreeMap<String, Object>()
				: searchCondition;
		searchCondition.put("disabled", true);
		SysOrganization createOrg = sysAreaDictionaryService
				.findCommpanByLoginUserId(sysUserLoginBean.getId());
		searchCondition.put("createOrg", createOrg.getId());
		pageBean.init(sysUserService
				.getNotSelectedSysUserTotalRows(searchCondition));
		searchCondition.put("createOrg", createOrg.getId());
		List<SysUser> sysUsers = sysUserService.findNotSelectedSysUsers(
				searchCondition, pageBean.getCurPage(), pageBean.getPageSize());
		List<SysUserBean> sysUserBeans = new ArrayList<SysUserBean>();
		for (SysUser sysUser : sysUsers) {
			SysUserBean bean = new SysUserBean();
			BeanUtils.copyProperties(sysUser, bean);
			bean.setGender(SystemResourceUtil.getInstance()
					.getCnNameOfBusinessDictionary("gender", bean.getGender()));
			sysUserBeans.add(bean);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pageBean", pageBean);
		params.put("sysUserBeans", sysUserBeans);
		return params;
	}

	/**
	 * 根据人员ID设置组织机构
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("setSysOrganization")
	@ResponseBody
	public String setSysOrganization(SysUserBean sysUserBean)
			throws FrameworkException {
		sysUserService.setSysOrganization(sysUserBean.getId(), sysUserBean
				.getSysOrganizationIDs().split(","));
		return SUCCESS;
	}

	/**
	 * 根据人员ID设置权限
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("setSysRole")
	@ResponseBody
	public Map<String, Object> setSysRole(SysUserBean sysUserBean)
			throws FrameworkException {
		sysUserService.setSysRole(sysUserBean.getId(), BaseUtil
				.isEmpty(sysUserBean.getSysRoleIDs()) ? null : sysUserBean
				.getSysRoleIDs().split(","));

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("result", true);
		return params;
	}

	/**
	 * 构造状态、操作员查询条件
	 * 
	 * @param params
	 * @param sysUserBean
	 * @return
	 * @throws FrameworkException
	 */
	private Map<String, Object> buildDisabledAndOperatorCondition(
			Map<String, Object> params, SysUserBean sysUserBean)
			throws FrameworkException {
		params = BaseUtil.isEmpty(params) ? new TreeMap<String, Object>()
				: params;
		params.put("disabled", true);
		if (!BaseUtil.isEmpty(sysUserBean)) {
			if (!BaseUtil.isEmpty(sysUserBean.getOperatorFlag())) {
				if (sysUserBean.getOperatorFlag().equals("true"))
					params.put("isOperator", true);
				else if (sysUserBean.getOperatorFlag().equals("false"))
					params.put("isOperator", false);
			}
		}
		return params;

	}

	/**
	 * 获取登录用户密码和用户名
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("findUserLoginInfo")
	@ResponseBody
	public String findUserLoginInfo(HttpServletRequest request) {
		SysMngUserLoginBean sysUserLoginBean = SessionUtils.getUser(request);
		logger.debug("----userbean=" + sysUserLoginBean.getPassword());
		logger.debug("----userbean=" + sysUserLoginBean.getUserName());

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("oldPassword", sysUserLoginBean.getPassword());
		params.put("sysUserLoginBean", sysUserLoginBean);
		return SUCCESS;
	}

	/**
	 * 一般用户修改密码
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("userPasswordChange")
	@ResponseBody
	public Map<String, Object> userPasswordChange(HttpServletRequest request,
			SysUserBean sysUserBean, String newpassword) throws Exception {
		SysMngUserLoginBean sysUserLoginBean = SessionUtils.getUser(request);
		SysUser sysUser = sysUserService.findSysUserById(sysUserLoginBean
				.getId());
		String password = DESUtil.getInstance().decrypt(sysUser.getPassword());

		Map<String, Object> params = new HashMap<String, Object>();

		if (sysUserBean.getPassword().equalsIgnoreCase(password)) {
			sysUser.setPassword(DESUtil.getInstance().encrypt(newpassword));
			sysUserService.modifySysUser(sysUser);
			sysUserLoginBean.setPassword(newpassword);
			SessionUtils.setUser(request, sysUserLoginBean);
			params.put("result", true);
		} else {
			params.put("result", false);
		}
		return params;
	}

	/**
	 * 恢复初始密码
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initPassword")
	@ResponseBody
	public String initPassword(HttpServletRequest request,
			SysUserBean sysUserBean) throws Exception {
		sysUserService.initPassword(sysUserBean.getDeleteIDs().split(","));
		return SUCCESS;
	}

}
