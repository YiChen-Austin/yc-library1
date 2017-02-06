package com.mall.web.mobile.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.util.BaseUtil;
import com.mall.common.util.CryptoTools;
import com.mall.web.mall.domain.MemberDeposit;
import com.mall.web.mall.member.service.WebMemberDepositService;
import com.mall.web.mall.order.service.OrderService;
import com.mall.web.mall.order.vo.WebOrderVo;
import com.mall.web.mall.security.vo.CustomUserDetails;
import com.mall.web.mobile.common.util.UserUtil;
import com.mall.web.mobile.member.vo.MobMemberVo;

//参考的是PayController
@Controller
@RequestMapping("/mobile/order/*")
public class MobOrderController {

	private static Logger logger = Logger.getLogger(MobOrderController.class);

	@Resource(name = "orderService")
	private OrderService orderService;
	// 账户信息
	@Resource(name = "webMemberDepositService")
	private WebMemberDepositService webMemberDepositService;

	@RequestMapping("newOrder")
	public ModelAndView newOrder(HttpServletRequest request) {
		// 未登录，进入登录界面，通过登录后，进行返回跳转
		if (UserUtil.isUserLogin(request) == false)
			return UserUtil.toUserLogin(request, UserUtil.Login_URL_PRF
					+ "/index1201");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/order/newOrder");
		return mav;
	}

	@RequestMapping("orderDetailNav")
	public ModelAndView orderDetail(HttpServletRequest request) {
		// 未登录，进入登录界面，通过登录后，进行返回跳转
		if (UserUtil.isUserLogin(request) == false)
			return UserUtil.toUserLogin(request, UserUtil.Login_URL_PRF
					+ "/order/orderDetailNav");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/order/orderDetail");
		return mav;
	}

	@RequestMapping("orderList")
	public ModelAndView orderList(HttpServletRequest request) {
		// 未登录，进入登录界面，通过登录后，进行返回跳转
		if (UserUtil.isUserLogin(request) == false)
			return UserUtil.toUserLogin(request, UserUtil.Login_URL_PRF
					+ "/order/orderList");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/order/orderList");
		return mav;
	}


	/**
	 * @Description(功能描述) : 买家订单列表
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年11月6日 上午9:56:57
	 * @return
	 */
	@ResponseBody
	@RequestMapping("oGoods4Buy")
	public Map<String, Object> orderGoods4Buy(HttpServletRequest request,
			String status) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			int iStatus = 0;
			if (BaseUtil.isNotEmpty(status))
				iStatus = Integer.parseInt(status);
			MobMemberVo vo = UserUtil.getInstatnce().getUser(request); // 新的会员信息获取方式！！！！
			if (vo == null) {
				jsonMap.put("meg", "未登录过期，请重新登录");
				jsonMap.put("serviceCode", "9"); // 未登陆
			} else {
				List<WebOrderVo> oGoodsList = orderService.findOrderByBuy(
						vo.getUserId(), iStatus);
				jsonMap.put("oGoodsList", oGoodsList);
				jsonMap.put("serviceCode", "0"); // 成功
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("meg", "获取购物车信息失败");
			jsonMap.put("serviceCode", "8");
		}
		return jsonMap;
	}

	/**
	 * @Description(功能描述) : 卖家订单列表
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年11月6日 上午9:56:57
	 * @return
	 */
	@ResponseBody
	@RequestMapping("oGoods4Sell")
	public Map<String, Object> orderGoods4Sell(HttpServletRequest request,
			String status) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			int iStatus = 0;
			if (BaseUtil.isNotEmpty(status))
				iStatus = Integer.parseInt(status);
			MobMemberVo vo = UserUtil.getInstatnce().getUser(request); // 新的会员信息获取方式！！！！
			if (vo == null) {
				jsonMap.put("meg", "未登录过期，请重新登录");
				jsonMap.put("serviceCode", "9"); // 未登陆
			} else {
				List<WebOrderVo> oGoodsList = orderService.findOrderBySell(
						vo.getUserId(), iStatus);
				jsonMap.put("oGoodsList", oGoodsList);
				jsonMap.put("serviceCode", "0"); // 成功
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("meg", "获取购物车信息失败");
			jsonMap.put("serviceCode", "8");
		}
		return jsonMap;
	}

	/**
	 * @Description(功能描述) : 订单详情
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年11月6日 上午9:56:57
	 * @return
	 */
	@ResponseBody
	@RequestMapping("orderDetail")
	public Map<String, Object> orderDetail(HttpServletRequest request,
			int orderId) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			MobMemberVo vo = UserUtil.getInstatnce().getUser(request); // 新的会员信息获取方式！！！！
