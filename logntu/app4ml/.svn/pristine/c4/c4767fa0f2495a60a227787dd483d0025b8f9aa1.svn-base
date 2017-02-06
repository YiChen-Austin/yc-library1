/**
 * 
 */
package com.mall.web.admin.system.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.constant.CommonConstant;
import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.HqlUtil;
import com.mall.common.vo.PageBean;
import com.mall.web.admin.common.domain.SysDictionary;
import com.mall.web.admin.common.utils.BaseAction;
import com.mall.web.admin.security.vo.SysDictionaryBean;
import com.mall.web.admin.system.service.SysDictionaryService;

/**
 * @功能说明： 系统字典控制层
 * @作者： 印鲜刚
 * @创建日期： 2010-5-25 @
 */
@Controller
@RequestMapping("/admin/")
public class SysDictionaryAction extends BaseAction {
	private static Logger logger = Logger.getLogger(SysDictionaryAction.class);
	@Autowired
	private SysDictionaryService sysDictionaryService;

	@RequestMapping("findAllSysDictionaries")
	@ResponseBody
	public Map<String, Object> findAllSysDictionaries(
			SysDictionaryBean sysDictionaryBean, PageBean pageBean)
			throws Exception {
		// jqGrid查询转码-utf-8
		BaseUtil.decodeObject(sysDictionaryBean);
		Map<String, Object> searchCondition = HqlUtil.beanToMap(
				sysDictionaryBean, SysDictionary.class);
		pageBean.init(sysDictionaryService
				.getSysDictionaryTotalRows(searchCondition));
		List<SysDictionary> sysDictionaries = sysDictionaryService
				.findAllSysDictionaries(searchCondition, pageBean.getCurPage(),
						pageBean.getPageSize());
		List<SysDictionaryBean> sysDictionaryBeans = new ArrayList<SysDictionaryBean>();
		for (SysDictionary sysDictionary : sysDictionaries) {
			SysDictionaryBean bean = new SysDictionaryBean();
			BeanUtils.copyProperties(sysDictionary, bean);
			bean.setFlag(bean.getFlag().charValue() == CommonConstant.FLAG_VARIABLE ? '是'
					: '否');
			sysDictionaryBeans.add(bean);
		}
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("sysDictionaryBeans", sysDictionaryBeans);
		context.put("pageBean", pageBean);
		return context;
	}

	/**
	 * 通过系统参数ID删除系统参数信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("deleteSysDictionary")
	@ResponseBody
	public Map<String, Object> deleteSysDictionary(
			SysDictionaryBean sysDictionaryBean, PageBean pageBean)
			throws Exception {
		sysDictionaryService.deleteSysDictionaryById(sysDictionaryBean
				.getDeleteIDs());
		return findAllSysDictionaries(sysDictionaryBean, pageBean);
	}

	/**
	 * 通过系统参数ID获取系统参数信息
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("findSysDictionary")
	public ModelAndView findSysDictionary(SysDictionaryBean sysDictionaryBean)
			throws FrameworkException, Exception {
		SysDictionary sysDictionary = sysDictionaryService
				.findSysDictionaryById(sysDictionaryBean.getId());
		BeanUtils.copyProperties(sysDictionary, sysDictionaryBean);
		return forword("system/sys_dictionary_modify", null);
	}

	/**
	 * 进入系统参数添加页面
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("createSysDictionary")
	public ModelAndView createSysDictionary() throws FrameworkException {
		return forword("system/sys_dictionary_detail", null);
	}

	/**
	 * 添加系统参数
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("saveSysDictionary")
	public ModelAndView saveSysDictionary(SysDictionaryBean sysDictionaryBean)
			throws FrameworkException, Exception {
		if (!BaseUtil.isEmpty(sysDictionaryService.findSysDictionaryByName(
				sysDictionaryBean.getName(), sysDictionaryBean.getCnName()))) {
			Map<String, Object> context = new HashMap<String, Object>();
			context.put("nameAndCnName", "已有该英文名称或中文名称!");
			return forword("system/sys_dictionary_detail", null);
		}
		SysDictionary sysDictionary = new SysDictionary();
		BeanUtils.copyProperties(sysDictionaryBean, sysDictionary);
		sysDictionaryService.saveSysDictionary(sysDictionary);
		return forword("system/sys_dictionary_list", null);
	}

	/**
	 * 修改系统参数
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("modifySysDictionary")
	public ModelAndView modifySysDictionary(SysDictionaryBean sysDictionaryBean)
			throws FrameworkException, Exception {
		if (!BaseUtil.isEmpty(sysDictionaryService
				.findSysDictionaryByIdAndName(sysDictionaryBean.getId(),
						sysDictionaryBean.getName(),
						sysDictionaryBean.getCnName()))) {
			Map<String, Object> context = new HashMap<String, Object>();
			context.put("nameAndCnName", "已有该英文名称或中文名称!");
			return forword("system/sys_dictionary_modify", null);
		}
		SysDictionary sysDictionary = sysDictionaryService
				.findSysDictionaryById(sysDictionaryBean.getId());
		BeanUtils.copyProperties(sysDictionaryBean, sysDictionary);
		sysDictionaryService.updateSysDictionary(sysDictionary);
		return forword("system/sys_dictionary_list", null);
	}
}
