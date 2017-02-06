package com.mall.web.mall.third.alipay.contorller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.common.util.MD5Util;
import com.mall.web.mall.third.alipay.AlipayConfig;
import com.mall.web.mall.third.alipay.util.AlipayNotify;
import com.mall.web.mall.third.alipay.util.AlipaySubmit;
import com.mall.web.mall.third.qq.service.MemberBoundService;
import com.mall.web.mall.domain.MemberBound;
import com.mall.web.mall.domain.Member;
import com.mall.web.mall.member.service.WebMemberService;


/**
 * @Description(描述)	: 支付宝登录
 * @author(作者)			: wangliyou
 * @date (开发日期)		: 2015年10月20日 下午2:05:48
 */
@Controller
@RequestMapping("/alipay")
public class AlipayLoginController {
	//用户信息
	@Resource(name="webMemberService")
	private WebMemberService webMemberService;
	//绑定
	@Resource(name="memberBoundService")
	private MemberBoundService memberBoundService;
	
	/**
	 * @Description(功能描述)		: 支付宝登录入口
	 * @author(作者)				: wangliyou
	 * @date (开发日期)			: 2015年10月20日 下午5:37:23 
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response)   {
		ModelAndView mav = new ModelAndView();
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "alipay.auth.authorize");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("target_service", AlipayConfig.target_service);
		sParaTemp.put("return_url", AlipayConfig.return_url);
		sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
		mav.addObject("sHtmlText", sHtmlText);
		mav.setViewName("mall/connect/alipay");
		return  mav;
	}
	
	/**
	 * @Description(功能描述)		: 登录成功后的回调函数
	 * @author(作者)				: wangliyou
	 * @date (开发日期)			: 2015年10月20日 下午5:37:23 
	 * @return
	 */
	@RequestMapping("/afterlogin")
	public ModelAndView alipayAfterlogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		try {
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			// 支付宝用户号
			String openID = new String(request.getParameter("user_id").getBytes("ISO-8859-1"), "UTF-8");
			// 授权令牌
			String accessToken = new String(request.getParameter("token").getBytes("ISO-8859-1"), "UTF-8");
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)
			boolean verifyResult = AlipayNotify.verify(params);
			if (verifyResult) { // 验证成功
				request.getSession().setAttribute("alipayToken", accessToken);
				MemberBound boundId = memberBoundService.findMemberBound(openID,2); // 判断是否绑定过
				if (BaseUtil.isNotEmpty(boundId)) {
					mav.setViewName("mall/index");
				} else {
					mav.addObject("openID", openID);
					mav.addObject("accessToken", accessToken);
					mav.setViewName("mall/connect/alipayBound");
				}
			} else {
				mav.setViewName("mall/404");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	/**
	 * @Description(功能描述)		: pc端绑定页面的注册
	 * @author(作者)				: wangliyou
	 * @date (开发日期)			: 2015年10月17日 下午1:46:43 
	 * @param openID			: openid 第三方唯一标识
	 * @param member			: 用户信息 
	 * @authCode 				: 验证码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/register")
	public Object register(String openID, Member member, String authCode, HttpServletRequest request) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			String yzm = (String) request.getSession().getAttribute("authCode");
			if (BaseUtil.isNotEmpty(yzm)) {
				if (authCode.equals(yzm)) {
					Member user = webMemberService.getMember(member.getUserName());
					if (BaseUtil.isEmpty(user)) {
						int userId = webMemberService.saveMember(member);
						MemberBound bound = new MemberBound();
						bound.setBoundTime(DateUtil.getCurrentTime());
						bound.setChannel(2);
						bound.setOpenId(openID);
						bound.setUserId(userId);
						memberBoundService.saveMemberBound(bound);
						jsonMap.put("result", true);
					} else {
						jsonMap.put("result", false);
						jsonMap.put("meg", "注册失败，此手机号已注册");
					}
				} else {
					jsonMap.put("result", false);
					jsonMap.put("meg", "验证码错误，请重新输入");
				}
			} else {
				jsonMap.put("result", false);
				jsonMap.put("meg", "验证码过期或未获取，请重试");
			}
		} catch (Exception e) {
			jsonMap.put("result", false);
			jsonMap.put("meg", "系统处理异常，请稍后再试");
			e.printStackTrace();
		}
		return jsonMap;
	}
	
	/**
	 * @Description(功能描述)		: 支付宝第三方登录绑定
	 * @author(作者)				: wangliyou
	 * @date (开发日期)			: 2015年10月17日 上午11:59:57 
	 * @param openID			: 支付宝的openid
	 * @param member			: 用户参数
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/boundUser")
	public Object boundUser(String openID, Member member) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			member = webMemberService.getUserInfo(member.getUserName(), MD5Util.MD5(member.getPassword()));
			if (BaseUtil.isNotEmpty(member)) { 
				MemberBound bound = new MemberBound();
				bound.setBoundTime(DateUtil.getCurrentTime());
				bound.setChannel(2);
				bound.setOpenId(openID);
				bound.setUserId(member.getUserId());
				memberBoundService.saveMemberBound(bound);
				jsonMap.put("result", "ture");
			} else {
				jsonMap.put("result", "false");
				jsonMap.put("meg", "手机号与密码不匹配，请重新输入");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMap;
	}
}
