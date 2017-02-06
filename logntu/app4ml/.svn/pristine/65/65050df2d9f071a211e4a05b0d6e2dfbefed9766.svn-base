package com.mall.web.pay.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.web.mall.security.vo.CustomUserDetails;
import com.mall.web.pay.domain.PayInteraction;
import com.mall.web.pay.service.PayDataService;
import com.mall.web.pay.service.PayInterfaceService;
import com.mall.web.pay.util.PayUtil;

/**
 * @category 快钱在线支付
 * 
 */
@Controller
public class Bill99Action {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private PayDataService mallPayDataService;
	@Autowired
	private PayInterfaceService mallPayInterfaceService;
	private String serviceCode;
	private String errCode;
	/************************/
	// 订单信息
	private String orderNo;// 订单字符串
	private String amount;// 订单扣费总金额
	private String orderType;// 订单类型

	ModelAndView ERROR = new ModelAndView("/pay/payResult.jsp");
	ModelAndView SUCCESS = new ModelAndView("/pay/payResult.jsp");

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public String getAmount() {
		return amount;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5975788170434670706L;

	/**
	 * 快捷支付异步步通知接口
	 * 
	 */
	@RequestMapping("billNotice")
	public String billNotice(HttpServletRequest request)
			throws FrameworkException {
		// 获取支付宝GET过来反馈信息
		Map<String, String> params = new LinkedHashMap<String, String>();
		Map requestParams = request.getParameterMap();
		// ////////////////////
		System.out.println("快捷异步通知：");
		// ////////////////////
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		// 处理响应
		doResonse(params, 3);
		return null;
	}

	/**
     * 
     * 
     * 
     */
	@RequestMapping("billReceive")
	public ModelAndView billReceive(HttpServletRequest request)
			throws FrameworkException {
		// 获取支付宝GET过来反馈信息
		Map<String, String> params = new LinkedHashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		return doResonse(params, 2);
	}

	@RequestMapping("billSend")
	public String billSend(HttpServletResponse response)
			throws FrameworkException {
		long userId = 0;
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Object object = auth.getPrincipal();
		if (object != null && object instanceof CustomUserDetails) {
			CustomUserDetails user = (CustomUserDetails) object;
			userId = user.getUserId();
		}
		try {
			String sHtmlText = mallPayInterfaceService.transfer99Bill(
					this.orderNo.split(","), this.orderType,
					Float.parseFloat(amount), userId);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(sHtmlText);
			return null;
		} catch (Exception e) {
			logger.warn(e);
		}
		return null;
	}

	/****************************************/
	private ModelAndView doResonse(Map<String, String> params, int status)
			throws FrameworkException {
		// 1、处理响应数据
		boolean flag = PayUtil.encodeByCer(params, params.get("signMsg"));
		if (flag == false) {
			System.out.println("数据校验失败!");
			return ERROR;
		}

		errCode = (String) params.get("errCode");
		orderNo = (String) params.get("orderId");
		try {
			amount = Double
					.toString(Double.parseDouble(params.get("payAmount")) / 100);
		} catch (Exception e) {
			logger.warn(e);
		}
		// 处理成功
		if ("10".equalsIgnoreCase((String) params.get("payResult"))) {
			try {
				String tOrder = mallPayDataService.payResponse(orderNo, "10");
				if (!BaseUtil.isEmpty(tOrder)) {
					orderNo = tOrder;
				}
				serviceCode = "0";
			} catch (Exception e) {
				e.printStackTrace();
				serviceCode = "2";// 无相关订单
				return ERROR;
			}
		} else {
			serviceCode = "1";
		}

		// 2、记录响应数据包
		PayInteraction pia = new PayInteraction();
		pia.setCreateTime(DateUtil.getGMTDate());
		try {
			pia.setMoney(BigDecimal.valueOf(params.get("payAmount") == null ? 0
					: Float.parseFloat(params.get("payAmount"))));
		} catch (Exception e) {
			logger.warn(e);
		}
		pia.setStatus(status);
		pia.setOrderId(orderNo);
		pia.setReactionData(params.toString());
		mallPayDataService.saveYzmPayInteraction(pia);
		return SUCCESS;
	}

	/*******************************/

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
}
