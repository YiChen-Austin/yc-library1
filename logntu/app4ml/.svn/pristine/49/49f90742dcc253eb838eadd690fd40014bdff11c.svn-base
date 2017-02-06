package com.mall.web.mobile.third.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.common.util.MD5Util;
import com.mall.web.mall.third.qq.service.MemberBoundService;
import com.mall.web.mall.third.wechat.config.WechatConfig;
import com.mall.web.mall.common.utils.MallEnum.ServiceCodeType;
import com.mall.web.mall.domain.MemberBound;
import com.mall.web.mall.domain.Member;
import com.mall.web.mall.member.service.WebMemberService;
import com.mall.web.mobile.common.util.UserUtil;
import com.mall.web.mobile.member.controller.MobMemberController;
import com.mall.web.mobile.member.service.MobMemberService;
import com.mall.web.mobile.member.vo.MobMemberVo;
import com.mall.web.mobile.third.util.WechatOpenInfo;
import com.mall.web.mobile.third.util.WechatUserInfo;
import com.tencent.common.Configure;

/**
 * @Description(描述) : 微信登录
 * @author(作者) : wangliyou
 * @date (开发日期) : 2015年11月3日 上午9:48:35
 */
@Controller
@RequestMapping("/mobile/wechat")
public class MobWechatLoginController {
	private static Logger logger = Logger
			.getLogger(MobWechatLoginController.class);
	// 用户信息
	@Resource(name = "webMemberService")
	private WebMemberService webMemberService;
	@Resource(name = "mobMemberService")
	private MobMemberService mobMemberService;
	// 绑定
	@Resource(name = "memberBoundService")
	private MemberBoundService memberBoundService;

