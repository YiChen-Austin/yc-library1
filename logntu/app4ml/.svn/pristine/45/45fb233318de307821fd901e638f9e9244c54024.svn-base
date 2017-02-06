package com.mall.web.admin.payrecord.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.common.vo.PageBean4UI;
import com.mall.web.admin.payrecord.service.AdminPayRecordService;
import com.mall.web.admin.payrecord.vo.AdminPayRecordVo;

/**
 * @Description(描述) : 支付控制层
 * @author(作者) : ventrue
 * @date (开发日期) : 2015年9月1日 上午11:22:30
 */

@Controller()
@RequestMapping("/admin/payrecord/*")
public class AdminPayRecordController {
	private static Logger logger = Logger
			.getLogger(AdminPayRecordController.class);

	// 支付报告（日志）
	@Resource(name = "adminPayRecordService")
	private AdminPayRecordService adminPayRecordService;

	/**
	 * @Description(描述) : 支付报告信息
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年8月24日 下午4:07:17
	 */
	@RequestMapping("list")
	@ResponseBody
	public Object recordList(PageBean4UI pageBean,
			AdminPayRecordVo payRecordVo, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			pageBean.init(adminPayRecordService.getPayRecords(
					payRecordVo.getRequestTimeS(),
					payRecordVo.getRequestTimeE(), payRecordVo.getIn_user_id()));
			List<AdminPayRecordVo> list = adminPayRecordService.findPayRecords(
					payRecordVo.getRequestTimeS(),
					payRecordVo.getRequestTimeE(), payRecordVo.getIn_user_id(),
					pageBean);
			result.put("rows", list);
			result.put("total", pageBean.getTotalRecords());
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
		}
		return result;
	}
}
