package com.mall.web.admin.system.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.mall.web.admin.common.domain.SysBusinessDictionary;
import com.mall.web.admin.common.domain.SysBusinessDictionaryDetail;
import com.mall.web.admin.common.utils.BaseAction;
import com.mall.web.admin.security.vo.BusinessDictionaryBean;
import com.mall.web.admin.security.vo.BusinessDictionaryDetailBean;
import com.mall.web.admin.system.service.SysBusinessDictionaryService;

/**
 * @功能说明：业务字典和业务字典明细控制层
 * @作者：雷运梅、潘瑞峥
 * @创建日期：2010-6-5
 */
@Controller
@RequestMapping("/admin/")
public class BusinessDictionaryAction extends BaseAction {

	@Autowired
	private SysBusinessDictionaryService sysBusinessDictionaryService;

	/**
	 * 查询业务字典参数信息
	 * 
	 * @return
	 * @throws FrameworkException
	 *             , Exception
	 */
	@RequestMapping("findAllBusinessDictionaries")
	@ResponseBody
	public Map<String, Object> findAllBusinessDictionaries(
			HttpServletRequest request,
			BusinessDictionaryBean businessDictionaryBean, PageBean pageBean)
			throws FrameworkException, Exception {
		// jqGrid查询转码-utf-8
		BaseUtil.decodeObject(businessDictionaryBean);
		Map<String, Object> searchCondition = HqlUtil.beanToMap(
				businessDictionaryBean, SysBusinessDictionary.class);
		pageBean.init(sysBusinessDictionaryService
				.getBusinessDictionaryTotalRows(searchCondition));
		List<SysBusinessDictionary> businessDictionaries = sysBusinessDictionaryService
				.findAllBusinessDictionaries(searchCondition,
						pageBean.getCurPage(), pageBean.getPageSize());
		List<BusinessDictionaryBean> businessDictionaryBeans = new ArrayList<BusinessDictionaryBean>();
		for (SysBusinessDictionary businessDictionary : businessDictionaries) {
			BusinessDictionaryBean bean = new BusinessDictionaryBean();
			BeanUtils.copyProperties(businessDictionary, bean);
			bean.setFlag(bean.getFlag().charValue() == CommonConstant.FLAG_VARIABLE ? '是'
					: '否');
			businessDictionaryBeans.add(bean);
		}

		Map<String, Object> context = new HashMap<String, Object>();
		context.put("pageBean", pageBean);
		context.put("businessDictionaryBeans", businessDictionaryBeans);
		return context;
	}

	/**
	 * 通过业务字典参数ID删除业务字典参数信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("deleteBusinessDictionary")
	@ResponseBody
	public Map<String, Object> deleteBusinessDictionary(
			HttpServletRequest request,
			BusinessDictionaryBean businessDictionaryBean, PageBean pageBean)
			throws Exception {
		sysBusinessDictionaryService
				.deleteBusinessDictionaryById(businessDictionaryBean
						.getDeleteIDs());
		return findAllBusinessDictionaries(request, businessDictionaryBean,
				pageBean);
	}

	/**
	 * 修改业务字典是时通过修改业务ID获取修改业务信息
	 * 
	 * @return
	 * @throws FrameworkException
	 *             , Exception
	 */
	@RequestMapping("findBusinessDictionary")
	public ModelAndView findBusinessDictionary(
			BusinessDictionaryBean businessDictionaryBean)
			throws FrameworkException, Exception {
		SysBusinessDictionary businessDictionary = sysBusinessDictionaryService
				.findBusinessDictionaryById(businessDictionaryBean.getId());
		BeanUtils.copyProperties(businessDictionary, businessDictionaryBean);

		Map<String, Object> context = new HashMap<String, Object>();
		context.put("businessDictionaryBean", businessDictionaryBean);
		return forword("system/business_dictionary_modify", context);
	}

	/**
	 * 修改业务字典时通过字典id获取字典信息及明细
	 * 
	 * @return
	 * @throws FrameworkException
	 *             , Exception
	 */
	@RequestMapping("findBusinessDictionaryDetails")
	@ResponseBody
	public Map<String, Object> findBusinessDictionaryDetails(
			BusinessDictionaryBean businessDictionaryBean)
			throws FrameworkException, Exception {
		SysBusinessDictionary businessDictionary = sysBusinessDictionaryService
				.findBusinessDictionaryById(businessDictionaryBean.getId());
		BeanUtils.copyProperties(businessDictionary, businessDictionaryBean);
		List<SysBusinessDictionaryDetail> businessDictionaryDetails = businessDictionary
				.getBusinessDictionaryDetails();
		List<BusinessDictionaryDetailBean> businessDictionaryDetailBeans = new ArrayList<BusinessDictionaryDetailBean>();
		for (SysBusinessDictionaryDetail businessDictionaryDetail : businessDictionaryDetails) {
			BusinessDictionaryDetailBean bean = new BusinessDictionaryDetailBean();
			BeanUtils.copyProperties(businessDictionaryDetail, bean);
			businessDictionaryDetailBeans.add(bean);
		}
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("businessDictionaryDetailBeans",
				businessDictionaryDetailBeans);
		return context;
	}

