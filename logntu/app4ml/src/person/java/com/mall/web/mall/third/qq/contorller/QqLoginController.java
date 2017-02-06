package com.mall.web.mall.third.qq.contorller;

import java.util.HashMap;
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
import com.mall.web.mall.third.qq.service.MemberBoundService;
import com.mall.web.mall.domain.MemberBound;
import com.mall.web.mall.domain.Member;
import com.mall.web.mall.member.service.WebMemberService;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;

/**
 * @Description(描述) : 第三方登录
 * @author(作者) : wangliyou
 * @date (开发日期) : 2015年10月12日 下午4:45:49
 */
@Controller
@RequestMapping("/qq")
public class QqLoginController {

	// 用户信息
	@Resource(name = "webMemberService")
	private WebMemberService webMemberService;
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
	 * @Description(功能描述) : QQ第三方登录绑定
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年10月17日 上午11:59:57
	 * @param openID
	 *            : QQ的openid
	 * @param accessToken
	 *            : token
	 * @param member
	 *            : 用户参数
	 * @return
	 */
	// @ResponseBody
	// @RequestMapping("/boundUser")
	// public Object boundUser(HttpServletRequest request, WebMemberVo bean) {
	// Map<String, Object> jsonMap = new HashMap<String, Object>();
	// try {
	// member = webMemberService.getUserInfo(member.getUserName(),
	// MD5Util.MD5(member.getPassword()));
	// if (BaseUtil.isNotEmpty(member)) { //
	// UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
	// UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
	// if (userInfoBean.getRet() == 0) {
	// String gender = userInfoBean.getGender();
	// member.setGender(gender.equals("男") ? 0 : 1);
	// member.setRealName(userInfoBean.getNickname());
	// webMemberService.saveMember(member);
	// MallMemberBound bound = new MallMemberBound();
	// bound.setBoundTime(DateUtil.getCurrentTime());
	// bound.setChannel(0);
	// bound.setOpenId(openID);
	// bound.setUserId(member.getUserId());
	// mallMemberBoundService.saveMemberBound(bound);
	// jsonMap.put("result", "ture");
	//
	// request.getSession().removeAttribute("openID");
	// request.getSession().removeAttribute("userInfoBean");
	// } else {
	// jsonMap.put("result", "false");
	// jsonMap.put("meg", "很抱歉，我们没能正确获取到您的信息，原因是：" + userInfoBean.getMsg());
	// System.out.println(userInfoBean.getMsg());
	// }
	// } else {
	// jsonMap.put("result", "false");
	// jsonMap.put("meg", "手机号与密码不匹配，请重新输入");
	// }
	// } catch (QQConnectException e) {
	// e.printStackTrace();
	// }
	// return jsonMap;
	// }

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
				mav.setViewName("404");
			} else {
				accessToken = accessTokenObj.getAccessToken();
				OpenID openIDObj = new OpenID(accessToken);
				openID = openIDObj.getUserOpenID();
				MemberBound boundId = memberBoundService
						.findMemberBound(openID, 0); // 判断是否绑定过
				if (BaseUtil.isNotEmpty(boundId)) {
					mav.setViewName("mall/index");
				} else {
					UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
					UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
					if (userInfoBean.getRet() == 0) {
						mav.addObject("openID", openID);
						mav.addObject("accessToken", accessToken);
						mav.setViewName("mall/connect/qqBound");
					} else {
						mav.addObject("msg", "很抱歉，我们没能正确获取到您的信息，原因是： "
								+ userInfoBean.getMsg());
						System.out.println(userInfoBean.getMsg());
						mav.setViewName("error/oauth");
					}
				}
			}
		} catch (Exception e) {
			mav.addObject("msg", "很抱歉，我们没能正确获取到您的信息，原因是：网络连接失败!");
			e.printStackTrace();
			System.out.println("很抱歉，我们没能正确获取到您的信息，原因是：网络连接失败!");
			mav.setViewName("404");
		}
		return mav;
	}

	/**
	 * @Description(功能描述) : pc端绑定页面的注册
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年10月17日 下午1:46:43
	 * @param openID
	 *            : openid 第三方唯一标识
	 * @param accessToken
	 *            : token
	 * @param member
	 *            : 用户信息
	 * @authCode : 验证码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/register")
	public Object register(String openID, String accessToken, Member member,
			String authCode, HttpServletRequest request) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			String yzm = (String) request.getSession().getAttribute("authCode");
			if (BaseUtil.isNotEmpty(yzm)) {
				if (authCode.equals(yzm)) {
					Member user = webMemberService.getMember(member
							.getUserName());
					if (BaseUtil.isEmpty(user)) {
						UserInfo qzoneUserInfo = new UserInfo(accessToken,
								openID);
						UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
						if (userInfoBean.getRet() == 0) {
							String gender = userInfoBean.getGender();
							member.setGender(gender.equals("男") ? 0 : 1);
							member.setRealName(userInfoBean.getNickname());
							int userId = webMemberService.saveMember(member);
							MemberBound bound = new MemberBound();
							bound.setBoundTime(DateUtil.getCurrentTime());
							bound.setChannel(0);
							bound.setOpenId(openID);
							bound.setUserId(userId);
							memberBoundService.saveMemberBound(bound);
							jsonMap.put("result", true);
						} else {
							jsonMap.put("result", false);
							jsonMap.put("meg", "很抱歉，我们没能正确获取到您的信息，原因是： "
									+ userInfoBean.getMsg());
						}
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
		} catch (QQConnectException e) {
			jsonMap.put("result", false);
			jsonMap.put("meg", "系统处理异常，请稍后再试");
			e.printStackTrace();
		}
		return jsonMap;
	}

	@RequestMapping("/bound")
	public ModelAndView bound(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mall/connect/qqBound");
		;
		return mav;
	}
}
