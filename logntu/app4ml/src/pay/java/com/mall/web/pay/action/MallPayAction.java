package com.mall.web.pay.action;

import java.util.List;

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
import com.mall.common.util.MD5Util;
import com.mall.web.mall.domain.Order;
import com.mall.web.mall.order.service.OrderService;
import com.mall.web.mall.security.vo.CustomUserDetails;
import com.mall.web.mall.third.alipay.AlipayConfig;
import com.mall.web.pay.conf.PayConfig;
import com.mall.web.pay.service.PayDataService;
import com.mall.web.pay.service.PayInterfaceService;
import com.mall.web.pay.vo.BankPayVo;
import com.mall.web.pay.vo.BankThirdPayVo;
import com.mall.web.pay.vo.PayOrderVo;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import com.tencent.common.Configure;
import com.tencent.protocol.qr.unifiedorder_protocol.UnifiedorderResData;

/**
 * @category 商城在线支付
 * 
 */
@Controller("mallPaySendAction")
public class MallPayAction {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private PayDataService mallPayDataService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private PayInterfaceService mallPayInterfaceService;

	private List<BankThirdPayVo> bankThirdPayVos;// 第三方接口信息
	private List<BankPayVo> bankPayVos;// 银行信息
	private String serviceCode;

	/************************/
	// 订单信息
	private String orderNo;// 订单字符串
	private int defaultId;// (多个订单中)默认（首）订单
	private String amount;// 订单扣费总金额
	private String key;// md5防篡改串
	private String orderType;// 订单类型
	private String code;

	private PayOrderVo payOrderVo = null;

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public PayOrderVo getPayOrderVo() {
		return payOrderVo;
	}

