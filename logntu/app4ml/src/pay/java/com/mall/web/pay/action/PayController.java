package com.mall.web.pay.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.util.BaseUtil;
import com.mall.common.util.StringUtil;
import com.mall.web.mall.common.annotation.MemberAuth;
import com.mall.web.mall.common.utils.CkSessionUtils;
import com.mall.web.mall.common.utils.MallEnum;
import com.mall.web.mall.common.vo.DataJsonBean;
import com.mall.web.mall.common.vo.ServerZoneBean;
import com.mall.web.mall.domain.Order;
import com.mall.web.mall.member.service.MemberPayNoticeService;
import com.mall.web.mall.member.service.WebMemberDepositService;
import com.mall.web.mall.member.service.WebMemberService;
import com.mall.web.mall.member.vo.WebMemberDepositVo;
import com.mall.web.mall.member.vo.WebMemberVo;
import com.mall.web.mall.order.service.OrderService;
import com.mall.web.mall.order.vo.WebOrderVo;
import com.mall.web.mall.third.alipay.AlipayConfig;
import com.mall.web.pay.conf.PayConfig;
import com.mall.web.pay.service.PayInterfaceService;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import com.tencent.protocol.qr.unifiedorder_protocol.UnifiedorderResData;

@Controller
@RequestMapping("/pay/*")
public class PayController {
	private static Logger logger = Logger.getLogger(PayController.class);

	@Resource(name = "webMemberService")
	private WebMemberService webMemberService;
	@Resource(name = "orderService")
	private OrderService orderService;
	@Resource(name = "payInterfaceService")
	private PayInterfaceService payInterfaceService;
	@Autowired
	protected WebMemberDepositService webMemberDepositService;// 會員賬戶信息
	@Autowired
	private MemberPayNoticeService memberPayNoticeService;//支付通知
	
