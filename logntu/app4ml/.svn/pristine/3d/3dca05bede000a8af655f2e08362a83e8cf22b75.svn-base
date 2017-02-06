package com.mall.web.mall.order.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mall.common.util.BaseUtil;
import com.mall.web.mall.common.annotation.MemberAuth;
import com.mall.web.mall.common.utils.CkSessionUtils;
import com.mall.web.mall.common.utils.MallEnum.ServiceCodeType;
import com.mall.web.mall.member.service.WebMemberDepositService;
import com.mall.web.mall.member.service.WebMemberService;
import com.mall.web.mall.member.vo.WebMemberVo;
import com.mall.web.mall.order.service.OrderService;

/**
 * @Description(描述) : 商品订单
 * @author(作者) : wangliyou
 * @date (开发日期) : 2015年9月1日 上午11:22:30
 */

@Controller()
@RequestMapping("/order/*")
public class OrderController {
	private static Logger logger = Logger.getLogger(OrderController.class);

	// 商品订单
	@Resource(name = "orderService")
	private OrderService orderService;
	// 账户信息
	@Resource(name = "webMemberDepositService")
	private WebMemberDepositService webMemberDepositService;
	// 用户信息
	@Resource(name = "webMemberService")
	private WebMemberService webMemberService;

	/**
	 *消费订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("listRecharge")
	@ResponseBody
	@MemberAuth(verifyLogin = true)
	public Object listRecharge(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		WebMemberVo user = CkSessionUtils.getUser(request);
		if (BaseUtil.isNotEmpty(user)) {
			result.put(ServiceCodeType.ServiceCode,ServiceCodeType.Success.getIndex());
			result.put("orderList", orderService.findRechargeOrder(user.getUserId()));
		}else{
			result.put(ServiceCodeType.ServiceCode,ServiceCodeType.OtherErr.getIndex());
		}
		return result;
	}
	/**
	 * 充值订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("listConsume")
	@ResponseBody
	@MemberAuth(verifyLogin = true)	
	public Object listConsume(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		WebMemberVo user = CkSessionUtils.getUser(request);
		if (BaseUtil.isNotEmpty(user)) {
			result.put(ServiceCodeType.ServiceCode,ServiceCodeType.Success.getIndex());
			result.put("orderList", orderService.findConsumeOrder(user.getUserId()));
		}else{
			result.put(ServiceCodeType.ServiceCode,ServiceCodeType.OtherErr.getIndex());
		}
		return result;
	}
}
