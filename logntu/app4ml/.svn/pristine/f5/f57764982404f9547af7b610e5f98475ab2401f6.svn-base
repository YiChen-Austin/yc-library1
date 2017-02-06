package com.mall.web.mobile.pay.controller;

import java.util.HashMap;
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

import com.mall.web.mall.common.utils.MallEnum;
import com.mall.web.mall.domain.Order;
import com.mall.web.mall.order.service.OrderService;
import com.mall.web.mall.third.alipay.AlipayConfig;
import com.mall.web.mall.third.wechat.config.WechatConfig;
import com.mall.web.mobile.common.util.UserUtil;
import com.mall.web.mobile.member.vo.MobMemberVo;
import com.mall.web.mobile.third.util.WechatOpenInfo;
import com.mall.web.pay.conf.PayConfig;
import com.mall.web.pay.service.PayInterfaceService;
import com.tencent.common.Configure;
import com.tencent.common.RandomStringGenerator;
import com.tencent.common.Signature;
import com.tencent.protocol.qr.unifiedorder_protocol.UnifiedorderResData;

//参考的是PayController
@Controller
@RequestMapping("/mobile/pay/*")
public class MobPayController {

	private static Logger logger = Logger.getLogger(MobPayController.class);


	// 商品订单
	@Resource(name = "orderService")
	private OrderService orderService;

	@Autowired
	private PayInterfaceService mallPayInterfaceService;

	@RequestMapping("pay")
	public ModelAndView pay(HttpServletRequest request, String orderSn) {
		ModelAndView mav = new ModelAndView();
		MobMemberVo vo = UserUtil.getInstatnce().getUser(request); // 新的会员信息获取方式！！！！
		if (vo == null || orderSn == null) {
			mav.setViewName("redirect:");
			mav.addObject("serviceCode", "9");
			return mav;
		}
//		WebOrderVo order = orderService.findUnsellOrderBySn(orderSn,
//				vo.getUserId());
//		if (order == null) {
//			mav.setViewName("redirect:");
//			mav.addObject("serviceCode", "8");
//			return mav;
//		}
		//mav.addObject("order", order);
		mav.setViewName("mobile/pay/pay");
		return mav;
	}

	/**
	 * @Description(功能描述) : 测试网页
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年11月6日 上午9:56:57
	 * @return
	 */
	@RequestMapping("test")
	public ModelAndView test(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/pay/payInfo");
		return mav;
	}

	/**
	 * @Description(功能描述) : 适用于wap方式的支付
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年11月6日 上午9:56:57
	 * @return
	 */
	@RequestMapping("wappay")
	public ModelAndView wappay(HttpServletRequest request,
			HttpServletResponse response, String payType, String orderSn) {
		ModelAndView mav = new ModelAndView();
		MobMemberVo vo = UserUtil.getInstatnce().getUser(request); // 新的会员信息获取方式！！！！
		if (vo == null || orderSn == null) {
			mav.setViewName("redirect:");
			mav.addObject("serviceCode", "9");
		} else {
			// 支付宝
			if ("4".equalsIgnoreCase(payType)) {
				return alipay(request, response, vo, orderSn);
			}// 微信
			else if ("5".equalsIgnoreCase(payType)) {
				mav.setViewName("redirect:" + WechatConfig.GetAuthorizeUrl
						+ "?appid=" + Configure.getAppID4mp()
						+ "&redirect_uri=" + WechatConfig.RedirectUri4Wxpay
						+ "?orderSn=" + orderSn + "&response_type="
						+ WechatConfig.ResponseType + "&scope="
						+ WechatConfig.SnsapiBase + "&state=123#"
						+ WechatConfig.WechatRedirect);
				// mav.setViewName("redirect:" + WechatConfig.GetAuthorizeUrl
				// + "?appid=" + "wxa476470af4aa2ee9"/*Configure.getAppID4mp()*/
				// + "&redirect_uri="
				// + WechatConfig.RedirectUri4Wxpay + "?orderSn="
				// + orderSn + "&response_type="
				// + WechatConfig.ResponseType + "&scope="
				// + WechatConfig.SnsapiBase + "&state=123#"
				// + WechatConfig.WechatRedirect);
				return mav;
			}
		}
		return mav;
	}

