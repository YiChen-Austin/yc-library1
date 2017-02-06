/**
 * 
 */
package com.mall.web.admin.news.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.mall.web.admin.common.utils.TreeUtil;
import com.mall.web.admin.news.domain.SysCommonCategory;
import com.mall.web.admin.news.service.CommonCategoryService;
import com.mall.web.admin.news.vo.CommonCategoryBean;
import com.mall.web.admin.security.vo.SysMngUserLoginBean;
import com.mall.web.admin.security.vo.TreeNodeBean;
import com.mall.web.admin.system.service.SysAreaDictionaryService;

/**
 * @功能说明：栏目管理控制层
 * @作者： 印鲜刚
 * @创建日期： 2010-9-18 @
 */
@Controller
@RequestMapping("/news/")
public class CommonCategoryAction extends BaseAction {
	private static final long serialVersionUID = -3604395120360423215L;
	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	private CommonCategoryService commonCategoryService;
	@Autowired
	private SysAreaDictionaryService sysAreaDictionaryService;

	/**
	 * 显示讨论栏目树形结构
	 * 
	 * @return
	 * @throws FrameworkException
	 * @throws Exception
	 */
	@RequestMapping("findAllDiscussionCategories")
	@ResponseBody
	public Map<String, Object> findAllDiscussionCategories()
			throws FrameworkException, Exception {
		List<SysCommonCategory> commonCategories = commonCategoryService
				.findAllDiscussionCategories();
		TreeNodeBean treeNodeBean = TreeUtil
				.convertMenuTreeNode(commonCategories);
		List<TreeNodeBean> children = treeNodeBean.getChildren();
		// 父节点
		String data = treeNodeBean.getData();
		Map<String, String> attributes = treeNodeBean.getAttr();

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("children", children);
		result.put("data", data);
		result.put("attributes", attributes);
		return result;
	}

	/**
	 * 显示信息栏目树形结构
	 * 
	 * @return
	 * @throws FrameworkException
	 * @throws Exception
	 */
	@RequestMapping("findAllNewsCategories")
	@ResponseBody
	public Map<String, Object> findAllNewsCategories()
			throws FrameworkException, Exception {
		List<SysCommonCategory> commonCategories = commonCategoryService
				.findAllNewsCategories();
		TreeNodeBean treeNodeBean = TreeUtil
				.convertMenuTreeNode(commonCategories);
		List<TreeNodeBean> children = treeNodeBean.getChildren();
		// 父节点
		String data = treeNodeBean.getData();
		Map<String, String> attributes = treeNodeBean.getAttr();

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("children", children);
		result.put("data", data);
		result.put("attributes", attributes);
		return result;
	}