	/**
	 * 进入业务字典参数添加页面
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("createBusinessDictionary")
	public ModelAndView createBusinessDictionary() throws FrameworkException {
		return forword("system/business_dictionary_detail", null);
	}

	/**
	 * 修改业务字典及字典明细
	 * 
	 * @return
	 * @throws FrameworkException
	 *             , Exception
	 */
	@RequestMapping("modifyBusinessDictionary")
	@ResponseBody
	public Map<String, Object> modifyBusinessDictionary(
			BusinessDictionaryBean businessDictionaryBean, String values)
			throws FrameworkException, Exception {
		// 需要传给service的参数，参数可为空
		String insertVal = null;
		String updateVal = null;
		String deleteVal = null;
		// 解码
		BaseUtil.decodeObject(businessDictionaryBean);
		// 给业务字典赋新值
		SysBusinessDictionary businessDictionary = new SysBusinessDictionary();
		businessDictionary.setId(businessDictionaryBean.getId());
		businessDictionary.setCnName(businessDictionaryBean.getCnName());
		businessDictionary.setEnName(businessDictionaryBean.getEnName());
		businessDictionary.setFlag(businessDictionaryBean.getFlag());
		businessDictionary.setRemark(businessDictionaryBean.getRemark());
		// 重名情况，js返回false
		if (!BaseUtil.isEmpty(sysBusinessDictionaryService
				.findBusinessDictionaryByIdAndName(
						businessDictionaryBean.getId(),
						businessDictionaryBean.getEnName(),
						businessDictionaryBean.getCnName()))) {
			Map<String, Object> context = new HashMap<String, Object>();
			context.put("result", false);
			return context;
		}
		// 拆分js传来的明细values参数，并自动赋给insertVal，updateVal，deleteVal
		if (null != values) {
			String[] _values = values.split("\\|\\|");
			int size = _values.length;
			if (1 == size) {
				// 新增
				if (null != _values[0] && !"".equals(_values[0].trim())) {
					insertVal = _values[0].trim();
				}
			} else if (2 == size) {
				// 新增和修改
				if (null != _values[0] && !"".equals(_values[0].trim())) {
					insertVal = _values[0].trim();
				}
				if (null != _values[1] && !"".equals(_values[1].trim())) {
					updateVal = _values[1].trim();
				}
			} else if (3 == size) {
				// 新增、修改、删除
				if (null != _values[0] && !"".equals(_values[0].trim())) {
					insertVal = _values[0].trim();
				}
				if (null != _values[1] && !"".equals(_values[1].trim())) {
					updateVal = _values[1].trim();
				}
				if (null != _values[2] && !"".equals(_values[2].trim())) {
					deleteVal = _values[2].trim();
				}
			}
		}
		sysBusinessDictionaryService.updateBusinessDictionaryDetail(
				businessDictionary, insertVal, updateVal, deleteVal);
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("result", true);
		return context;
	}

	/**
	 * 新增业务字典及字典明细
	 * 
	 * @return
	 * @throws FrameworkException
	 *             , Exception
	 */
	@RequestMapping("saveBusinessDictionary")
	@ResponseBody
	public Map<String, Object> saveBusinessDictionary(
			BusinessDictionaryBean businessDictionaryBean, String values)
			throws FrameworkException, Exception {
		// 解码
		BaseUtil.decodeObject(businessDictionaryBean);
		// 判断重名，重名给js返回false
		if (!BaseUtil.isEmpty(sysBusinessDictionaryService
				.findBusinessDictionaryByName(
						businessDictionaryBean.getEnName(),
						businessDictionaryBean.getCnName()))) {
			Map<String, Object> context = new HashMap<String, Object>();
			context.put("result", false);
			return context;
		}
		// 为businessDictionary赋值
		SysBusinessDictionary businessDictionary = new SysBusinessDictionary();
		businessDictionary.setCnName(businessDictionaryBean.getCnName());
		businessDictionary.setEnName(businessDictionaryBean.getEnName());
		businessDictionary.setFlag(businessDictionaryBean.getFlag());
		businessDictionary.setRemark(businessDictionaryBean.getRemark());
		// 字典明细list
		List<SysBusinessDictionaryDetail> list = new ArrayList<SysBusinessDictionaryDetail>();
		for (String val : values.split(",")) {
			SysBusinessDictionaryDetail businessDictionaryDetail = new SysBusinessDictionaryDetail();
			businessDictionaryDetail.setName(val.split(";")[0]);
			businessDictionaryDetail.setValue(val.split(";")[1]);
			if (val.split(";").length < 3) {
				businessDictionaryDetail.setRemark("");
			} else {
				businessDictionaryDetail.setRemark(val.split(";")[2]);
			}
			list.add(businessDictionaryDetail);
		}
		sysBusinessDictionaryService.saveBusinessDictionary(businessDictionary,
				list);
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("result", true);
		return context;
	}

}