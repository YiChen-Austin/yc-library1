package com.mall.web.admin.system.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.common.exception.FrameworkException;
import com.mall.web.admin.common.domain.SysDictionary;
import com.mall.web.admin.common.utils.BaseAction;
import com.mall.web.admin.common.utils.SystemResourceUtil;
import com.mall.web.admin.security.vo.DictionaryBean;
import com.mall.web.admin.security.vo.SysDictionaryBean;
import com.mall.web.admin.system.service.SysDictionaryService;

/**
 * 功能说明：
 * 
 * @作者： 雷运梅 、谢朝胜 创建日期： 2010-5-6
 */

@Controller
@RequestMapping("/admin/")
public class PeelingAction extends BaseAction {
	@Autowired
	private SysDictionaryService sysDictionaryService;

	/**
	 * 通过CSS样式的ID获取样式颜色信息接口
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findSysPeelings")
	@ResponseBody
	public Map<String, Object> findSysPeelings() throws Exception {
		List<DictionaryBean> list = SystemResourceUtil.getInstance()
				.getResourceAsList("peeling");
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("list", list);
		return context;
	}

	/**
	 * 修改CSS皮肤样式
	 * 
	 * @return
	 * @throws FrameworkException
	 * @throws Exception
	 */
	@RequestMapping("peelingChange")
	@ResponseBody
	public Map<String, Object> peelingChange(SysDictionaryBean sysDictionaryBean)
			throws FrameworkException {
		SysDictionary sysDictionary = sysDictionaryService
				.findSysDictionaryByName("PEELING_CHANGE", null);
		sysDictionary.setValue(sysDictionaryBean.getValue());
		sysDictionaryService.updateSysDictionary(sysDictionary);

		Map<String, Object> context = new HashMap<String, Object>();
		context.put("result", true);
		context.put("sysDictionary", sysDictionary);
		return context;
	}
}