//			if (vo == null) {
//				jsonMap.put("meg", "未登录过期，请重新登录");
//				jsonMap.put("serviceCode", "9"); // 未登陆
//			} else {
//				WebOrderVo detail = orderService.getOrderById(orderId);
//				jsonMap.put("oDetail", detail);
//				jsonMap.put("serviceCode", "0"); // 成功
//			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("meg", "获取购物车信息失败");
			jsonMap.put("serviceCode", "8");
		}
		return jsonMap;
	}

	/**
	 * @Description(功能描述) : 删除订单（已取消）
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年11月6日 上午9:56:57
	 * @return
	 */
	@ResponseBody
	@RequestMapping("delOrder")
	public Map<String, Object> delOrder4Cancel(HttpServletRequest request,
			int orderId) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			MobMemberVo vo = UserUtil.getInstatnce().getUser(request); // 新的会员信息获取方式！！！！
			if (vo == null) {
				jsonMap.put("meg", "未登录过期，请重新登录");
				jsonMap.put("serviceCode", "9"); // 未登陆
			} else {
				orderService.delOrderById(orderId);
				jsonMap.put("serviceCode", "0"); // 成功
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("meg", "获取购物车信息失败");
			jsonMap.put("serviceCode", "8");
		}
		return jsonMap;
	}

	/**
	 * @Description(功能描述) : 已收货
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年11月6日 上午9:56:57
	 * @return
	 */
	@ResponseBody
	@RequestMapping("receiveOrder")
	public Map<String, Object> receiveOrder(HttpServletRequest request,
			int orderId) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			MobMemberVo vo = UserUtil.getInstatnce().getUser(request); // 新的会员信息获取方式！！！！
			if (vo == null) {
				jsonMap.put("meg", "未登录过期，请重新登录");
				jsonMap.put("serviceCode", "9"); // 未登陆
			} else {
				orderService.updateOrder4Rec(orderId);
				jsonMap.put("serviceCode", "0"); // 成功
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("meg", "获取购物车信息失败");
			jsonMap.put("serviceCode", "8");
		}
		return jsonMap;
	}

	/**
	 * 
	 * @Description(描述) : 判断支付密码是否正确
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年9月10日 下午3:32:48
	 */
	@ResponseBody
	@RequestMapping("/cppw")
	public Object checkePayPasswd(String passwd) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			if (BaseUtil.isEmpty(passwd)) {
				jsonMap.put("result", false);
			} else {
				CustomUserDetails user = BaseUtil.getUser();
				MemberDeposit vo = webMemberDepositService
						.getMemberDepositById(user.getUserId());

				String ePw = CryptoTools.getInstance().encode2HexStrDelimi(
						passwd, vo.getSalt());
				if (ePw.equals(vo.getPayPass())) {
					jsonMap.put("result", true);
				} else {
					jsonMap.put("result", false);
				}
			}
		} catch (Exception e) {
			jsonMap.put("result", false);
			e.printStackTrace();
			logger.warn(e);
		}
		return jsonMap;
	}
	/**
	 * @Description(功能描述) : 退款状态订单列表
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年11月6日 上午9:56:57
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/oGoodsBuyRetrun")
	public Map<String, Object> orderGoods4BuyRetrun(HttpServletRequest request) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			// int iStatus = 0;
			// if (BaseUtil.isNotEmpty(status))
			// iStatus = Integer.parseInt(status);
			MobMemberVo vo = UserUtil.getInstatnce().getUser(request); // 新的会员信息获取方式！！！！
			if (vo == null) {
				jsonMap.put("meg", "未登录过期，请重新登录");
				jsonMap.put("serviceCode", "9"); // 未登陆
			} else {
				
				jsonMap.put("serviceCode", "0"); // 成功
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("meg", "获取购物车信息失败");
			jsonMap.put("serviceCode", "8");
		}
		return jsonMap;
	}

	@ResponseBody
	@RequestMapping("orderReturnDt")
	public Map<String, Object> orderReturnDt(HttpServletRequest request,
			int orderId) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			MobMemberVo vo = UserUtil.getInstatnce().getUser(request); // 新的会员信息获取方式！！！！
			if (vo == null) {
				jsonMap.put("meg", "未登录过期，请重新登录");
				jsonMap.put("serviceCode", "9"); // 未登陆
			} else {
//				WebReturnOrderVo detail = orderService
//						.getOrderByReturnId(orderId);
//				jsonMap.put("oDetail", detail);
//				jsonMap.put("serviceCode", "0"); // 成功
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("serviceCode", "8");
		}
		return jsonMap;
	}

	// @RequestMapping("/oderLog")
	// public void oderLog(int orderId, String operator, String orderStatus,
	// String changedStatus, String remark, int logTime,HttpServletRequest
	// request) {
	// Map<String, Object> jsonMap = new HashMap<String, Object>();
	// try {
	// MobMemberVo vo = UserUtil.getInstatnce().getUser(request); //
	// 新的会员信息获取方式！！！！
	// if (vo == null) {
	// jsonMap.put("meg", "未登录过期，请重新登录");
	// jsonMap.put("serviceCode", "9"); // 未登陆
	// } else {
	// //订单状态， 改变状态， 当前时间
	// OrderLog entity = new YzmOrderLog(orderId,vo.getUserName(),String
	// remark);
	//
	//
	// orderStatusService.updateLog(entity);
	// jsonMap.put("serviceCode", "0"); // 成功
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// jsonMap.put("meg", "失败");
	// jsonMap.put("serviceCode", "8");
	// }
	// // return jsonMap;
	// }

}