	/**
	 * @Description(功能描述) : 微信登录入口
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年10月20日 下午5:37:23
	 * @return
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		MobMemberVo vo = UserUtil.getInstatnce().getUser(request);
		if (BaseUtil.isNotEmpty(vo)) {
			return "redirect:" + UserUtil.Login_URL_PRF + "/member/account";
		}
		String url = "redirect:" + WechatConfig.GetAuthorizeUrl + "?appid="
				+ Configure.getAppID4openWeb() + "&redirect_uri="
				+ WechatConfig.RedirectUri4Login + "&response_type="
				+ WechatConfig.ResponseType + "&scope="
				+ WechatConfig.SnsapiBase + "&state=123#"
				+ WechatConfig.WechatRedirect;
		return url;
	}

	@RequestMapping("/loginWc")
	public String loginWc(HttpServletRequest request,
			HttpServletResponse response) {
		MobMemberVo vo = UserUtil.getInstatnce().getUser(request);
		if (BaseUtil.isNotEmpty(vo)) {
			return "redirect:" + UserUtil.Login_URL_PRF + "/member/account";
		}
		String url = "redirect:" + WechatConfig.GetAuthorizeUrl + "?appid="
				+ Configure.getAppID4mp() + "&redirect_uri="
				+ WechatConfig.RedirectUri4Login + "&response_type="
				+ WechatConfig.ResponseType + "&scope="
				+ WechatConfig.SnsapiUserinfo
				+ "&connect_redirect=1&state=123#"
				+ WechatConfig.WechatRedirect;
		return url;
	}

	/**
	 * @Description(功能描述) : 登录成功后的回调函数
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年10月20日 下午5:37:23
	 * @return
	 */
	@RequestMapping("/afterlogin")
	public ModelAndView afterlogin(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		try {
			WechatOpenInfo openInfo = WechatConfig.getUserOpenId(request,
					Configure.getAppID4mp(), Configure.getAppSecret4mp());
			if (BaseUtil.isNotEmpty(openInfo)
					&& BaseUtil.isNotEmpty(openInfo.getOpenid())) {
				MemberBound boundId = memberBoundService
						.findMemberBound(openInfo.getOpenid(), 1); // 判断是否绑定过
				if (BaseUtil.isNotEmpty(boundId)) {
					MobMemberVo vo = mobMemberService.getMemberByid(boundId
							.getUserId());
					vo.setPassword("");
					UserUtil.getInstatnce().setUser(request, vo);
					mav.setViewName("redirect:" + UserUtil.Login_URL_PRF
							+ "/member/account");// 会员中心
				} else {
					WechatUserInfo userInfo = WechatConfig.getUserInfo(
							openInfo.getOpenid(), openInfo.getAccessToken());
					mav.setViewName("redirect:" + UserUtil.Login_URL_PRF
							+ "/common/loginBindSwitch");
					request.getSession().setAttribute("openID",
							openInfo.getOpenid());
					request.getSession().setAttribute("openUserInfoBean",
							userInfo);
					request.getSession().setAttribute("userType", "Wechat");
				}
			} else {
				mav.setViewName("mobile/error/oauth");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("mobile/error/oauth");
			logger.warn(e);
		}
		return mav;
	}

	/**
	 * @Description(功能描述) : 登录成功后的回调函数
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年10月20日 下午5:37:23
	 * @return
	 */
	@RequestMapping("/afterlogin4m")
	@ResponseBody
	public Map<String, Object> afterlogin4m(HttpServletRequest request) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			String code = request.getParameter("code");
			if (BaseUtil.isEmpty(code)) {
				jsonMap.put(ServiceCodeType.ServiceCode,
						ServiceCodeType.OtherErr.getIndex());
			}
			WechatOpenInfo openInfo = WechatConfig
					.getUserOpenId4M(code, Configure.getAppID4openMob(),
							Configure.getAppSec4openMob());
			if (BaseUtil.isNotEmpty(openInfo.getOpenid())) {
				MemberBound boundId = memberBoundService
						.findMemberBound(openInfo.getOpenid(), 1); // 判断是否绑定过
				if (BaseUtil.isNotEmpty(boundId)) {
					MobMemberVo vo = mobMemberService.getMemberByid(boundId
							.getUserId());
					vo.setPassword("");
					UserUtil.getInstatnce().setUser(openInfo.getOpenid(), vo);
					jsonMap.put(ServiceCodeType.ServiceCode,
							ServiceCodeType.Success.getIndex());
					jsonMap.put("row", vo);
					jsonMap.put("token", openInfo.getOpenid());
				} else {
					WechatUserInfo userInfo = WechatConfig.getUserInfo(
							openInfo.getOpenid(), openInfo.getAccessToken());
					// jsonMap.put("openID", openInfo.getOpenid());
					jsonMap.put("openUserInfoBean", userInfo);
					jsonMap.put("userType", "Wechat");
					jsonMap.put(ServiceCodeType.ServiceCode,
							ServiceCodeType.OthSucc.getIndex());
				}
			} else {
				jsonMap.put(ServiceCodeType.ServiceCode,
						ServiceCodeType.OtherErr.getIndex());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
			jsonMap.put(ServiceCodeType.ServiceCode,
					ServiceCodeType.OtherErr.getIndex());
		}
		return jsonMap;
	}

	/**
	 * @Description(功能描述) : pc端绑定页面的注册
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年10月17日 下午1:46:43
	 * @param openID
	 *            : openid 第三方唯一标识
	 * @param member
	 *            : 用户信息
	 * @authCode : 验证码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/register")
	public Object register(String openID, Member member, String authCode,
			HttpServletRequest request) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			String yzm = (String) request.getSession().getAttribute("authCode");
			if (BaseUtil.isNotEmpty(yzm)) {
				if (authCode.equals(yzm)) {
					Member user = webMemberService.getMember(member
							.getUserName());
					if (BaseUtil.isEmpty(user)) { // 不存在此用户
						member.setPassword(MD5Util.MD5(member.getPassword()));
						int userId = webMemberService.saveMember(member);
						MemberBound bound = new MemberBound();
						bound.setBoundTime(DateUtil.getCurrentTime());
						bound.setChannel(1);
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
	 * @Description(功能描述) : 支付宝第三方登录绑定
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年10月17日 上午11:59:57
	 * @param openID
	 *            : 支付宝的openid
	 * @param member
	 *            : 用户参数
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/boundUser")
	public Object boundUser(String openID, Member member) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			member = webMemberService.getUserInfo(member.getUserName(),
					MD5Util.MD5(member.getPassword()));
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

	public static void main(String[] args) {
		System.out.println(MobMemberController.class.getName());
	}
}