	public void setPayOrderVo(PayOrderVo payOrderVo) {
		this.payOrderVo = payOrderVo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public String getAmount() {
		return amount;
	}

	public String getKey() {
		return key;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	/**
	 * 支付三部曲： 1、支付选择 2、支付确认 3、支付结果
	 * 
	 * 进入1、支付选择
	 * 
	 * @throws Exception
	 * 
	 */
	@RequestMapping("/pay")
	public ModelAndView pay() {
		ModelAndView ERROR = new ModelAndView("/pay/payIError.jsp");
		ModelAndView SUCCESS = new ModelAndView("/pay/payIndex.jsp");
		// 1、 判断数据修改性
		try {
			if (BaseUtil.isEmpty(orderType)) {
				orderType = "";
			}
			if (BaseUtil.isEmpty(orderNo)
					|| BaseUtil.isEmpty(amount)
					|| !MD5Util.mallMD5(orderNo + amount + orderType)
							.equalsIgnoreCase(key)) {
				serviceCode = "3";
				return ERROR;
			}
			if (BaseUtil.isEmpty(orderType)) {
				orderType = "00";
			}
		} catch (Exception e1) {
			logger.warn(e1);
			serviceCode = "3";
			return ERROR;
		}
		// 2、判断订单是否存在及金额是否相当
		if (mallPayDataService.validOrders(orderNo, orderType, amount) == false) {
			serviceCode = "3";
			return ERROR;
		}
		// 3、数据加载
		try {
			bankThirdPayVos = mallPayDataService.findBankThirdPays();
			bankPayVos = mallPayDataService.findBankPays();
			payOrderVo = new PayOrderVo();
			payOrderVo.setOrderNo(orderNo);
			payOrderVo.setAmount(amount);
			payOrderVo.setOrderType(BaseUtil.isEmpty(orderType) ? "00"
					: orderType);
			payOrderVo.gntPayInfo();

			// 默认订单
			List<Order> list = orderService.getOrderBySn(orderNo
					.split(","));
			if (list.size() > 0) {
				defaultId = list.get(0).getOrderId().intValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
		}
		return SUCCESS;
	}

	/**
	 * 支付三部曲： 1、支付选择 2、支付确认 3、支付结果
	 * 
	 * 进入2、支付确认（网银支付）
	 * 
	 */
	@RequestMapping("/payShow")
	public ModelAndView payShow() throws FrameworkException {
		ModelAndView ERROR = new ModelAndView("/pay/payIError.jsp");
		ModelAndView SUCCESS = new ModelAndView("/pay/netPay.jsp");
		try {
			payOrderVo.parsePayInfo();
			// 判断订单是否存在及金额是否相当
			if (mallPayDataService.validOrders(payOrderVo.getOrderNo(),
					payOrderVo.getOrderType(), payOrderVo.getAmount()) == false) {
				serviceCode = "3";
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.warn(e);
			serviceCode = "3";
			return ERROR;
		}
	}

	/**
	 * 支付三部曲： 1、支付选择 2、支付确认 3、支付结果
	 * 
	 * 进入2、步支付确认(快捷支付)
	 * 
	 */
	@RequestMapping("/bindBankCard")
	public ModelAndView bindBankCard() throws FrameworkException {
		ModelAndView ERROR = new ModelAndView("/pay/payIError.jsp");
		ModelAndView SUCCESS = new ModelAndView("/pay/quickPay.jsp");
		try {
			payOrderVo.parsePayInfo();
			// 判断订单是否存在及金额是否相当
			if (mallPayDataService.validOrders(payOrderVo.getOrderNo(),
					payOrderVo.getOrderType(), payOrderVo.getAmount()) == false) {
				serviceCode = "3";
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.warn(e);
			e.printStackTrace();
			serviceCode = "3";
			return ERROR;
		}
	}

	/**
	 * 开启正式支付过程（快捷）
	 */
	@RequestMapping("/quickPay")
	public ModelAndView quickPay(HttpServletResponse response)
			throws FrameworkException {
		ModelAndView ERROR = new ModelAndView("/pay/payIError.jsp");
		long userId = 0;
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Object object = auth.getPrincipal();
		if (object != null && object instanceof CustomUserDetails) {
			CustomUserDetails user = (CustomUserDetails) object;
			userId = user.getUserId();
		}
		try {
			payOrderVo.parsePayInfo();
			// 判断订单是否存在及金额是否相当
			if (mallPayDataService.validOrders(payOrderVo.getOrderNo(),
					payOrderVo.getOrderType(), payOrderVo.getAmount()) == false) {
				serviceCode = "3";
				return ERROR;
			}
			String sHtmlText = mallPayInterfaceService.bankDirect99Bill(
					payOrderVo.getOrderNo().split(","),
					payOrderVo.getOrderType(),
					Float.parseFloat(payOrderVo.getAmount()),
					payOrderVo.getBankTCode(),
					Integer.parseInt(payOrderVo.getCardType()), userId);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(sHtmlText);
			return null;
		} catch (Exception e) {
			logger.warn(e);
		}
		return ERROR;
	}

	/**
	 * 开启正式支付过程（网银）
	 */
	@RequestMapping("/netPay")
	public ModelAndView netPay(HttpServletResponse response)
			throws FrameworkException {
		ModelAndView ERROR = new ModelAndView("/pay/payIError.jsp");
		long userId = 0;
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Object object = auth.getPrincipal();
		if (object != null && object instanceof CustomUserDetails) {
			CustomUserDetails user = (CustomUserDetails) object;
			userId = user.getUserId();
		}
		try {
			payOrderVo.parsePayInfo();
			// 判断订单是否存在及金额是否相当
			if (mallPayDataService.validOrders(payOrderVo.getOrderNo(),
					payOrderVo.getOrderType(), payOrderVo.getAmount()) == false) {
				serviceCode = "3";
				return ERROR;
			}
			// 网银在线
			if ("2".equalsIgnoreCase(payOrderVo.getThirdId())) {
				String sHtmlText = mallPayInterfaceService.bankDirectChianbank(
						payOrderVo.getOrderNo().split(","),
						payOrderVo.getOrderType(),
						Float.parseFloat(payOrderVo.getAmount()),
						payOrderVo.getBankTCode(),
						Integer.parseInt(payOrderVo.getCardType()), userId);
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write(sHtmlText);
				return null;
			} // 快钱
			else if ("3".equalsIgnoreCase(payOrderVo.getThirdId())) {
				String sHtmlText = mallPayInterfaceService.bankDirect99Bill(
						payOrderVo.getOrderNo().split(","),
						payOrderVo.getOrderType(),
						Float.parseFloat(payOrderVo.getAmount()),
						payOrderVo.getBankTCode(),
						Integer.parseInt(payOrderVo.getCardType()), userId);
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write(sHtmlText);
				return null;
			}

		} catch (Exception e) {
			// e.printStackTrace();
			logger.warn(e);
		}
		return ERROR;
	}

	/*******************************/
	@RequestMapping("/payPlatform")
	public ModelAndView payPlatform(HttpServletRequest request,HttpServletResponse response)
			throws FrameworkException {
		ModelAndView ERROR = new ModelAndView("/pay/payIError.jsp");
		ModelAndView SUCCESS = new ModelAndView("/pay/payWeixin.jsp");
		long userId = 0;
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Object object = auth.getPrincipal();
		if (object != null && object instanceof CustomUserDetails) {
			CustomUserDetails user = (CustomUserDetails) object;
			userId = user.getUserId();
		}
		try {
			payOrderVo.parsePayInfo();
			// 判断订单是否存在及金额是否相当
			if (mallPayDataService.validOrders(payOrderVo.getOrderNo(),
					payOrderVo.getOrderType(), payOrderVo.getAmount()) == false) {
				serviceCode = "3";
				return ERROR;
			}
			String baseUri = request.getRequestURL().toString();
			baseUri = baseUri.substring(0, baseUri.indexOf(request.getRequestURI()));
			baseUri += request.getContextPath();
			
			// 银联
			if ("1".equalsIgnoreCase(payOrderVo.getThirdId())) {
				String sHtmlText = mallPayInterfaceService.transferChianpay(
						payOrderVo.getOrderNo().split(","),
						payOrderVo.getOrderType(),
						Float.parseFloat(payOrderVo.getAmount()), userId);
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write(sHtmlText);
				return null;
			} else
			// 网银在线
			if ("2".equalsIgnoreCase(payOrderVo.getThirdId())) {
				String sHtmlText = mallPayInterfaceService.transferChianbank(
						payOrderVo.getOrderNo().split(","),
						payOrderVo.getOrderType(),
						Float.parseFloat(payOrderVo.getAmount()), userId);
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write(sHtmlText);
				return null;
			} // 快钱
			else if ("3".equalsIgnoreCase(payOrderVo.getThirdId())) {
				String sHtmlText = mallPayInterfaceService.transfer99Bill(
						payOrderVo.getOrderNo().split(","),
						payOrderVo.getOrderType(),
						Float.parseFloat(payOrderVo.getAmount()), userId);
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write(sHtmlText);
				return null;
			}
			// 支付宝
			else if ("4".equalsIgnoreCase(payOrderVo.getThirdId())) {
				String sHtmlText = mallPayInterfaceService.bankAlipay(
						payOrderVo.getOrderNo().split(","),
						payOrderVo.getOrderType(),
						Float.parseFloat(payOrderVo.getAmount()), userId,
						AlipayConfig.subject, AlipayConfig.body,
						AlipayConfig.show_Url, AlipayConfig.anti_phishing_key,
						AlipayConfig.exter_invoke_ip,baseUri);
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write(sHtmlText);

				return null;
			}// 微信
			else if ("5".equalsIgnoreCase(payOrderVo.getThirdId())) {
				UnifiedorderResData respData = mallPayInterfaceService
						.bankWeixin(Configure.getAppID4mp(), Configure
								.getMchID4mp(), Configure.getPwd4mp(),
								Configure.getCertLocalPath4mp(), Configure
										.getCertPassword4mp(), payOrderVo
										.getOrderNo().split(","), payOrderVo
										.getOrderType(), Float
										.parseFloat(payOrderVo.getAmount()),
								userId, PayConfig.WEIXIN_receive_url,
								"192.168.0.11", "NATIVE", null);
				String code_url = respData.getCode_url();
				orderNo = respData.getDevice_info();
				code = Base64.encode(code_url.getBytes());
				key = MD5Util.mallMD5(payOrderVo.getOrderNo()
						+ payOrderVo.getAmount());
				return SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
		}
		return ERROR;
	}

	/*******************************/
	/**
	 * 开启正式快捷支付过程（快捷）
	 */
	@RequestMapping("/quickPayKj")
	public ModelAndView quickPayKj(HttpServletResponse response)
			throws FrameworkException {
		ModelAndView ERROR = new ModelAndView("/pay/payIError.jsp");
		long userId = 0;
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Object object = auth.getPrincipal();
		if (object != null && object instanceof CustomUserDetails) {
			CustomUserDetails user = (CustomUserDetails) object;
			userId = user.getUserId();
		}
		try {
			String sHtmlText = mallPayInterfaceService.bankQuick99BillKj(
					"1420912102".split(","), "00", 1F, "1011", userId);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(sHtmlText);
			return null;
		} catch (Exception e) {
			logger.warn(e);
		}
		return ERROR;
	}

	/*******************************/
	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public List<BankThirdPayVo> getBankThirdPayVos() {
		return bankThirdPayVos;
	}

	public void setBankThirdPayVos(List<BankThirdPayVo> bankThirdPayVos) {
		this.bankThirdPayVos = bankThirdPayVos;
	}

	public List<BankPayVo> getBankPayVos() {
		return bankPayVos;
	}

	public void setBankPayVos(List<BankPayVo> bankPayVos) {
		this.bankPayVos = bankPayVos;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getDefaultId() {
		return defaultId;
	}
}
