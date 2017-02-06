package com.mall.web.admin.system.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.common.exception.FrameworkException;
import com.mall.web.admin.common.utils.BaseAction;
import com.mall.web.admin.security.service.SysResourcesRegService;
import com.mall.web.admin.security.vo.SysResourcesRegBean;

@Controller
@RequestMapping("/admin/")
public class SysResourcesRegAction extends BaseAction {
	private static Logger logger = Logger
			.getLogger(SysResourcesRegAction.class);
	@Autowired
	private SysResourcesRegService sysResourcesRegService;

	/**
	 * 修改指定组件
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("updataTargetcomponentAction")
	@ResponseBody
	public Map<String, Object> updataTargetcomponentAction(String targeID,
			SysResourcesRegBean sysResourcesRegBean) throws FrameworkException {
		logger.debug("targeID:" + targeID);
		logger.debug("JSP_url" + sysResourcesRegBean.getName());
		logger.debug("Remark:" + sysResourcesRegBean.getRemark());
		logger.debug("Description:" + sysResourcesRegBean.getDescription());
		String flag = sysResourcesRegService.updataTargetcomponent(targeID,
				sysResourcesRegBean);
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("flag", flag);
		return context;
	}

	/**
	 * 修改指定menu的JSP
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("updataTargetJSPAction")
	@ResponseBody
	public Map<String, Object> updataTargetJSPAction(String targeID,
			SysResourcesRegBean sysResourcesRegBean) throws FrameworkException {
		logger.debug("targeID:" + targeID);
		logger.debug("JSP_url" + sysResourcesRegBean.getName());
		logger.debug("Remark:" + sysResourcesRegBean.getRemark());
		logger.debug("Description:" + sysResourcesRegBean.getDescription());
		String flag = sysResourcesRegService.updataTargetJSP(targeID,
				sysResourcesRegBean);

		Map<String, Object> context = new HashMap<String, Object>();
		context.put("flag", flag);
		return context;
	}

	/**
	 * 增加指定menu的JSP
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("addTargetJSPAction")
	@ResponseBody
	public Map<String, Object> addTargetJSPAction(String targeID,
			SysResourcesRegBean sysResourcesRegBean) throws FrameworkException {
		logger.debug("targeID:" + targeID);
		logger.debug("JSP_url" + sysResourcesRegBean.getName());
		logger.debug("Remark:" + sysResourcesRegBean.getRemark());
		logger.debug("Description:" + sysResourcesRegBean.getDescription());
		List<SysResourcesRegBean> list = new ArrayList<SysResourcesRegBean>();
		list.add(sysResourcesRegBean);
		String flag = sysResourcesRegService.addTargetJSP(targeID, list);
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("flag", flag);
		return context;
	}

	/**
	 * 增加组件的Action
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("addTargetcomponentAction")
	@ResponseBody
	public Map<String, Object> addTargetcomponentAction(String targeID,
			SysResourcesRegBean sysResourcesRegBean) throws FrameworkException {
		logger.debug("addTargetcomponentAction_targeID:" + targeID);

		List<SysResourcesRegBean> list = new ArrayList<SysResourcesRegBean>();
		list.add(sysResourcesRegBean);
		String flag = sysResourcesRegService.addTargetcomponent(targeID, list);

		Map<String, Object> context = new HashMap<String, Object>();
		context.put("flag", flag);
		return context;
	}

	/**
	 * 增加指定组件的Action
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("addTargetActionURLAction")
	@ResponseBody
	public Map<String, Object> addTargetActionURLAction(String targeID,
			SysResourcesRegBean sysResourcesRegBean) throws FrameworkException {
		logger.debug("addTargetActionURLAction_targeID:" + targeID);

		List<SysResourcesRegBean> list = new ArrayList<SysResourcesRegBean>();
		list.add(sysResourcesRegBean);
		String flag = sysResourcesRegService.addTargetActionURL(targeID, list);

		Map<String, Object> context = new HashMap<String, Object>();
		context.put("flag", flag);
		return context;
	}

	/**
	 * 获取指定菜单的所有jsp列表
	 */
	@RequestMapping("findTargetJSPAction")
	@ResponseBody
	public Map<String, Object> findTargetJSPAction(String targeID,
			SysResourcesRegBean sysResourcesRegBean) throws FrameworkException {
		logger.debug("findTargetJSPAction_targeID:" + targeID);
		List<SysResourcesRegBean> sysResourcesRegBeans = sysResourcesRegService
				.getTargetJSP(targeID);

		Map<String, Object> context = new HashMap<String, Object>();
		context.put("sysResourcesRegBeans", sysResourcesRegBeans);
		return context;
	}

	//

	/**
	 * 获取指JSP列表对应的组件
	 */
	@RequestMapping("findComponentsAction")
	@ResponseBody
	public Map<String, Object> findComponentsAction(String targeID)
			throws FrameworkException {
		logger.debug("findComponentsAction_targeID:" + targeID);

		List<SysResourcesRegBean> sysResourcesRegBeans = sysResourcesRegService
				.getTargetComponent(targeID);

		Map<String, Object> context = new HashMap<String, Object>();
		context.put("sysResourcesRegBeans", sysResourcesRegBeans);
		return context;
	}

	/**
	 * 获取指组建对应的Action
	 */
	@RequestMapping("findAcrionUrlsAction")
	@ResponseBody
	public Map<String, Object> findAcrionUrlsAction(String targeID)
			throws FrameworkException {
		logger.debug("findAcrionUrlsAction_targeID:" + targeID);

		List<SysResourcesRegBean> sysResourcesRegBeans = sysResourcesRegService
				.findAcrionUrls(targeID);

		Map<String, Object> context = new HashMap<String, Object>();
		context.put("sysResourcesRegBeans", sysResourcesRegBeans);
		return context;
	}

	/**
	 * 删除指定的Action地址
	 */
	@RequestMapping("delActionUrlAction")
	@ResponseBody
	public Map<String, Object> delActionUrlAction(String targeID,
			SysResourcesRegBean sysResourcesRegBean) throws FrameworkException {
		logger.debug("delActionUrlAction_targeID:" + targeID);
		String flag = sysResourcesRegService.delActionUrl(targeID);
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("flag", flag);
		return context;
	}

	/**
	 * 删除指定的组件
	 */
	@RequestMapping("delComponentAction")
	@ResponseBody
	public Map<String, Object> delComponentAction(String targeID,
			SysResourcesRegBean sysResourcesRegBean) throws FrameworkException {
		logger.debug("delActionUrlAction_targeID:" + targeID);
		String flag = sysResourcesRegService.delComponent(targeID);

		Map<String, Object> context = new HashMap<String, Object>();
		context.put("flag", flag);
		return context;
	}

	/**
	 * 删除指定的JSP页面
	 */
	@RequestMapping("delJspUrlAction")
	@ResponseBody
	public Map<String, Object> delJspUrlAction(String targeID,
			SysResourcesRegBean sysResourcesRegBean) throws FrameworkException {
		logger.debug("delActionUrlAction_targeID:" + targeID);
		String flag = sysResourcesRegService.delJspUrl(targeID);

		Map<String, Object> context = new HashMap<String, Object>();
		context.put("flag", flag);
		return context;
	}

}