	/**
	 * 成功支付界面（导航）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("payR4Succ")
	@MemberAuth(verifyLogin = false)
	public ModelAndView payR4Succ(HttpServletRequest request, HttpServletResponse response, String returnUrl) {
		ModelAndView SUCCESS = new ModelAndView("person/pay/payResult");
		return SUCCESS;
	}
	/**
	 * 失败支付界面（导航）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("payR4Erro")
	@MemberAuth(verifyLogin = false)
	public ModelAndView payR4Erro(HttpServletRequest request, HttpServletResponse response, String returnUrl) {
		ModelAndView ERROR = new ModelAndView("person/pay/payError");
		return ERROR;
	}
	/**
	 * 游戏支付界面（无需登录）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("pay4nl")
	@MemberAuth(verifyLogin = false)
	public ModelAndView pay4nl(HttpServletRequest request, HttpServletResponse response, String returnUrl) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/pay/pay4nl");
		return mav;
	}

	/**
	 * 龙币支付界面（须登录）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("pay4l")
	@MemberAuth(verifyLogin = true)
	public ModelAndView pay4l(HttpServletRequest request, HttpServletResponse response, String returnUrl) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/pay/pay4l");
		return mav;
	}

	/**
	 * 获取区域服务器
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("serverZone")
	@ResponseBody
	@MemberAuth(verifyLogin = false)
	public Object serverZone(String serverId) {
		List<ServerZoneBean> value = new LinkedList<ServerZoneBean>();
		List<ServerZoneBean> list = null;
		if (BaseUtil.isEmpty(serverId)) {
			list = DataJsonBean.serverZoneBean.getSubZones();
		} else {
			ServerZoneBean zone = DataJsonBean.serverZoneMap.get(serverId);
			list = (zone != null ? zone.getSubZones() : null);
		}
		for (ServerZoneBean zone : list) {
			ServerZoneBean zoneBean = new ServerZoneBean();
			zoneBean.copySplValue(zone);
			value.add(zoneBean);
		}
		return value;
	}

	/**
	 * 获取用户所在区域服务器的角色列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("serverRole")
	@ResponseBody
	@MemberAuth(verifyLogin = false)
	public Object serverRole(HttpServletResponse response, String userName, String serverId) {
		try {
			String roles = "[{\"roleId\":\"abcde-efadfee-xafeef-xxxxx\",\"roleName\":\"杀破浪\"},{\"roleId\":\"abcde-efadfee-xafeef-ffff\",\"roleName\":\"东西南白\"}]";
			response.setContentType("text/html;charset=UTF-8");
			response.getOutputStream().write(roles.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
		}
		return null;
	}

	/**
	 * 获取用户名是否存在
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("validUn")
	@ResponseBody
	@MemberAuth(verifyLogin = false)
	public Object validUn(HttpServletResponse response, String userName) {
		Map<String, Integer> result = new HashMap<String, Integer>();
		try {
			if (BaseUtil.isEmpty(userName) || BaseUtil.isEmpty(webMemberService.getUserInfo(userName))) {
				result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.DataErr.getIndex());
			} else {
				result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.Success.getIndex());
			}
		} catch (Exception e) {
			result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.SysErr.getIndex());
			e.printStackTrace();
			logger.warn(e);
		}
		return result;
	}

	/**
	 * 获取用户名、所在服務器id角色是否存在
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("validUnRl")
	@ResponseBody
	@MemberAuth(verifyLogin = false)
	public Object validUnRl(HttpServletResponse response, String userName, String serverCode, String roleCode) {
		Map<String, Integer> result = new HashMap<String, Integer>();
		try {
			if (BaseUtil.isEmpty(userName) || BaseUtil.isEmpty(serverCode) || BaseUtil.isEmpty(roleCode)) {
				result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.DataErr.getIndex());
			} else if (BaseUtil.isEmpty(webMemberService.getUserInfo(userName))) {
				result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.OtherErr.getIndex());
			} else {
				result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.Success.getIndex());
			}
		} catch (Exception e) {
			result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.SysErr.getIndex());
			e.printStackTrace();
			logger.warn(e);
		}
		return result;
	}

	/*******************************//*******************************/
	/**
	 * 1、余额支付(判断是否可以退通过余额进行支付)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("checkBalance")
	@ResponseBody
	@MemberAuth(verifyLogin = false)
	public Object checkBalance(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			// 登陆用户信息
			WebMemberVo user = CkSessionUtils.getUser(request);
			// 1、用户是否登陆
			if (BaseUtil.isEmpty(user)) {
				result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.DataErr.getIndex());
				return result;
			} else {
				// 2、用户账户余额是否大于0
				WebMemberDepositVo desposit = webMemberDepositService.getMemberDeposit(user.getUserId());
				if (BaseUtil.isNotEmpty(desposit) && desposit.getBalance() > 0) {
					result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.Success.getIndex());
					result.put("desposit", desposit);
					return result;
				}
			}
			result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.OtherErr.getIndex());
		} catch (Exception e) {
			result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.SysErr.getIndex());
			e.printStackTrace();
			logger.warn(e);
		}
		return result;
	}

	/**
	 * 1、余额支付
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("balancePay")
	@MemberAuth(verifyLogin = false)
	public ModelAndView balancePay(HttpServletRequest request, HttpServletResponse response, String userName,
			WebOrderVo orderVo) {
		ModelAndView mav = new ModelAndView();
		// 判断提交处理返回路径
		String return_url = "person/pay/pay4nl";
		String referer = request.getHeader("Referer");
		// 充值游戏
		if (!BaseUtil.isEmpty(referer) && referer.indexOf("pay4nl") >= 0) {
			return_url = "person/pay/pay4nl";
		} // 充值游戏
		else if (!BaseUtil.isEmpty(referer) && referer.indexOf("pay4l") >= 0) {
			return_url = "person/pay/pay4l";
		}
		// 判断是否符合余额支付
		// 登陆用户信息
		WebMemberVo user = CkSessionUtils.getUser(request);
		// 1、用户是否登陆
		if (BaseUtil.isEmpty(user)) {
			mav.addObject(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.DataErr.getIndex());
			mav.setViewName(return_url);
			return mav;
		}
		// 2、用户账户余额是否大于0
		WebMemberDepositVo desposit = webMemberDepositService.getMemberDeposit(user.getUserId());
		if (BaseUtil.isEmpty(desposit) || desposit.getBalance() <= 0) {
			mav.addObject(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.OtherErr.getIndex());
			mav.setViewName(return_url);
			return mav;
		}
		Object orderResult = order4GameUtil(request, response, userName, orderVo);
		// 返回错误
		if (orderResult instanceof HashMap) {
			mav.addAllObjects((Map<String, Object>) orderResult);
			mav.setViewName(return_url);
			return mav;
		}
		Map<String, Object> result = new HashMap<String, Object>();
		if (orderResult instanceof Order) {
			Order order = (Order) orderResult;
			// 处理支付
			try {
				//余额支付支付成功
				if(webMemberDepositService.reduceBalance(user.getUserId(), order.getOrderAmount().doubleValue())){
					//修改支付状态（已支付）
					order.setStatus((int)MallEnum.OrderStatus.ORDER_PAYED.getIndex());
					orderService.updateOrder(order);
					//支付后处理，游戏通知
					if(MallEnum.GoodsType.consumption2product.getIndex().equalsIgnoreCase(order.getType())
							||MallEnum.GoodsType.consumption2game.getIndex().equalsIgnoreCase(order.getType())){				
						memberPayNoticeService.payNoticeQueue(order);
					}					
					result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.Success.getIndex());
					result.put("orderNo",order.getOrderSn());
					result.put("amount",StringUtil.yunPrice2yunString(order.getOrderAmount().doubleValue()));
					mav.setViewName("person/pay/payResult");
					return mav;
				};
			} catch (Exception e) {
				e.printStackTrace();
				logger.warn(e);
				result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.SysErr.getIndex());
			}
		}
		mav.addAllObjects(result);
		mav.setViewName("person/pay/payError");
		return mav;
	}

	/**
	 * 2、支付(支付宝)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("alipay")
	@MemberAuth(verifyLogin = false)
	public ModelAndView alipay(HttpServletRequest request, HttpServletResponse response, String userName,
			WebOrderVo orderVo) {
		ModelAndView mav = new ModelAndView();
		// 判断提交处理返回路径
		String return_url = "person/pay/pay4nl";
		String referer = request.getHeader("Referer");
		// 充值游戏
		if (!BaseUtil.isEmpty(referer) && referer.indexOf("pay4nl") >= 0) {
			return_url = "person/pay/pay4nl";
		} // 充值游戏
		else if (!BaseUtil.isEmpty(referer) && referer.indexOf("pay4l") >= 0) {
			return_url = "person/pay/pay4l";
		}
		Object orderResult = order4GameUtil(request, response, userName, orderVo);
		// 返回错误
		if (orderResult instanceof HashMap) {
			mav.addAllObjects((Map<String, Object>) orderResult);
			mav.setViewName(return_url);
			return mav;
		}
		if (orderResult instanceof Order) {
			Order order = (Order) orderResult;

			String baseUri = request.getRequestURL().toString();
			baseUri = baseUri.substring(0, baseUri.indexOf(request.getRequestURI()));
			baseUri += request.getContextPath();

			// 处理支付
			try {
				String sHtmlText = payInterfaceService.bankAlipay(order.getOrderSn().split(","), order.getType(),
						order.getOrderAmount().floatValue(), order.getBuyerId(), AlipayConfig.subject,
						AlipayConfig.body + ":" + order.getOrderAmount().floatValue(), AlipayConfig.show_Url,
						AlipayConfig.anti_phishing_key, AlipayConfig.exter_invoke_ip, baseUri);
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write(sHtmlText);
			} catch (Exception e) {
				e.printStackTrace();
				logger.warn(e);
			}
		}
		return null;
	}

	/*******************************/
	/**
	 * 3、支付(微信)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("wxpay")
	@MemberAuth(verifyLogin = false)
	public ModelAndView wxpay(HttpServletRequest request, HttpServletResponse response, String userName,
			WebOrderVo orderVo) {
		ModelAndView mav = new ModelAndView();
		// 判断提交处理返回路径
		String return_url = "person/pay/pay4nl";
		String referer = request.getHeader("Referer");
		// 充值游戏
		if (!BaseUtil.isEmpty(referer) && referer.indexOf("pay4nl") >= 0) {
			return_url = "person/pay/pay4nl";
		} // 充值游戏
		else if (!BaseUtil.isEmpty(referer) && referer.indexOf("pay4l") >= 0) {
			return_url = "person/pay/pay4l";
		}
		Object orderResult = order4GameUtil(request, response, userName, orderVo);
		// 返回错误
		if (orderResult instanceof HashMap) {
			mav.addAllObjects((Map<String, Object>) orderResult);
			mav.setViewName(return_url);
			return mav;
		}
		Map<String, Object> result = new HashMap<String, Object>();
		if (orderResult instanceof Order) {
			Order order = (Order) orderResult;
			// 处理支付
			try {
				UnifiedorderResData respData = payInterfaceService.bankWeixin(order.getOrderSn().split(","),
						order.getType(), order.getOrderAmount().doubleValue(), 0, order.getBuyerId(),
						PayConfig.WEIXIN_receive_url, "192.168.0.11");
				String code_url = respData.getCode_url();
				result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.Success.getIndex());
				result.put("orderSn", order.getOrderSn());
				result.put("orderNo", respData.getDevice_info());
				result.put("orderCode", Base64.encode(code_url.getBytes()));
				result.put("orderAmount", StringUtil.yunPrice2yunString(order.getOrderAmount().doubleValue()));
			} catch (Exception e) {
				e.printStackTrace();
				logger.warn(e);
				result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.SysErr.getIndex());
			}
		}
		mav.addAllObjects(result);
		mav.setViewName("person/pay/payWeixin");
		return mav;
	}
	/*******************************/
	/**
	 * 4、支付(银联)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("unionpay")
	@MemberAuth(verifyLogin = false)
	public ModelAndView unionpay(HttpServletRequest request, HttpServletResponse response, String userName,
			WebOrderVo orderVo) {
		ModelAndView mav = new ModelAndView();
		// 判断提交处理返回路径
		String return_url = "person/pay/pay4nl";
		String referer = request.getHeader("Referer");
		// 充值游戏
		if (!BaseUtil.isEmpty(referer) && referer.indexOf("pay4nl") >= 0) {
			return_url = "person/pay/pay4nl";
		} // 充值游戏
		else if (!BaseUtil.isEmpty(referer) && referer.indexOf("pay4l") >= 0) {
			return_url = "person/pay/pay4l";
		}
		Object orderResult = order4GameUtil(request, response, userName, orderVo);
		// 返回错误
		if (orderResult instanceof HashMap) {
			mav.addAllObjects((Map<String, Object>) orderResult);
			mav.setViewName(return_url);
			return mav;
		}
		String baseUri = request.getRequestURL().toString();
		baseUri = baseUri.substring(0, baseUri.indexOf(request.getRequestURI()));
		baseUri += request.getContextPath();
		
		if (orderResult instanceof Order) {
			Order order = (Order) orderResult;
			// 处理支付
			try {
				String sHtmlText= payInterfaceService.transferChianpay2016(order.getOrderSn().split(","),
						order.getType(), order.getOrderAmount().doubleValue(), 0, order.getBuyerId(),baseUri);
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write(sHtmlText);
			} catch (Exception e) {
				e.printStackTrace();
				logger.warn(e);
			}
		}
		return null;
	}
	/*******************************/
	/**
	 * 订单生产辅助
	 * 
	 * @param request
	 * @param response
	 * @param userName
	 *            受益用户
	 * @param orderVo
	 *            订单信息
	 * @return Object 订单或失败信息
	 */
	public Object order4GameUtil(HttpServletRequest request, HttpServletResponse response, String userName,
			WebOrderVo orderVo) {
		Map<String, Integer> result = new HashMap<String, Integer>();
		if (BaseUtil.isEmpty(userName) || BaseUtil.isEmpty(orderVo) || BaseUtil.isEmpty(orderVo.getOrderAmount())
				|| orderVo.getOrderAmount().doubleValue() < 10/* RMB10元以下不允许充值 */) {
			result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.DataErr.getIndex());
			return result;
		}
		// 受益用户信息
		WebMemberVo benefit = webMemberService.getUserInfo(userName);
		if (BaseUtil.isEmpty(benefit)) {
			result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.DataErr.getIndex());
			return result;
		}
		// TODO 测试使用
		orderVo.setOrderAmount(BigDecimal.valueOf(0.01));
		// 登陆用户信息
		WebMemberVo user = CkSessionUtils.getUser(request);
		user = (user == null ? benefit : user);
		/*******/
		// 处理业务(保存訂單)
		Order order = orderService.saveOrder4Game(user == null ? benefit : user, benefit, orderVo);
		return order;
	}
}