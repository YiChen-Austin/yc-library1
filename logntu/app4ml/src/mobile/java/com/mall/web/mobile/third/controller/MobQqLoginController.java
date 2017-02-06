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
import com.mall.web.mall.third.qq.service.MemberBoundService;
import com.mall.web.mall.third.qq.util.QQuserInfoBeanEx;
import com.mall.web.mall.third.qq.util.QQuserInfoEx;
import com.mall.web.mall.common.utils.MallEnum.ServiceCodeType;
import com.mall.web.mall.domain.MemberBound;
import com.mall.web.mall.member.service.WebMemberService;
import com.mall.web.mobile.common.util.UserUtil;
import com.mall.web.mobile.member.service.MobMemberService;
import com.mall.web.mobile.member.vo.MobMemberVo;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.QQuserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.QQuserInfoBean;
import com.qq.connect.oauth.Oauth;

/**
 * @Description(描述) : 第三方登录
 * @author(作者) : wangliyou
 * @date (开发日期) : 2015年10月12日 下午4:45:49
 */
@Controller
@RequestMapping("/mobile/qq")
public class MobQqLoginController {
	private static Logger logger = Logger.getLogger(MobQqLoginController.class);

	// 用户信息
	@Resource(name = "webMemberService")
	private WebMemberService webMemberService;
	@Resource(name = "mobMemberService")
	private MobMemberService mobMemberService;
	// 绑定
	@Resource(name = "memberBoundService")
	private MemberBoundService memberBoundService;

	/***
	 * @Description(功能描述) : 进入QQ登录
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年10月12日 下午4:49:33
	 * @return
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String qq = "";
		try {
			qq = new Oauth().getAuthorizeURL(request);
		} catch (QQConnectException e) {
			e.printStackTrace();
		}
		return "redirect:" + qq;
	}

	/**
	 * @Description(功能描述) : QQ成功回调登陆
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年10月12日 下午4:50:14
	 * @return
	 */
	@RequestMapping("/afterlogin")
	public ModelAndView qqAfterlogin(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		try {
			AccessToken accessTokenObj = (new Oauth())
					.getAccessTokenByRequest(request);
			String accessToken = null, openID = null;
			if (accessTokenObj.getAccessToken().equals("")) {
				mav.addObject("msg", "很抱歉，我们没能正确获取到您的信息，原因是：没有获取到响应参数!");
				mav.setViewName("mobile/error/oauth");
			} else {
				accessToken = accessTokenObj.getAccessToken();
				OpenID openIDObj = new OpenID(accessToken);
				openID = openIDObj.getUserOpenID();
				MemberBound boundId = memberBoundService
						.findMemberBound(openID, 0); // 判断是否绑定过
				if (BaseUtil.isNotEmpty(boundId)
						&& BaseUtil.isNotEmpty(boundId.getUserId())) {
					MobMemberVo vo = mobMemberService.getMemberByid(boundId
							.getUserId());
					vo.setPassword("");
					UserUtil.getInstatnce().setUser(request, vo);
					mav.setViewName("redirect:/member/account");// 会员中心
				} else {
					request.getSession().setAttribute("openID", openID);
					request.getSession().setAttribute("userType", "qq");

					QQuserInfo qquserInfo = new QQuserInfo(accessToken, openID);
					QQuserInfoBean userInfoBean = qquserInfo.getUserInfo();
					if (userInfoBean.getRet() == 0) {
						request.getSession().setAttribute("openUserInfoBean",
								userInfoBean);
						mav.setViewName("redirect:" + UserUtil.Login_URL_PRF
								+ "/common/loginBindSwitch");
					} else {
						mav.addObject("msg", "很抱歉，我们没能正确获取到您的信息，原因是： "
								+ userInfoBean.getMsg());
						mav.setViewName("mobile/error/oauth");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("msg", "很抱歉，我们没能正确获取到您的信息，原因是：网络连接失败!");
			mav.setViewName("mobile/error/oauth");
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
			String accessToken = request.getParameter("accessToken"), openID = request
					.getParameter("openID");
			if (BaseUtil.isEmpty(accessToken) || BaseUtil.isEmpty(openID)) {
				jsonMap.put(ServiceCodeType.ServiceCode,
						ServiceCodeType.OtherErr.getIndex());
			} else {
				MemberBound boundId = memberBoundService
						.findMemberBound(openID, 0); // 判断是否绑定过
				if (BaseUtil.isNotEmpty(boundId)
						&& BaseUtil.isNotEmpty(boundId.getUserId())) {
					MobMemberVo vo = mobMemberService.getMemberByid(boundId
							.getUserId());
					vo.setPassword("");
					UserUtil.getInstatnce().setUser(openID, vo);
					jsonMap.put(ServiceCodeType.ServiceCode,
							ServiceCodeType.Success.getIndex());
					jsonMap.put("row", vo);
					jsonMap.put("token", openID);
				} else {
					QQuserInfoEx qquserInfo = new QQuserInfoEx(accessToken, openID);
					QQuserInfoBeanEx userInfoBean = qquserInfo.getUserInfoByAppid("1104964224");//app中qq登录所用
					jsonMap.put("openUserInfoBean", userInfoBean);
					jsonMap.put("userType", "qq");
					jsonMap.put(ServiceCodeType.ServiceCode,
							ServiceCodeType.OthSucc.getIndex());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put(ServiceCodeType.ServiceCode,
					ServiceCodeType.OtherErr.getIndex());
		}
		return jsonMap;
	}
}
