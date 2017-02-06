package com.mall.web.pay.action;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.web.pay.domain.PayInteraction;
import com.mall.web.pay.service.PayDataService;
import com.unionpay.acp.sdk.AcpService;
import com.unionpay.acp.sdk.SDKConstants;

/**
 * @category 银联在线支付
 * 
 */
@Controller("chinaPayAction")
public class ChinaPayAction {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private PayDataService mallPayDataService;

	ModelAndView ERROR = new ModelAndView("person/pay/payError");
	ModelAndView SUCCESS = new ModelAndView("person/pay/payResult");

	/**
	 * @category 银联在线异步步通知接口
	 * 
	 */
	@RequestMapping("/cpNotice")
	public String cpNotice(HttpServletRequest request) {
		String encoding = request.getParameter(SDKConstants.param_encoding);
		Map<String, String> respParam = getAllRequestParam(request);
		Map<String, String> valideData = null;
		try {
			if (null != respParam && !respParam.isEmpty()) {
				Iterator<Entry<String, String>> it = respParam.entrySet().iterator();
				valideData = new HashMap<String, String>(respParam.size());
				while (it.hasNext()) {
					Entry<String, String> e = it.next();
					String key = (String) e.getKey();
					String value = (String) e.getValue();
					value = new String(value.getBytes(encoding), encoding);
					valideData.put(key, value);
				}
			}
			// 处理响应
			doResonse(valideData, encoding, 3);
		} catch (Exception e) {
			logger.warn(e);
		}
		return null;
	}

	@RequestMapping("/cpReceive")
	public ModelAndView cpReceive(HttpServletRequest request, HttpServletResponse response) {
		String encoding = request.getParameter(SDKConstants.param_encoding);
		Map<String, String> respParam = getAllRequestParam(request);
		Map<String, String> valideData = null;
		try {
			if (null != respParam && !respParam.isEmpty()) {
				Iterator<Entry<String, String>> it = respParam.entrySet().iterator();
				valideData = new HashMap<String, String>(respParam.size());
				while (it.hasNext()) {
					Entry<String, String> e = it.next();
					String key = (String) e.getKey();
					String value = (String) e.getValue();
					value = new String(value.getBytes(encoding), encoding);
					valideData.put(key, value);
				}
			}
			// 处理响应
			return doResonse(valideData, encoding, 2);
		} catch (Exception e) {
			logger.warn(e);
		}
		return ERROR;
	}

	/****************************************/
	private ModelAndView doResonse(Map<String, String> params, String encoding, int status) throws Exception {
		// 1、处理响应数据
		boolean flag = AcpService.validate(params, encoding);
		if (flag == false) {
			System.out.println("数据校验失败!");
			return ERROR;
		}
		String orderNo = (String) params.get("orderId");
		if ("00".equalsIgnoreCase(params.get("respCode"))) {
			try {
				String tOrder = mallPayDataService.payResponse(orderNo, "1001");
				if (!BaseUtil.isEmpty(tOrder)) {
					orderNo = tOrder;
				}
			} catch (Exception e) {
				return ERROR;
			}
		}
		// 2、记录响应数据包
		PayInteraction pia = new PayInteraction();
		pia.setCreateTime(DateUtil.getGMTDate());
		try {
			pia.setMoney(BigDecimal
					.valueOf(params.get("txnAmt") == null ? 0 : Float.parseFloat(params.get("txnAmt")) / 100));
		} catch (Exception e) {
			logger.warn(e);
		}
		pia.setStatus(status);
		pia.setOrderId(orderNo);
		pia.setReactionData(params.toString());
		mallPayDataService.saveYzmPayInteraction(pia);
		return SUCCESS;
	}

	/**
	 * 获取请求参数中所有的信息
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getAllRequestParam(final HttpServletRequest request) {
		Map<String, String> res = new HashMap<String, String>();
		Enumeration<?> temp = request.getParameterNames();
		if (null != temp) {
			while (temp.hasMoreElements()) {
				String en = (String) temp.nextElement();
				String value = request.getParameter(en);
				res.put(en, value);
				// 在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
				if (res.get(en) == null || "".equals(res.get(en))) {
					// System.out.println("======为空的字段名===="+en);
					res.remove(en);
				}
			}
		}
		return res;
	}
}
