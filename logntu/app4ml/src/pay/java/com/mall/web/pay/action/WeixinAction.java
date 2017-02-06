package com.mall.web.pay.action;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.web.mall.common.annotation.MemberAuth;
import com.mall.web.mall.domain.Order;
import com.mall.web.mall.order.service.OrderService;
import com.mall.web.pay.domain.PayInteraction;
import com.mall.web.pay.domain.PayPrimary;
import com.mall.web.pay.service.PayDataService;
import com.tencent.common.Configure;
import com.tencent.common.Util;
import com.tencent.protocol.qr.notice_protocol.NoticeReqData;
import com.tencent.protocol.qr.notice_protocol.NoticeResData;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

/**
 * @category 微信在线支付
 * 
 */
@Scope("prototype")
@Controller("weixinAction")
public class WeixinAction {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private PayDataService mallPayDataService;
	@Autowired
	protected OrderService orderService;
	/************************/
	// 订单信息
//	private String orderNo;// 订单字符串
//	private String amount;// 订单扣费总金额
//	private String orderType;// 订单类型
//	private String status;// 回调订单状态

	ModelAndView ERROR = new ModelAndView("/pay/payResult.jsp");
	ModelAndView SUCCESS = new ModelAndView("/pay/payResult.jsp");
	
	/**
	 * 微信支付异步步通知接口
	 * 
	 */

	@RequestMapping("/wxnotic4app")
	public String wxnotic4app(HttpServletRequest request,
			HttpServletResponse response) {
		return wxnotic(request, response, Configure.getAppID4mp());
	}

	@RequestMapping("/wxnotic")
	public String wxnoticWeb(HttpServletRequest request,
			HttpServletResponse response, String key) {
		return wxnotic(request, response, Configure.getAppID4mp());
	}

	public String wxnotic(HttpServletRequest request,
			HttpServletResponse response, String key) {
		try {
			// 通知数据处理
			String gzip = request.getHeader("Content-Encoding");
			InputStream input = request.getInputStream();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] data = new byte[512];
			int len = 0;
			if ("gzip".equalsIgnoreCase(gzip)) {
				GZIPInputStream gunzip = new GZIPInputStream(input);
				while ((len = gunzip.read(data)) >= 0) {
					out.write(data, 0, len);
				}
			} else {
				while ((len = input.read(data)) >= 0) {
					out.write(data, 0, len);
				}
			}
			String responseString = new String(out.toByteArray());
			System.out.println(responseString);
			if (responseString.length() > 0) {
				NoticeReqData noticeReqData = (NoticeReqData) Util
						.getObjectFromXML(responseString, NoticeReqData.class);
				// System.out.println(noticeReqData.validSign() + ","
				// + noticeReqData.getAppid() + ","
				// + noticeReqData.getOut_trade_no() + ","
				// + noticeReqData.getTransaction_id());
				doResonse(noticeReqData, responseString, 3, key);
			}

			// 通知响应数据
			NoticeResData res = new NoticeResData();
			res.setReturn_code("SUCCESS");
			res.setReturn_msg("OK");
			// 解决XStream对出现双下划线的bug
			XStream xStreamForRequestPostData = new XStream(new DomDriver(
					"UTF-8", new XmlFriendlyNameCoder("-_", "_")));
			// 将要提交给API的数据对象转换成XML格式数据Post给API
			String postDataXML = xStreamForRequestPostData.toXML(res);
			response.setContentType("text/xml");
			response.setCharacterEncoding("UTF-8");
			Writer writer = response.getWriter();
			writer.write(postDataXML);
			writer.flush();
		} catch (Exception e) {
			logger.warn(e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @throws FrameworkException
	 **************************************/
	private String doResonse(NoticeReqData noticeReqData,
			String responseString, int status, String key)
			throws FrameworkException {
		//TODO 1、处理响应数据(后续还要打开)
//		if (!noticeReqData.validSign(key)) {
//			System.out.println("数据校验失败!");
//			return null;
//		}

		String orderNo = noticeReqData.getOut_trade_no();
		try {
			String amount = Double
					.toString((double) noticeReqData.getTotal_fee() / 100);
		} catch (Exception e) {
			logger.warn(e);
		}
		// 处理成功
		if ("SUCCESS".equalsIgnoreCase(noticeReqData.getResult_code())) {
			try {
				String tOrder = mallPayDataService.payResponse4Game(orderNo,
						"SUCCESS", noticeReqData.getTransaction_id());
				if (!BaseUtil.isEmpty(tOrder)) {
					orderNo = tOrder;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		// 2、记录响应数据包
		PayInteraction pia = new PayInteraction();
		pia.setCreateTime(DateUtil.getGMTDate());
		try {
			pia.setMoney(BigDecimal.valueOf((double) noticeReqData
					.getTotal_fee() / 100));
		} catch (Exception e) {
			logger.warn(e);
		}
		pia.setStatus(status);
		pia.setOrderId(orderNo);
		pia.setReactionData(responseString);
		mallPayDataService.saveYzmPayInteraction(pia);
		return null;
	}

	/**
	 * 微信支付异步步通知接口
	 * 
	 */
	@RequestMapping("/wxPayStaus")
	@ResponseBody
	@MemberAuth(verifyLogin = false)
	public Object wxPayStaus(HttpServletResponse response,String orderNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		PayPrimary pay = mallPayDataService.getPayPrimaryBySn(orderNo);
		String status =null;
		if (pay != null) {
			List<Order> list = orderService.findOrderByPayId(pay
					.getPrimaryId());
			if (list.size() > 0) {
				orderNo = Long.toString(list.get(0).getOrderId());
			}
			status = (pay == null ? "0" : Integer.toString(pay.getStatus()));
			result.put("orderNo", orderNo);
			result.put("status", status);
		}
		return result;
	}
}
