package com.mall.web.admin.primary.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.web.admin.primary.service.AdminPrimaryService;
import com.mall.web.admin.primary.vo.PayPrimaryVo;

/**
 * @Description(描述) : 支付控制层
 * @author(作者) : ventrue
 * @date (开发日期) : 2015年9月1日 上午11:22:30
 */

@Controller()
@RequestMapping("/admin/primary/*")
public class AdminPrimaryController {
	private static Logger logger = Logger
			.getLogger(AdminPrimaryController.class);

	// 支付
	@Resource(name = "adminPrimaryService")
	private AdminPrimaryService adminPrimaryService;

	/**
	 * @Description(描述) : 支付方式统计
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年8月24日 下午4:07:17
	 */
	@RequestMapping("primaryStat")
	@ResponseBody
	public Object primaryStat(PayPrimaryVo primaryVo, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			PayPrimaryVo pimaryVo = adminPrimaryService.findPayPrimaryStat(
					primaryVo.getRequestTimeS(), primaryVo.getRequestTimeE(),
					primaryVo.getStatus());
			ArrayList<PayPrimaryVo> list = new ArrayList<PayPrimaryVo>();
			list.add(pimaryVo);

			result.put("rows", list);
			result.put("total", list.size());
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
		}
		return result;
	}
}
