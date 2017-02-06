package com.mall.web.mobile.common.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.mall.common.util.BaseUtil;
import com.mall.web.mobile.member.vo.MobMemberVo;

/**
 * @Description(描述) : 用户信息工具类
 * @author(作者) : wangliyou
 * @date (开发日期) : 2015年10月31日 下午1:28:10
 */

public class UserUtil {
	private static UserUtil userUtil = null;
	public static final String WX_QR_GZH="WX_QR_GZH";
	private static Map<String, MobMemberVo> tokenUsers; // 用户信息
	private static Map<String, String> validCodeMap; // 手机验证码

	static {
		userUtil = new UserUtil();
		tokenUsers = new HashMap<String, MobMemberVo>();
		validCodeMap = new HashMap<String, String>();
	}

	public static UserUtil getInstatnce() {
		return userUtil;
	}

	/**
	 * @Description(功能描述) : 根据token(app),cookie(浏览器)值获取用户信息
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年11月6日 下午5:12:30
	 * @param request
	 *            : http请求
	 * @return MobMemberVo 用户信息
	 */
	public MobMemberVo getUser(HttpServletRequest request) {
		MobMemberVo userInfo = null;
		String token = request.getParameter("token");
		if (BaseUtil.isNotEmpty(token)) {
			userInfo = tokenUsers.get(token);
		}
		if (BaseUtil.isEmpty(userInfo)) {
			userInfo = (MobMemberVo) request.getSession().getAttribute(
					"memberInfo");
		}
		return userInfo;
	}

	/**
	 * @Description(功能描述) :cookie(浏览器)方式存放值获取用户信息
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年11月6日 下午5:12:30
	 * @param request
	 *            : http请求
	 * @param user
	 *            : 用户信息
	 */
	public void setUser(HttpServletRequest request, MobMemberVo user) {
		request.getSession().setAttribute("memberInfo", user);
	}

	/**
	 * @Description(功能描述) :token(app)方式存放值获取用户信息
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年11月6日 下午5:12:30
	 * @param token
	 *            : token数值
	 * @param user
	 *            : 用户信息
	 */
	public void setUser(String token, MobMemberVo user) {
		tokenUsers.put(token, user);
	}

	/**
	 * @Description(功能描述) :清除用户信息
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年11月6日 下午5:12:30
	 * @param request
	 *            : http请求
	 */
	public void removeUser(HttpServletRequest request) {
		String token = request.getParameter("token");
		if (BaseUtil.isNotEmpty(token)) {
			tokenUsers.remove(token);
		}
		request.getSession().removeAttribute("memberInfo");
	}

	/**
	 * @Description(功能描述) :记录手机验证码
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年11月6日 下午5:12:30
	 * @param phone
	 *            : 手机号
	 * @param validCode
	 *            : 验证码
	 */
	public void setUserCode(String phone, String validCode) {
		validCodeMap.put(phone, validCode);
	}

	/**
	 * @Description(功能描述) :获取手机验证码
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年11月6日 下午5:12:30
	 * @param phone
	 *            : 手机号
	 */
	public String getUserCode(String phone) {
		return validCodeMap.remove(phone);
	}

	public static boolean isUserLogin(HttpServletRequest request) {
		if (BaseUtil.isNotEmpty(UserUtil.getInstatnce().getUser(request)))
			return true;
		return false;
	}

	public static ModelAndView toUserLogin(HttpServletRequest request,
			String returnUrl) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:" + UserUtil.Login_URL_PRF+"/common/login?returnUrl=" + returnUrl);
		return mav;
	}

    //public static final String Login_URL_PRF = "/mobile"; //本机
	public static final String Login_URL_PRF = "";	//外网
}
