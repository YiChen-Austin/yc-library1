package com.mall.web.mobile.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.constant.CommonConstant;
import com.mall.common.exception.FrameworkException;
import com.mall.common.geo.coordinate.BaiduGeoUtil;
import com.mall.common.geo.coordinate.CoordinateVo;
import com.mall.common.geo.coordinate.Gps2BaiduUtil;
import com.mall.common.geo.coordinate.JsonParseUtil;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.common.util.MD5Util;
import com.mall.common.util.QRcodeUtil;
import com.mall.common.util.UrlUtil;
import com.mall.common.util.VerificationUtil;
import com.mall.web.admin.common.utils.SessionUtils;
import com.mall.web.common.util.IpSearchUtil;
import com.mall.web.mall.common.utils.HttpSendSms;
import com.mall.web.mall.common.utils.MallEnum;
import com.mall.web.mall.common.utils.MallEnum.ServiceCodeType;
import com.mall.web.mall.domain.MemberBound;
import com.mall.web.mall.domain.Member;
import com.mall.web.mall.member.service.AutoLoginService;
import com.mall.web.mall.member.service.WebMemberService;
import com.mall.web.mall.member.vo.WebMemberVo;
import com.mall.web.mall.third.qq.service.MemberBoundService;
import com.mall.web.mobile.common.service.MobCommonService;
import com.mall.web.mobile.common.util.UserUtil;
import com.mall.web.mobile.common.vo.MobSysDateBean;
import com.mall.web.mobile.member.service.MobMemberService;
import com.mall.web.mobile.member.vo.MobMemberVo;
import com.mall.web.mobile.third.util.WechatUserInfo;
import com.qq.connect.javabeans.QQuserInfoBean;
import com.sun.org.apache.xml.internal.security.utils.Base64;

@Controller
@RequestMapping("/mobile/common/*")
public class MobCommonController {
	private static Logger logger = Logger.getLogger(MobCommonController.class);
	@Resource(name = "mobCommonService")
	private MobCommonService mobCommonService;
	@Resource(name = "mobMemberService")
	private MobMemberService mobMemberService;
	// 绑定
	@Resource(name = "memberBoundService")
	private MemberBoundService memberBoundService;
	// 用户信息
	@Resource(name = "webMemberService")
	private WebMemberService webMemberService;
	// 通过token判断用户登录信息
	@Resource(name = "autoLoginService")
	private AutoLoginService autoLoginService;