	/**
	 * 修改讨论栏目功能
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("modifyDiscussionCategory")
	@ResponseBody
	public Map<String, Object> modifyDiscussionCategory(
			HttpServletRequest request, HttpServletResponse response,
			CommonCategoryBean commonCategoryBean) throws FrameworkException {
		SysMngUserLoginBean sysUserLoginBean = SessionUtils.getUser(request);

		if (!BaseUtil.isEmpty(commonCategoryService
				.findDiscussionCategoryByIdAndName(commonCategoryBean.getId(),
						commonCategoryBean.getName(), sysAreaDictionaryService
								.findCommpanByLoginUserId(sysUserLoginBean
										.getId())))) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("newId", "none");
			return result;
		}
		SysCommonCategory commonCategory = commonCategoryService
				.findCommonCategoryById(commonCategoryBean.getId());
		Integer orderNo = commonCategory.getOrderNo();
		BeanUtils.copyProperties(commonCategoryBean, commonCategory);
		commonCategory.setOrderNo(orderNo);
		commonCategoryService.modifyDiscussionCategory(commonCategory);
		// LogUtil.getInstance().modifyExecution(commonCategory, "论坛栏目");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "true");
		return result;
	}

	/**
	 * 修改信息栏目功能
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("modifyNewsCategory")
	@ResponseBody
	public Map<String, Object> modifyNewsCategory(HttpServletRequest request,
			HttpServletResponse response, CommonCategoryBean commonCategoryBean)
			throws FrameworkException {
		SysMngUserLoginBean sysUserLoginBean = SessionUtils.getUser(request);

		if (!BaseUtil.isEmpty(commonCategoryService
				.findNewsCategoryByIdAndName(commonCategoryBean.getId(),
						commonCategoryBean.getName(), sysAreaDictionaryService
								.findCommpanByLoginUserId(sysUserLoginBean
										.getId())))) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("newId", "none");
			return result;
		}
		SysCommonCategory commonCategory = commonCategoryService
				.findCommonCategoryById(commonCategoryBean.getId());
		Integer orderNo = commonCategory.getOrderNo();
		BeanUtils.copyProperties(commonCategoryBean, commonCategory);
		commonCategory.setOrderNo(orderNo);
		commonCategoryService.modifyNewsCategory(commonCategory,
				commonCategoryBean.getAdministratorId(),
				commonCategoryBean.getPublisherId(),
				commonCategoryBean.getAdministratorName(),
				commonCategoryBean.getPublisherName());
		// LogUtil.getInstance().modifyExecution(commonCategory, "信息栏目");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "true");
		return result;
	}

	/**
	 * 同级讨论新增功能
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("sameSaveDiscussionCategory")
	@ResponseBody
	public Map<String, Object> sameSaveDiscussionCategory(
			HttpServletRequest request, HttpServletResponse response,
			CommonCategoryBean commonCategoryBean, String selectId)
			throws FrameworkException {
		SysMngUserLoginBean sysUserLoginBean = SessionUtils.getUser(request);

		if (!BaseUtil.isEmpty(commonCategoryService
				.findDiscussionCategoryByName(commonCategoryBean.getName(),
						sysAreaDictionaryService
								.findCommpanByLoginUserId(sysUserLoginBean
										.getId())))) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("newId", "none");
			return result;
		}
		SysCommonCategory commonCategory = new SysCommonCategory();
		BeanUtils.copyProperties(commonCategoryBean, commonCategory);
		commonCategory.setCompany(sysAreaDictionaryService
				.findCommpanByLoginUserId(sysUserLoginBean.getId()));
		logger.debug("selectId=" + selectId);
		commonCategoryService.sameSaveDiscussionCategory(commonCategory,
				selectId);
		// LogUtil.getInstance().addExecution(commonCategory, "论坛栏目");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "true");
		result.put("newId", commonCategory.getId());
		return result;
	}

	/**
	 * 同级信息新增功能
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("sameSaveNewsCategory")
	@ResponseBody
	public Map<String, Object> sameSaveNewsCategory(HttpServletRequest request,
			HttpServletResponse response,
			CommonCategoryBean commonCategoryBean, String selectId)
			throws FrameworkException {
		SysMngUserLoginBean sysUserLoginBean = SessionUtils.getUser(request);
		if (!BaseUtil.isEmpty(commonCategoryService.findNewsCategoryByName(
				commonCategoryBean.getName(), sysAreaDictionaryService
						.findCommpanByLoginUserId(sysUserLoginBean.getId())))) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("newId", "none");
			return result;
		}
		SysCommonCategory commonCategory = new SysCommonCategory();
		BeanUtils.copyProperties(commonCategoryBean, commonCategory);
		commonCategory.setCompany(sysAreaDictionaryService
				.findCommpanByLoginUserId(sysUserLoginBean.getId()));
		logger.debug("selectId=" + selectId);
		commonCategoryService.sameSaveNewsCategory(commonCategory,
				commonCategoryBean.getAdministratorId(),
				commonCategoryBean.getPublisherId(), selectId,
				commonCategoryBean.getAdministratorName(),
				commonCategoryBean.getPublisherName());
		// LogUtil.getInstance().addExecution(commonCategory, "信息栏目");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "true");
		result.put("newId", commonCategory.getId());
		return result;
	}

	/**
	 * 次级新增栏目
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("secondarySaveDiscussionCategory")
	@ResponseBody
	public Map<String, Object> secondarySaveDiscussionCategory(
			HttpServletRequest request, HttpServletResponse response,
			CommonCategoryBean commonCategoryBean, String selectId)
			throws FrameworkException {
		SysMngUserLoginBean sysUserLoginBean = SessionUtils.getUser(request);
		if (!BaseUtil.isEmpty(commonCategoryService
				.findDiscussionCategoryByName(commonCategoryBean.getName(),
						sysAreaDictionaryService
								.findCommpanByLoginUserId(sysUserLoginBean
										.getId())))) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("newId", "none");
			return result;
		}
		SysCommonCategory commonCategory = new SysCommonCategory();
		BeanUtils.copyProperties(commonCategoryBean, commonCategory);
		commonCategory.setCompany(sysAreaDictionaryService
				.findCommpanByLoginUserId(sysUserLoginBean.getId()));
		logger.debug("selectId=" + selectId);
		commonCategoryService.secondarySaveDiscussionCategory(commonCategory,
				selectId);
		// LogUtil.getInstance().addExecution(commonCategory, "论坛栏目");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "true");
		result.put("newId", commonCategory.getId());
		return result;
	}

	/**
	 * 次级新增信息
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("secondarySaveNewsCategory")
	@ResponseBody
	public Map<String, Object> secondarySaveNewsCategory(
			HttpServletRequest request, HttpServletResponse response,
			CommonCategoryBean commonCategoryBean, String selectId)
			throws FrameworkException {
		SysMngUserLoginBean sysUserLoginBean = SessionUtils.getUser(request);
		if (!BaseUtil.isEmpty(commonCategoryService.findNewsCategoryByName(
				commonCategoryBean.getName(), sysAreaDictionaryService
						.findCommpanByLoginUserId(sysUserLoginBean.getId())))) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("newId", "none");
			return result;
		}
		SysCommonCategory commonCategory = new SysCommonCategory();
		BeanUtils.copyProperties(commonCategoryBean, commonCategory);
		commonCategory.setCompany(sysAreaDictionaryService
				.findCommpanByLoginUserId(sysUserLoginBean.getId()));
		logger.debug("selectId=" + selectId);
		commonCategoryService.secondarySaveNewsCategory(commonCategory,
				commonCategoryBean.getAdministratorId(),
				commonCategoryBean.getPublisherId(), selectId,
				commonCategoryBean.getAdministratorName(),
				commonCategoryBean.getPublisherName());
		// LogUtil.getInstance().addExecution(commonCategory, "信息栏目");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "true");
		result.put("newId", commonCategory.getId());
		return result;
	}

	/**
	 * 删除栏目
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("deleteDiscussionCategory")
	@ResponseBody
	public Map<String, Object> deleteDiscussionCategory(
			HttpServletRequest request, HttpServletResponse response,
			CommonCategoryBean commonCategoryBean) throws FrameworkException {
		commonCategoryService
				.deleteAllDiscussionCategoriesById(commonCategoryBean.getId());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "true");
		return result;
	}
}
