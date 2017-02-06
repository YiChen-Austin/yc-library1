package com.mall.web.pay.action;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.web.mall.third.alipay.util.AlipayNotify;
import com.mall.web.pay.domain.PayInteraction;
import com.mall.web.pay.service.PayDataService;

/**
 * @category 支付宝支付
 * 
 */
@Controller("alipayAction")
public class AlipayAction {
	/**
	 * 
	 */
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private PayDataService mallPayDataService;
	private String serviceCode;
	private String errCode;
	/************************/
	// 订单信息
	private String orderNo;// 订单字符串
	private String amount;// 订单扣费总金额
	ModelAndView ERROR = new ModelAndView("person/pay/payError");
	ModelAndView SUCCESS = new ModelAndView("person/pay/payResult");


	/**
	 * @throws UnsupportedEncodingException
	 * @category 支付宝异步步通知接口
	 * 
	 */
	@RequestMapping("/aliNotice")
	public String cpNotice(HttpServletRequest request) throws FrameworkException, UnsupportedEncodingException {
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 商户订单号

		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
		params.put("out_trade_no", out_trade_no);
		// 支付宝交易号

		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
		params.put("trade_no", trade_no);
		// 交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
		params.put("trade_status", trade_status);
		// 处理响应
		doResonse(params, 3);
		return null;
	}

	@RequestMapping("/aliReceive")
	public ModelAndView cpReceive(HttpServletRequest request) throws FrameworkException, UnsupportedEncodingException {
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 商户订单号

		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
		params.put("out_trade_no", out_trade_no);
		// 支付宝交易号

		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
		params.put("trade_no", trade_no);
		// 交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
		params.put("trade_status", trade_status);
		
		ModelAndView mav=doResonse(params, 2);
		Map<String, String> data = new HashMap<String, String>();
		data.put("serviceCode", serviceCode);
		data.put("errCode", errCode);
		data.put("orderNo", orderNo);
		data.put("amount", amount);
		mav.addAllObjects(data);
		return mav;
	}
	/****************************************/
	private ModelAndView doResonse(Map<String, String> params, int status) throws FrameworkException {
		// 1、处理响应数据
		boolean flag = AlipayNotify.verify(params);
		if (flag == false) {
			System.out.println("数据校验失败!");
			return ERROR;
		}

		orderNo = (String) params.get("out_trade_no");
		try {
			amount = Double.toString(Double.parseDouble(params.get("total_fee")));
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
		}
		String trade_status = (String) params.get("trade_status");
		if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
			// 判断该笔订单是否在商户网站中已经做过处理
			// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
			// 如果有做过处理，不执行商户的业务程序
			try {
				String tOrder = mallPayDataService.payResponse(orderNo, trade_status);
				if (!BaseUtil.isEmpty(tOrder)) {
					orderNo = tOrder;
				}
				serviceCode = "0";
			} catch (Exception e) {
				e.printStackTrace();
				logger.warn(e);
				serviceCode = "2";// 无相关订单
				return ERROR;
			}
			// 注意：
			// 该种交易状态只在两种情况下出现
			// 1、开通了普通即时到账，买家付款成功后。
			// 2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
		} else
			serviceCode = "1";
		// 2、记录响应数据包
		PayInteraction pia = new PayInteraction();
		pia.setCreateTime(DateUtil.getGMTDate());
		try {
			pia.setMoney(BigDecimal.valueOf(params.get("total_fee") == null ? 0 : Float.parseFloat(params.get("total_fee"))));
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
		}
		pia.setStatus(status);
		pia.setOrderId(orderNo);
		pia.setReactionData(params.toString());
		mallPayDataService.saveYzmPayInteraction(pia);
		return SUCCESS;
	}
}