	@RequestMapping("forgetPwd")
	public ModelAndView forgetPwd() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/common/forgetPwd");
		return mav;
	}

	@RequestMapping("login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/common/login");
		return mav;
	}

	@RequestMapping("article")
	public ModelAndView article() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/common/article");
		return mav;
	}

	@RequestMapping("articleOne")
	public ModelAndView articleOne() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/common/articleOne");
		return mav;
	}

	@RequestMapping("registerNew")
	public ModelAndView registerNew() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/common/registerNew");
		return mav;
	}

	@RequestMapping("topUp")
	public ModelAndView topUp(HttpServletRequest request) {
		if (UserUtil.isUserLogin(request) == false)
			return UserUtil.toUserLogin(request, UserUtil.Login_URL_PRF
					+ "/common/topUp");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/common/topUp");
		return mav;
	}

	@RequestMapping("loginBindSwitch")
	public ModelAndView loginBindSwitch(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String openID = (String) request.getSession().getAttribute("openID");
		if (BaseUtil.isEmpty(openID)) {
			mav.setViewName("mobile/error/oauth");
			return mav;
		}
		mav.addObject("openID", openID);
		mav.setViewName("mobile/common/loginBindSwitch");
		return mav;
	}

	@RequestMapping("loginBind")
	public ModelAndView loginBind(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String openID = (String) request.getSession().getAttribute("openID");
		if (BaseUtil.isEmpty(openID)) {
			mav.setViewName("mobile/error/oauth");
			return mav;
		}
		mav.addObject("openID", openID);
		mav.setViewName("mobile/common/loginBind");
		return mav;
	}

	@RequestMapping("loginBreg")
	public ModelAndView loginBreg(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String openID = (String) request.getSession().getAttribute("openID");
		if (BaseUtil.isEmpty(openID)) {
			mav.setViewName("mobile/error/oauth");
			return mav;
		}
		mav.addObject("openID", openID);
		mav.setViewName("mobile/common/loginBreg");
		return mav;
	}

	/**
	 * 获取用户
	 * 
	 * 判断用户信息是否是存于内存，存在则返回
	 * 
	 * 未加入内容，通过token获取用户登录信息，并加入内存
	 * 
	 * @return
	 */
	@RequestMapping("getLoginUser")
	@ResponseBody
	public MobMemberVo getLoginUser(HttpServletRequest request) {
		MobMemberVo member = UserUtil.getInstatnce().getUser(request);
		String token = request.getParameter("token");
		try {
			// 用户信息不在，通过token获取用户信息
			if (member == null && token != null && token.length() > 0) {
				member = autoLoginService.getUserByToken(token);
				if (member != null) {
					member.setPassword("");
					UserUtil.getInstatnce().setUser(token, member);
				}
			}
		} catch (Exception e) {
			logger.warn(e);
		}
		return member;
	}

	/**
	 * 获取
	 * 
	 * @param sysDate
	 * @return
	 */
	@RequestMapping("getSysDate")
	@ResponseBody
	public MobSysDateBean getSysDate(MobSysDateBean sysDateBean) {
		sysDateBean = (sysDateBean == null) ? new MobSysDateBean()
				: sysDateBean;
		sysDateBean
				.setServiceDate(DateUtil.dateToString(DateUtil.getGMTDate()));
		return sysDateBean;
	}

	/**
	 * 通过经纬度获取具体地址
	 * 
	 * @param latitude
	 *            纬度
	 * @param longitude
	 *            经度
	 * @return
	 */
	@RequestMapping("findLocByLatLng")
	@ResponseBody
	public Map<String, Object> findLocByLatLng(String latitude, String longitude) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (!BaseUtil.isEmpty(latitude) && !BaseUtil.isEmpty(longitude)) {
			try {
				String location = BaiduGeoUtil.lnglat2addr(longitude, latitude);
				result.put("serverCode", 1);
				result.put("row", location);
			} catch (Exception e) {
				e.printStackTrace();
				logger.warn(e);
				result.put("serverCode", 0);
				return result;
			}
		}
		return result;
	}

	/**
	 * 通过地理坐标获取城市
	 * 
	 * @param latitude
	 *            纬度
	 * @param longitude
	 *            经度
	 * @return
	 */
	@RequestMapping("findCityBylatLng")
	@ResponseBody
	public Map<String, Object> findCityBylatLng(String latitude,
			String longitude) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 暂时写死，默认重庆
		Map<String, Object> city = new HashMap<String, Object>();
		city.put("cityId", 13308);
		city.put("cityName", "重庆");
		result.put("serverCode", 1);
		result.put("row", city);
		return result;
	}


	/**
	 * 登录获取验证码
	 * 
	 * @param session
	 * @param response
	 */
	@RequestMapping("verification")
	public void verification(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getSession().setAttribute(
					SessionUtils.SESSION_VALIDATECODE,
					VerificationUtil.getInstance().getCheckCodeImage(4,
							response.getOutputStream()));
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
		}

	}

	/**
	 * 发送短信-通用
	 * 
	 * @param phone
	 *            手机号码
	 * @param type
	 *            发送文本类型，默认值可输入0
	 * @return serverCode:0表示失败，1表示成功，2表示输入的手机号码与绑定的手机号码不匹配,3表示用户没有绑定手机号码
	 */
	@RequestMapping("smsSend")
	@ResponseBody
	public Map<String, Object> SmsSend(HttpServletRequest request,
			String phone, String code, int type) {
		Map<String, Object> result = new HashMap<String, Object>();
		// # 错误码: 图片验证码错误
		if (BaseUtil.isEmpty(code)
				|| !code.equals(request.getSession().getAttribute(
						SessionUtils.SESSION_VALIDATECODE))) {
			result.put("serverCode", "8");
			request.getSession().removeAttribute(
					SessionUtils.SESSION_VALIDATECODE);
			return result;
		}
		request.getSession().removeAttribute(SessionUtils.SESSION_VALIDATECODE);
		int serverCode = 0;
		try {
			String validCode = VerificationUtil.getInstance()
					.getCheckCodeImage("0123456789", 6);// 验证码
			request.getSession().setAttribute("phoneSmsCode", validCode);
			if (!BaseUtil.isEmpty(phone) && !BaseUtil.isEmpty(type)) {
				MobMemberVo vo = UserUtil.getInstatnce().getUser(request);
				if (vo != null) {// 登录情况下
					if (!BaseUtil.isEmpty(vo.getPhoneMob())) {
						if (vo.getPhoneMob().equals(phone.trim())) {
							serverCode = HttpSendSms.postSend4b(phone,
									validCode) ? 1 : 0;
						} else {
							serverCode = 2;
						}
					} else {
						serverCode = 3;
					}
				} else {
					// 未登录情况下，并且用户已存在
					if (request.getSession().getAttribute("userRecord") != null) {
						vo = (MobMemberVo) request.getSession().getAttribute(
								"userRecord");
						if (!BaseUtil.isEmpty(vo.getPhoneMob())) {
							if (phone.trim().equals(vo.getPhoneMob())) {
								serverCode = HttpSendSms.postSend4b(phone,
										validCode) ? 1 : 0;
								serverCode = 1;
							} else {
								serverCode = 2;
							}
						} else {
							serverCode = 3;
						}
					}
					// 用户未登录，用于注册或者手机登录
					else {
						boolean isBind = mobCommonService
								.checkPhoneIsBind(phone);
						// 手机注册
						if (type == 2) {
							// 该号码是否已绑定
							if (isBind) {
								serverCode = 9;
							}
							// 号码未被绑定,发送验证码
							else {
								serverCode = HttpSendSms.postSend4b(phone,
										validCode) ? 1 : 0;
								// 发送成功
								if (serverCode == 1) {
									request.getSession().setAttribute(
											"registerPhone", phone);
								}
							}
						}
						// 手机登录
						else if (type == 3) {
							if (!isBind) {
								serverCode = 9;
							} else {
								serverCode = HttpSendSms.postSend4b(phone,
										validCode) ? 1 : 0;
								// 发送成功
								if (serverCode == 1) {
									request.getSession().setAttribute(
											"loginPhone", phone);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
		}
		result.put("serverCode", serverCode);
		return result;
	}

	/**
	 * 发送短信-通用
	 * 
	 * @param phone
	 *            手机号码
	 * @param type
	 *            发送文本类型，默认值可输入0
	 * @return serverCode:0表示失败，1表示成功，2表示输入的手机号码与绑定的手机号码不匹配,3表示用户没有绑定手机号码
	 */
	@RequestMapping("smsSend4m")
	@ResponseBody
	public Map<String, Object> smsSend4m(HttpServletRequest request,
			String phone, String code, String appId) {
		Map<String, Object> result = new HashMap<String, Object>();
		String appValue=UserUtil.getInstatnce().getUserCode(appId);
		// # 错误码: 图片验证码错误
		if (BaseUtil.isEmpty(appId) || appId.length() % 2 != 0
				|| appId.length() < 10
				|| !code.equals(appValue)) {
			result.put(MallEnum.ServiceCodeType.ServiceCode,
					MallEnum.ServiceCodeType.DataErr.getIndex());
			return result;
		}
		try {
			String validCode = VerificationUtil.getInstance()
					.getCheckCodeImage("0123456789", 6);// 验证码
			UserUtil.getInstatnce().setUserCode(phone, validCode);
			HttpSendSms.postSend(phone, validCode);
			// if (HttpSendSms.postSend4b(phone, 2, validCode)) {
			result.put(MallEnum.ServiceCodeType.ServiceCode,
					MallEnum.ServiceCodeType.Success.getIndex());
			// } else {
			// result.put(MallEnum.ServiceCodeType.ServiceCode,
			// MallEnum.ServiceCodeType.OtherErr.getIndex());
			// }
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
			result.put(MallEnum.ServiceCodeType.ServiceCode,
					MallEnum.ServiceCodeType.SysErr.getIndex());
		}
		return result;
	}

	/**
	 * 图片验证码 app专属
	 * 
	 * @param session
	 * @param response
	 */
	@RequestMapping("verification4m")
	public void verification4m(HttpServletRequest request,
			HttpServletResponse response, String appId) {
		if (BaseUtil.isEmpty(appId) || appId.length() % 2 != 0
				|| appId.length() < 10)
			return;
		byte[] b = new byte[appId.length() / 2];
		for (int i = 0; i < appId.length() / 2; i++) {
			b[i] = Integer.valueOf(appId.substring(i * 2, i * 2 + 2), 16)
					.byteValue();
		}
		String dAppId = null;
		try {
			byte[] d = Base64.decode(b);
			dAppId = new String(d);
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
		}
		if (dAppId == null || !dAppId.startsWith("qqgo_")
				|| !dAppId.endsWith("_ogqq"))
			return;
		try {
			String verification = VerificationUtil.getInstance()
					.getCheckCodeImage(4, response.getOutputStream());// 图片验证码
			UserUtil.getInstatnce().setUserCode(appId, verification);
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
		}

	}

	/**
	 * 发送短信-向登录用户
	 * 
	 * @return serverCode:0表示失败，1表示成功，2表示输入的手机号码与绑定的手机号码不匹配,3表示用户没有绑定手机号码
	 */
	@RequestMapping("smsSend4l")
	@ResponseBody
	public Map<String, Object> smsSend4l(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		MobMemberVo vo = UserUtil.getInstatnce().getUser(request);
		if (vo == null) {
			result.put(MallEnum.ServiceCodeType.ServiceCode,
					MallEnum.ServiceCodeType.UnLogin.getIndex());
			return result;
		}

		try {
			String validCode = VerificationUtil.getInstance()
					.getCheckCodeImage("0123456789", 6);// 验证码
			String loginUserKey = vo.getUserId() + "_" + vo.getUserName();
			UserUtil.getInstatnce().setUserCode(loginUserKey, validCode);
			HttpSendSms.postSend(vo.getPhoneMob(), validCode);
			result.put(MallEnum.ServiceCodeType.ServiceCode,
					MallEnum.ServiceCodeType.Success.getIndex());
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
			result.put(MallEnum.ServiceCodeType.ServiceCode,
					MallEnum.ServiceCodeType.SysErr.getIndex());
		}
		return result;
	}

	/**
	 * 发送短信--用于手机修改和绑定
	 * 
	 * @param phone
	 *            手机号码
	 * @param type
	 *            0: 绑定手机, 1: 修改手机绑定
	 * @param code
	 *            图片验证码
	 * @return serverCode 0: 失败, 1: 成功, 2: 图片验证码不正确, 3: 未验证旧手机（仅当 type=1 时可能发生）
	 */
	@RequestMapping("mobSmsSend")
	@ResponseBody
	public Map<String, Object> mobSmsSend(HttpServletRequest request,
			String phone, int type, String code) {
		Map<String, Object> result = new HashMap<String, Object>();
		// # 错误码: 图片验证码错误
		if (BaseUtil.isEmpty(code)
				|| !code.equals(request.getSession().getAttribute(
						SessionUtils.SESSION_VALIDATECODE))) {
			result.put(MallEnum.ServiceCodeType.ServiceCode,
					MallEnum.ServiceCodeType.DataErr.getIndex());
			request.getSession().removeAttribute(
					SessionUtils.SESSION_VALIDATECODE);
			return result;
		}
		int serverCode = 0;
		try {
			MobMemberVo vo = UserUtil.getInstatnce().getUser(request);
			if (!BaseUtil.isEmpty(phone) && type >= 0 && vo != null) {
				String validCode = VerificationUtil.getInstance()
						.getCheckCodeImage("0123456789", 6); // 验证码
				request.getSession().setAttribute("phoneSmsCode", validCode);
				switch (type) {
				case 1: {
					// # 未验证旧手机
					if (request.getSession().getAttribute(
							"isBoundPhoneValidated") == null
							|| !request.getSession()
									.getAttribute("isBoundPhoneValidated")
									.equals(1)) {
						serverCode = 3;
					}
					// # 已验证旧手机
					else {
						serverCode = HttpSendSms.postSend4b(phone, validCode) ? 1
								: 0;
					}
					break;
				}
				default: {
					serverCode = HttpSendSms.postSend4b(phone, validCode) ? 1
							: 0;
					break;
				}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
		}
		result.put("serverCode", serverCode);
		return result;
	}

	

	/**
	 * 绑定注册用户(还需要修改，保证定时清除session，释放内存，而且防止恶意攻击，进行判断处理)
	 * 
	 * @param session
	 *            当前注册session
	 * @param phone
	 *            注册手机号码
	 * @param code
	 *            手机验证码
	 * @param password
	 *            密码
	 * @return
	 */
	@RequestMapping("bindReg")
	@ResponseBody
	public Map<String, Object> bindRegister(HttpServletRequest request,
			String phone, String code, String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		String openID = (String) request.getSession().getAttribute("openID");
		Object openUser = request.getSession().getAttribute("openUserInfoBean");
		if (BaseUtil.isEmpty(openID)) {
			result.put("serverCode", 8);// 过期
			return result;
		}
		// 手机号码和验证码均与session中匹配
		if (request.getSession().getAttribute("registerPhone") != null
				&& request.getSession().getAttribute("registerPhone")
						.equals(phone)
				&& request.getSession().getAttribute("phoneSmsCode") != null
				&& request.getSession().getAttribute("phoneSmsCode")
						.equals(code)) {
			try {
				String nickname = null;
				int channel = 0;
				// 微信
				if (openUser instanceof WechatUserInfo) {
					channel = 1;
					nickname = ((WechatUserInfo) openUser).getNickname();
				} // qq
				else if (openUser instanceof QQuserInfoBean) {
					channel = 0;
					nickname = ((QQuserInfoBean) openUser).getNickname();
				}
				// 注册用户
				MobMemberVo vo = mobMemberService.register(phone, password,
						nickname);
				// 新用户注册开通支付账号
				webMemberService.openNewAccount(vo.getUserId(), phone);

				
				// 注册成功，注意清除session内容，节省内存
				if (vo != null) {
					UserUtil.getInstatnce().setUser(request, vo);
					request.getSession().removeAttribute("registerPhone");
					request.getSession().removeAttribute("phoneSmsCode");
					result.put("serverCode", 1);
					result.put("row", vo);

					MemberBound bound = new MemberBound();
					bound.setBoundTime(DateUtil.getCurrentTime());
					bound.setChannel(channel);
					bound.setOpenId(openID);
					bound.setUserId(vo.getUserId());
					memberBoundService.saveMemberBound(bound);

					request.getSession().removeAttribute("openID");
					request.getSession().removeAttribute("openUserInfoBean");
					request.getSession().removeAttribute("userType");
				} else {
					result.put("serverCode", "0");
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.warn(e);
				result.put("serverCode", 0);
				return result;
			}
		}
		// 手机号码与验证码不匹配
		else {
			result.put("serverCode", 9);
		}
		return result;
	}

	/**
	 * 绑定注册用户(还需要修改，保证定时清除session，释放内存，而且防止恶意攻击，进行判断处理)
	 * 
	 * @param session
	 *            当前注册session
	 * @param phone
	 *            注册手机号码
	 * @param code
	 *            手机验证码
	 * @param password
	 *            密码
	 * @return
	 */
	@RequestMapping("bindReg4m")
	@ResponseBody
	public Map<String, Object> bindReg4m(String openId, String phone,
			String code, String password, String nickname, int channel) {
		Map<String, Object> result = new HashMap<String, Object>();
		String validCode = UserUtil.getInstatnce().getUserCode(phone);
		// 手机号码和验证码均与session中匹配
		if (validCode != null && code != null && validCode.equals(code)) {
			try {
				// 注册用户
				MobMemberVo vo = mobMemberService.register(phone, password,
						nickname);
				// 新用户注册开通支付账号
				webMemberService.openNewAccount(vo.getUserId(), phone);

				
				// 注册成功，注意清除session内容，节省内存
				if (vo != null) {
					UserUtil.getInstatnce().setUser(openId, vo);
					result.put(MallEnum.ServiceCodeType.ServiceCode,
							MallEnum.ServiceCodeType.Success.getIndex());
					result.put("row", vo);

					MemberBound bound = new MemberBound();
					bound.setBoundTime(DateUtil.getCurrentTime());
					bound.setChannel(channel);
					bound.setOpenId(openId);
					bound.setUserId(vo.getUserId());
					memberBoundService.saveMemberBound(bound);
				} else {
					result.put(MallEnum.ServiceCodeType.ServiceCode,
							MallEnum.ServiceCodeType.OtherErr.getIndex());
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.warn(e);
				result.put(MallEnum.ServiceCodeType.ServiceCode,
						MallEnum.ServiceCodeType.OtherErr.getIndex());
			}
		}
		// 手机号码与验证码不匹配
		else {
			result.put(MallEnum.ServiceCodeType.ServiceCode,
					MallEnum.ServiceCodeType.OtherErr.getIndex());
		}
		return result;
	}

	/**
	 * @Description(功能描述) : 第三方登录绑定
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年10月17日 上午11:59:57
	 * @param openID
	 *            : openid
	 * @param accessToken
	 *            : token
	 * @param member
	 *            : 用户参数
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/boundUser")
	public Object boundUser(HttpServletRequest request, WebMemberVo bean) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String openID = (String) request.getSession().getAttribute("openID");
		if (BaseUtil.isEmpty(openID)) {
			jsonMap.put("result", "6");
			jsonMap.put("meg", "绑定过期");
			return jsonMap;
		}
		try {
			Member member = webMemberService.getMember(bean.getUserName());
			if (BaseUtil.isNotEmpty(member)
					&& MD5Util.MD5(bean.getPassword()).equalsIgnoreCase(
							member.getPassword())) {
				MemberBound bound = new MemberBound();
				bound.setBoundTime(DateUtil.getCurrentTime());
				bound.setChannel(0);
				bound.setOpenId(openID);
				bound.setUserId(member.getUserId());
				memberBoundService.saveMemberBound(bound);
				MobMemberVo user = MobMemberVo.bean2Vo(member);
				user.setPassword("");
				UserUtil.getInstatnce().setUser(request, user);
				jsonMap.put("result", "0");
				jsonMap.put(MallEnum.ServiceCodeType.ServiceCode,
						MallEnum.ServiceCodeType.Success.getIndex());
				jsonMap.put("vo", user);

				request.getSession().removeAttribute("openID");
				request.getSession().removeAttribute("openUserInfoBean");
				request.getSession().removeAttribute("userType");
			} else {
				jsonMap.put("result", "8");
				jsonMap.put(MallEnum.ServiceCodeType.ServiceCode,
						MallEnum.ServiceCodeType.DataErr.getIndex());
				jsonMap.put("meg", "手机号与密码不匹配，请重新输入");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("result", "7");
			jsonMap.put(MallEnum.ServiceCodeType.ServiceCode,
					MallEnum.ServiceCodeType.SysErr.getIndex());
			jsonMap.put("meg", "系统忙，稍后再试.");
		}
		return jsonMap;
	}

	/**
	 * @Description(功能描述) : 第三方登录绑定
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年10月17日 上午11:59:57
	 * @param openID
	 *            : openid
	 * @param accessToken
	 *            : token
	 * @param member
	 *            : 用户参数
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/boundUser4m")
	public Object boundUser4m(HttpServletRequest request, WebMemberVo bean) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String openID = (String) request.getParameter("openId");
		if (BaseUtil.isEmpty(openID)) {
			jsonMap.put("result", "6");
			jsonMap.put("meg", "绑定过期");
			return jsonMap;
		}
		try {
			Member member = webMemberService.getMember(bean.getUserName());
			if (BaseUtil.isNotEmpty(member)
					&& MD5Util.MD5(bean.getPassword()).equalsIgnoreCase(
							member.getPassword())) {
				MemberBound bound = new MemberBound();
				bound.setBoundTime(DateUtil.getCurrentTime());
				bound.setChannel(0);
				bound.setOpenId(openID);
				bound.setUserId(member.getUserId());
				memberBoundService.saveMemberBound(bound);
				MobMemberVo user = MobMemberVo.bean2Vo(member);
				user.setPassword("");
				UserUtil.getInstatnce().setUser(request, user);
				jsonMap.put("result", "0");
				jsonMap.put(MallEnum.ServiceCodeType.ServiceCode,
						MallEnum.ServiceCodeType.Success.getIndex());
				jsonMap.put("vo", user);

				request.getSession().removeAttribute("openID");
				request.getSession().removeAttribute("openUserInfoBean");
				request.getSession().removeAttribute("userType");
			} else {
				jsonMap.put("result", "8");
				jsonMap.put(MallEnum.ServiceCodeType.ServiceCode,
						MallEnum.ServiceCodeType.DataErr.getIndex());
				jsonMap.put("meg", "手机号与密码不匹配，请重新输入");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("result", "7");
			jsonMap.put(MallEnum.ServiceCodeType.ServiceCode,
					MallEnum.ServiceCodeType.SysErr.getIndex());
			jsonMap.put("meg", "系统忙，稍后再试.");
		}
		return jsonMap;
	}

	/**
	 * 通过城市名加搜索关键字从百度获取地址信息
	 * 
	 * @param keyword
	 *            搜索关键字
	 * @param cityName
	 *            当前城市名
	 * @return
	 */
	@RequestMapping("findLocationFromBaidu")
	@ResponseBody
	public Map<String, Object> findLocationFromBaidu(String keyword,
			String cityName) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (!BaseUtil.isEmpty(keyword) && !BaseUtil.isEmpty(cityName)) {
				List<Map<String, Object>> list = BaiduGeoUtil.poiQuery(keyword,
						cityName);
				result.put("rows", list);
				result.put("serverCode", 1);
			} else {
				result.put("serverCode", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
			result.put("serverCode", 0);
			return result;
		}

		return result;
	}

	/**
	 * 把GPS坐标转换才百度坐标
	 * 
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	@RequestMapping("getBaiduCoordFromGps")
	@ResponseBody
	public Map<String, Object> getBaiduCoordFromGps(String latitude,
			String longitude) {
		Map<String, Object> result = new HashMap<String, Object>();
		double lat = 0, lng = 0;
		JsonParseUtil<CoordinateVo> jsonUtil = new JsonParseUtil<CoordinateVo>();
		String json = Gps2BaiduUtil.gps2baiduGet(new String[] { longitude + ","
				+ latitude });
		List<CoordinateVo> list = jsonUtil.getList(json, CoordinateVo.class);
		for (CoordinateVo vo : list) {
			lng = vo.getX();
			lat = vo.getY();
		}
		result.put("serverCode", 1);
		result.put(
				"row",
				JSONObject.fromObject("{lat:" + String.valueOf(lat) + ",lng:"
						+ String.valueOf(lng) + "}"));
		return result;
	}

	/**
	 * 通用二维码
	 * 
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	@RequestMapping("myQrcode")
	public String findMySelfQr(String code, HttpServletResponse response) {
		try {
			String url = CommonConstant.MOBILE_URL
					+ "/qruser/"
					+ (code == null ? "" : new String(Base64.encode(code
							.getBytes())));
			QRcodeUtil.encodeMyCode(url, response.getOutputStream());
		} catch (Exception e) {
			logger.warn(e);
			e.printStackTrace();
		}
		return null;
	}
}