	/**
	 * @Description(功能描述) : 微信支付
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年11月6日 上午9:56:57
	 * @return
	 */
	@RequestMapping("wxpay")
	public ModelAndView wxpay(HttpServletRequest request, String orderSn) {
		ModelAndView mav = new ModelAndView();
		try {
			MobMemberVo vo = UserUtil.getInstatnce().getUser(request); // 新的会员信息获取方式！！！！
			if (vo == null || orderSn == null) {
				mav.setViewName("redirect:");
				mav.addObject("serviceCode", "9");
			} else {
				List<Order> list = orderService.getOrderBySn(orderSn
						.split(","));
				if (list == null || list.size() <= 0) {
					mav.setViewName("redirect:");
					mav.addObject("serviceCode", "8");
				} else {
					float amount = 0;
					for (Order order : list) {
						amount += order.getOrderAmount().floatValue();
					}
					WechatOpenInfo openInfo = WechatConfig.getUserOpenId(
							request, Configure.getAppID4mp(),
							Configure.getAppSecret4mp());
					UnifiedorderResData respData = mallPayInterfaceService
							.bankWeixin(Configure.getAppID4mp(),
									Configure.getMchID4mp(),
									Configure.getPwd4mp(),
									Configure.getCertLocalPath4mp(),
									Configure.getCertPassword4mp(),
									orderSn.split(","), "00", amount,
									vo.getUserId(),
									PayConfig.WEIXIN_receive_url,
									"192.168.0.11", "JSAPI",
									openInfo.getOpenid());

					Map<String, Object> map = new HashMap<String, Object>();

					String _package = "prepay_id=" + respData.getPrepay_id();
					String appId = Configure.getAppID4mp();
					String timeStamp = Long.toString(System.currentTimeMillis() / 1000);
					String nonceStr = RandomStringGenerator.getRandomStringByLength(32);
					String signType = "MD5";

					map.put("appId", appId);
					map.put("timeStamp", timeStamp);
					map.put("nonceStr", nonceStr);
					map.put("package", _package);
					map.put("signType", signType);
					String paySign = Signature.getSign(map,Configure.getPwd4mp());
					map.put("paySign", paySign);
					map.remove("package");
					map.put("_package", _package);

					mav.addObject("serviceCode", "0");
					mav.addObject("orderSn", orderSn);
					mav.addObject("amount", amount);
					mav.addAllObjects(map);
					mav.setViewName("mobile/pay/pay4wx");
//					 WechatOpenInfo openInfo = WechatConfig
//					 .getUserOpenId(request,"wxa476470af4aa2ee9","36d70f83542cef17dcb71a0e4e8ce33b");
//					 UnifiedorderResData respData = mallPayInterfaceService
//					 .bankWeixin("wxa476470af4aa2ee9","1263395401","2468sdfhb654194livvyx359951hgdff",Configure.getCertLocalPath4mp(),Configure.getCertPassword4mp(),new
//					 String[] { orderSn }, "00", amount, vo
//					 .getUserId(), PayConfig.WEIXIN_receive_url,
//					 "192.168.0.11", "JSAPI", openInfo
//					 .getOpenid());
//					
//					 Map<String, Object> map = new HashMap<String, Object>();
//					
//					 String _package = "prepay_id=" + respData.getPrepay_id();
//					 String appId = "wxa476470af4aa2ee9";
//					 String timeStamp = Long
//					 .toString(System.currentTimeMillis() / 1000);
//					 String nonceStr = RandomStringGenerator
//					 .getRandomStringByLength(32);
//					 String signType = "MD5";
//					
//					 map.put("appId", appId);
//					 map.put("timeStamp", timeStamp);
//					 map.put("nonceStr", nonceStr);
//					 map.put("package", _package);
//					 map.put("signType", signType);
//					 String paySign =
//					 Signature.getSign(map,"2468sdfhb654194livvyx359951hgdff");
//					 map.put("paySign", paySign);
//					 map.remove("package");
//					 map.put("_package", _package);
//					
//					 mav.addObject("serviceCode", "0");
//					 mav.addObject("orderSn", orderSn);
//					 mav.addObject("amount", amount);
//					 mav.addAllObjects(map);
//					 mav.setViewName("mobile/pay/pay4wx");
					 
					 System.out.println(">>>>>>>>>>>:"+map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
			mav.setViewName("redirect:");
			mav.addObject("serviceCode", "8");
		}
		return mav;
	}

	/**
	 * @Description(功能描述) : 微信支付
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年11月6日 上午9:56:57
	 * @return
	 */
	@ResponseBody
	@RequestMapping("wxpay4app")
	public Map<String, Object> wxpay4app(HttpServletRequest request,
			String orderSn) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			MobMemberVo vo = UserUtil.getInstatnce().getUser(request); // 新的会员信息获取方式！！！！
			if (vo == null || orderSn == null) {
				result.put(MallEnum.ServiceCodeType.ServiceCode,
						MallEnum.ServiceCodeType.OtherErr.getIndex());
			} else {
				List<Order> list = orderService.getOrderBySn(orderSn
						.split(","));
				if (list == null || list.size() <= 0) {
					result.put(MallEnum.ServiceCodeType.ServiceCode,
							MallEnum.ServiceCodeType.DataErr.getIndex());
				} else {
					float amount = 0;
					for (Order order : list) {
						amount += order.getOrderAmount().floatValue();
					}
					UnifiedorderResData respData = mallPayInterfaceService
							.bankWeixin(Configure.getAppID4openMob(),
									Configure.getMchID4open(),
									Configure.getPwd4open(),
									Configure.getCertLocalPath4open(),
									Configure.getCertPassword4open(),
									orderSn.split(","), "00", amount,
									vo.getUserId(),
									PayConfig.WEIXIN_receive_url,
									"192.168.0.11", "APP", null);

					Map<String, Object> map = new HashMap<String, Object>();

					// String appId = Configure.getAppID4openMob();
					String timeStamp = Long
							.toString(System.currentTimeMillis() / 1000);
					String nonceStr = RandomStringGenerator
							.getRandomStringByLength(32);

					map.put("appid", Configure.getAppID4openMob());
					map.put("partnerid", Configure.getMchID4open());
					map.put("prepayid", respData.getPrepay_id());
					map.put("package", "Sign=WXPay");
					map.put("noncestr", nonceStr);
					map.put("timestamp", timeStamp);
					String paySign = Signature.getSign(map,
							Configure.getPwd4open());
					map.put("sign", paySign);

					// result.put("order", order);
					result.put("wx", map);
					result.put(MallEnum.ServiceCodeType.ServiceCode,
							MallEnum.ServiceCodeType.Success.getIndex());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
		}
		return result;
	}

	/**
	 * @Description(功能描述) : 支付宝支付跳转
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年11月6日 上午9:56:57
	 * @return
	 */
	public ModelAndView alipay(HttpServletRequest request,
			HttpServletResponse response, MobMemberVo vo, String orderSn) {
		ModelAndView mav = new ModelAndView();
		try {
			List<Order> list = orderService.getOrderBySn(orderSn
					.split(","));
			if (list == null || list.size() <= 0) {
				mav.setViewName("redirect:");
				mav.addObject("serviceCode", "8");
			} else {
				float amount = 0;
				for (Order order : list) {
					amount += order.getOrderAmount().floatValue();
				}
				String sHtmlText = mallPayInterfaceService.bankAlipay4wap(
						orderSn.split(","), "00", amount, vo.getUserId(),
						AlipayConfig.subject);
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write(sHtmlText);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
			mav.setViewName("redirect:");
			mav.addObject("serviceCode", "8");
		}
		return mav;
	}

	/**
	 * @Description(功能描述) : 支付宝app支付
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年11月6日 上午9:56:57
	 * @return
	 */
	@ResponseBody
	@RequestMapping("alipay4app")
	public Map<String, Object> alipay4app(HttpServletRequest request,
			String orderSn) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			MobMemberVo vo = UserUtil.getInstatnce().getUser(request);
			if (vo == null || orderSn == null) {
				result.put(MallEnum.ServiceCodeType.ServiceCode,
						MallEnum.ServiceCodeType.OtherErr.getIndex());
			} else {
				List<Order> list = orderService.getOrderBySn(orderSn
						.split(","));
				if (list == null || list.size() <= 0) {
					result.put(MallEnum.ServiceCodeType.ServiceCode,
							MallEnum.ServiceCodeType.DataErr.getIndex());
				} else {
					float amount = 0;
					for (Order order : list) {
						amount += order.getOrderAmount().floatValue();
					}
					String sHtmlText = mallPayInterfaceService.bankAlipay4app(
							orderSn.split(","), "00", amount, vo.getUserId(),
							AlipayConfig.subject);
					result.put("ali", sHtmlText);
					result.put(MallEnum.ServiceCodeType.ServiceCode,
							MallEnum.ServiceCodeType.Success.getIndex());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
			result.put(MallEnum.ServiceCodeType.ServiceCode,
					MallEnum.ServiceCodeType.OtherErr.getIndex());
		}
		return result;
	}

	/**
	 * @Description(功能描述) : 银联app支付
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年11月6日 上午9:56:57
	 * @return
	 */
	@ResponseBody
	@RequestMapping("union4app")
	public Map<String, Object> union4app(HttpServletRequest request,
			String orderSn) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			MobMemberVo vo = UserUtil.getInstatnce().getUser(request);
			if (vo == null || orderSn == null) {
				result.put(MallEnum.ServiceCodeType.ServiceCode,
						MallEnum.ServiceCodeType.OtherErr.getIndex());
			} else {
				List<Order> list = orderService.getOrderBySn(orderSn
						.split(","));
				if (list == null || list.size() <= 0) {
					result.put(MallEnum.ServiceCodeType.ServiceCode,
							MallEnum.ServiceCodeType.DataErr.getIndex());
				} else {
					float amount = 0;
					for (Order order : list) {
						amount += order.getOrderAmount().floatValue();
					}
//					String sHtmlText = mallPayInterfaceService.unionpay4app(
//							orderSn.split(","), "00", amount, vo.getUserId());
//					result.put("tn", sHtmlText);
//					result.put(MallEnum.ServiceCodeType.ServiceCode,
//							MallEnum.ServiceCodeType.Success.getIndex());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
			result.put(MallEnum.ServiceCodeType.ServiceCode,
					MallEnum.ServiceCodeType.OtherErr.getIndex());
		}
		return result;
	}
}
