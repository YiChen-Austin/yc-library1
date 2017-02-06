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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.common.utils.BaseAction;
import com.mall.web.admin.common.utils.SessionUtils;
import com.mall.web.admin.common.utils.SystemResourceUtil;
import com.mall.web.admin.common.utils.TreeUtil;
import com.mall.web.admin.security.domain.SysOrganization;
import com.mall.web.admin.security.domain.SysUser;
import com.mall.web.admin.security.service.SysOrganizationService;
import com.mall.web.admin.security.service.SysUserService;
import com.mall.web.admin.security.vo.SysMngUserLoginBean;
import com.mall.web.admin.security.vo.SysOrganizationBean;
import com.mall.web.admin.security.vo.SysUserBean;
import com.mall.web.admin.security.vo.TreeNodeBean;

/**
 * @功能说明：组织机构管理控制层
 * @作者： 印鲜刚
 * @创建日期： 2010-5-18 @
 */
@Controller
@RequestMapping("/admin/")
public class SysOrganizationAction extends BaseAction {
	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	private SysOrganizationService sysOrganizationService;
	@Autowired
	private SysUserService sysUserService;

	/**
	 * 显示树形组织机构
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findSelectSysOrganizations")
	@ResponseBody
	public Map<String, Object> findSelectSysOrganizations()
			throws FrameworkException, Exception {
		TreeNodeBean treeNodeBean = null;

		List<SysOrganization> sysOrganizations = sysOrganizationService
				.findAllSysOrganizations(null);
		treeNodeBean = TreeUtil.convertMenuTreeNode(sysOrganizations);
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
	 * 显示树形组织机构
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findAllSysOrganizations")
	@ResponseBody
	public Map<String, Object> findAllSysOrganizations(
			HttpServletRequest request) throws FrameworkException, Exception {
		SysMngUserLoginBean sysUserLoginBean = SessionUtils.getUser(request);

		List<SysOrganization> sysOrganizations = null;
		TreeNodeBean treeNodeBean = null;
		// if (sysUserLoginBean.getFlag().equals("1")) {
		sysOrganizations = sysUserService
				.findSysOrganizationsByLoginUserId(sysUserLoginBean.getId());
		SysUser sysUser = sysUserService.findSysUserById(sysUserLoginBean
				.getId());
		List<SysOrganization> sysOrgs = sysUser.getSysOrganizations();
		if (BaseUtil.isEmpty(sysOrgs) || sysOrgs.size() == 0)
			return null;
		treeNodeBean = TreeUtil.convertMenuTreeNode(sysOrgs.get(0),
				sysOrganizations);
		// } else {
		// sysOrganizations =
		// sysOrganizationService.findAllSysOrganizations(null);
		// treeNodeBean = TreeUtil.convertMenuTreeNode(sysOrganizations);
		// }
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
	 * 修改组织机构功能
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("modifySysOrganization")
	@ResponseBody
	public Map<String, Object> modifySysOrganization(
			SysOrganizationBean sysOrganizationBean) throws FrameworkException {
		String newId = null;
		if (!BaseUtil.isEmpty(sysOrganizationService
				.findSysOrganizationByIdAndName(sysOrganizationBean.getId(),
						sysOrganizationBean.getName()))) {
			newId = "none";
			Map<String, Object> context = new HashMap<String, Object>();
			context.put("newId", newId);
			return context;
		}
		SysOrganization sysOrganization = sysOrganizationService
				.findSysOrganizationById(sysOrganizationBean.getId());
		Integer orderNo = sysOrganization.getOrderNo();
		BeanUtils.copyProperties(sysOrganizationBean, sysOrganization);
		sysOrganization.setOrderNo(orderNo);
		sysOrganizationService.modifySysOrganization(sysOrganization);
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("result", true);
		context.put("newId", newId);
		return context;
	}

	/**
	 * 组织机构数删除功能
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("deleteSysOrganization")
	@ResponseBody
	public Map<String, Object> deleteSysOrganization(
			SysOrganizationBean sysOrganizationBean) throws FrameworkException {
		sysOrganizationService
				.deleteAllSysOrganizationsById(sysOrganizationBean.getId());

		Map<String, Object> context = new HashMap<String, Object>();
		context.put("result", true);
		return context;
	}

	/**
	 * 同级组织机构新增功能
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("sameSaveSysOrganization")
	@ResponseBody
	public Map<String, Object> sameSaveSysOrganization(
			SysOrganizationBean sysOrganizationBean, String selectId)
			throws FrameworkException {
		String newId = null;
		if (!BaseUtil.isEmpty(sysOrganizationService
				.findSysOrganizationByName(sysOrganizationBean.getName()))) {
			newId = "none";
			Map<String, Object> context = new HashMap<String, Object>();
			context.put("newId", newId);
			return context;
		}
		SysOrganization sysOrganization = new SysOrganization();
		BeanUtils.copyProperties(sysOrganizationBean, sysOrganization);
		logger.debug("selectId=" + selectId);
		sysOrganizationService.sameSaveSysOrganization(sysOrganization,
				selectId);
		newId = sysOrganization.getId();

		Map<String, Object> context = new HashMap<String, Object>();
		context.put("result", true);
		context.put("newId", newId);
		return context;
	}

	/**
	 * 次级组织机构新增功能
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("secondarySaveSysOrganization")
	@ResponseBody
	public Map<String, Object> secondarySaveSysOrganization(
			SysOrganizationBean sysOrganizationBean, String selectId)
			throws FrameworkException {
		String newId = null;
		if (!BaseUtil.isEmpty(sysOrganizationService
				.findSysOrganizationByName(sysOrganizationBean.getName()))) {
			newId = "none";
			Map<String, Object> context = new HashMap<String, Object>();
			context.put("newId", newId);
			return context;
		}
		SysOrganization sysOrganization = new SysOrganization();
		BeanUtils.copyProperties(sysOrganizationBean, sysOrganization);
		logger.debug("selectId=" + selectId);
		sysOrganizationService.secondarySaveSysOrganization(sysOrganization,
				selectId);
		newId = sysOrganization.getId();
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("result", true);
		context.put("newId", newId);
		return context;
	}

	/**
	 * 通过组织机构ID获取获取组织机构及其关联人员信息
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("findSysUsersBySysOrganizationId")
	@ResponseBody
	public Map<String, Object> findSysUsersBySysOrganizationId(
			SysOrganizationBean sysOrganizationBean) throws FrameworkException {
		SysOrganization sysOrganization = sysOrganizationService
				.findSysOrganizationById(sysOrganizationBean.getId());
		BeanUtils.copyProperties(sysOrganization, sysOrganizationBean);
		List<SysUser> sysUsers = sysOrganization.getSysUsers();
		List<SysUserBean> sysUserBeans = new ArrayList<SysUserBean>();
		for (SysUser sysUser : sysUsers) {
			if (!sysUser.getDisabled())
				continue;
			SysUserBean sysUserBean = new SysUserBean();
			BeanUtils.copyProperties(sysUser, sysUserBean);
			sysUserBean.setGender(SystemResourceUtil.getInstance()
					.getCnNameOfBusinessDictionary("gender",
							sysUserBean.getGender()));
			sysUserBeans.add(sysUserBean);
		}
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("sysUserBeans", sysUserBeans);
		return context;
	}

	/**
	 * 根据组织机构ID修改原有人员
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("setSysUserBySysOrganizationId")
	@ResponseBody
	public Map<String, Object> setSysUserBySysOrganizationId(
			SysOrganizationBean sysOrganizationBean) throws FrameworkException {
		sysOrganizationService.setSysUser(sysOrganizationBean.getId(), BaseUtil
				.isEmpty(sysOrganizationBean.getSysUserIDs()) ? null
				: sysOrganizationBean.getSysUserIDs().split(","));
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("result", true);
		return context;
	}

	/**
	 * 根据组织机构ID新增人员
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("setNewSysUserBySysOrganizationId")
	@ResponseBody
	public Map<String, Object> setNewSysUserBySysOrganizationId(
			SysOrganizationBean sysOrganizationBean) throws FrameworkException {
		sysOrganizationService.setNewSysUser(sysOrganizationBean.getId(),
				BaseUtil.isEmpty(sysOrganizationBean.getSysUserIDs()) ? null
						: sysOrganizationBean.getSysUserIDs().split(","));
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("result", true);
		return context;
	}

	/**
	 * 根据组织机构移动功能
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("moveSysOrganization")
	@ResponseBody
	public Map<String, Object> moveSysOrganization(String oldNodeId,
			String newNodeId, String changeType) throws FrameworkException {

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
		result = sysOrganizationService.moveNode(oldNodeId, newNodeId,
				changeType);
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("result", result);
		return context;
	}

}
