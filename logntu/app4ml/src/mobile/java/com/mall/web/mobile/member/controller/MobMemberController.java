package com.mall.web.mobile.member.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.constant.CommonConstant;
import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.common.util.MD5Util;
import com.mall.common.util.QRcodeUtil;
import com.mall.common.util.ShopIdUtil;
import com.mall.common.util.VerificationUtil;
import com.mall.common.vo.PageBean;
import com.mall.web.mall.common.tag.MallJstlFunction;
import com.mall.web.mall.common.utils.MallEnum.ServiceCodeType;
import com.mall.web.mall.common.utils.NarrowImage;
import com.mall.web.mall.domain.Member;
import com.mall.web.mall.domain.MemberDeposit;
import com.mall.web.mall.member.service.AutoLoginService;
import com.mall.web.mall.member.service.WebMemberDepositService;
import com.mall.web.mall.member.service.WebMemberService;
import com.mall.web.mall.member.vo.MemberPayRecordVo;
import com.mall.web.mall.member.vo.WebMemberDepositVo;
import com.mall.web.mall.third.wechat.config.WechatConfig;
import com.mall.web.mobile.common.util.UserUtil;
import com.mall.web.mobile.member.service.MobMemberService;
import com.mall.web.mobile.member.vo.MobMemberVo;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import com.tencent.common.Configure;

/**
 * 移动端会员控制层
 * 
 * @author ty
 * 
 */
@Controller
@RequestMapping("/mobile/member/*")
public class MobMemberController {
	private static Logger logger = Logger.getLogger(MobMemberController.class);

	@Resource(name = "webMemberService")
	private WebMemberService webMemberService;

	@Resource(name = "mobMemberService")
	private MobMemberService mobMemberService;

	@Resource(name = "autoLoginService")
	private AutoLoginService autoLoginService;

	private String serverCode;
	private String errorMessage;

	/**
	 * 个人资料界面
	 */
	@RequestMapping("accountInfo")
	public ModelAndView accountInfo(HttpServletRequest request) {
		// 未登录，进入登录界面，通过登录后，进行返回跳转
		if (UserUtil.isUserLogin(request) == false)
			return UserUtil.toUserLogin(request, UserUtil.Login_URL_PRF
					+ "/member/accountInfo");
		ModelAndView mav = new ModelAndView();
		MobMemberVo user = UserUtil.getInstatnce().getUser(request);
		mav.addObject("user", user);
		mav.setViewName("mobile/member/accountInfo");
		return mav;
	}

	@RequestMapping("accountCenter")
	public ModelAndView accountCenter(HttpServletRequest request) {
		// 未登录，进入登录界面，通过登录后，进行返回跳转
		if (UserUtil.isUserLogin(request) == false)
			return UserUtil.toUserLogin(request, UserUtil.Login_URL_PRF
					+ "/member/accountCenter");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/member/accountCenter");
		return mav;
	}

	// 账户信息
	@Resource(name = "webMemberDepositService")
	private WebMemberDepositService webMemberDepositService;

	@RequestMapping("address")
	public ModelAndView address(HttpServletRequest request) {
		// 未登录，进入登录界面，通过登录后，进行返回跳转
		if (UserUtil.isUserLogin(request) == false)
			return UserUtil.toUserLogin(request, UserUtil.Login_URL_PRF
					+ "/member/address");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/member/address");
		return mav;
	}

	@RequestMapping("bindPhone")
	public ModelAndView bindPhone(HttpServletRequest request) {
		// 未登录，进入登录界面，通过登录后，进行返回跳转
		if (UserUtil.isUserLogin(request) == false)
			return UserUtil.toUserLogin(request, UserUtil.Login_URL_PRF
					+ "/member/bindPhone");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/member/bindPhone");
		return mav;
	}

	@RequestMapping("comment")
	public ModelAndView comment(HttpServletRequest request) {
		// 未登录，进入登录界面，通过登录后，进行返回跳转
		if (UserUtil.isUserLogin(request) == false)
			return UserUtil.toUserLogin(request, UserUtil.Login_URL_PRF
					+ "/member/comment");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/member/comment");
		return mav;
	}

	@RequestMapping("loginPwd")
	public ModelAndView loginPwd(HttpServletRequest request) {
		// 未登录，进入登录界面，通过登录后，进行返回跳转
		if (UserUtil.isUserLogin(request) == false)
			return UserUtil.toUserLogin(request, UserUtil.Login_URL_PRF
					+ "/member/loginPwd");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/member/loginPwd");
		return mav;
	}

	@RequestMapping("myfours")
	public ModelAndView myfours(HttpServletRequest request) {
		// 未登录，进入登录界面，通过登录后，进行返回跳转
		if (UserUtil.isUserLogin(request) == false)
			return UserUtil.toUserLogin(request, UserUtil.Login_URL_PRF
					+ "/member/myfours");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/member/myfours");
		return mav;
	}

	@RequestMapping("wallet")
	public ModelAndView wallet(HttpServletRequest request) {
		// 未登录，进入登录界面，通过登录后，进行返回跳转
		if (UserUtil.isUserLogin(request) == false)
			return UserUtil.toUserLogin(request, UserUtil.Login_URL_PRF
					+ "/member/wallet");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/member/wallet");
		return mav;
	}

	@RequestMapping("redPackage")
	public ModelAndView redPackage(HttpServletRequest request) {
		// 未登录，进入登录界面，通过登录后，进行返回跳转
		if (UserUtil.isUserLogin(request) == false)
			return UserUtil.toUserLogin(request, UserUtil.Login_URL_PRF
					+ "/member/redPackage");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/member/redPackage");
		return mav;
	}

	@RequestMapping("coupon")
	public ModelAndView coupon(HttpServletRequest request) {
		// 未登录，进入登录界面，通过登录后，进行返回跳转
		if (UserUtil.isUserLogin(request) == false)
			return UserUtil.toUserLogin(request, UserUtil.Login_URL_PRF
					+ "/member/coupon");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/member/coupon");
		return mav;
	}

	@RequestMapping("g")
	public ModelAndView g(HttpServletRequest request) {
		// 未登录，进入登录界面，通过登录后，进行返回跳转
		if (UserUtil.isUserLogin(request) == false)
			return UserUtil.toUserLogin(request, UserUtil.Login_URL_PRF
					+ "/member/g");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/member/g");
		return mav;
	}

	@RequestMapping("logout")
	@ResponseBody
	public Map<String, Object> logout(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String token = request.getParameter("token");
		// 删除token,从token库中删除
		if (token != null && token.length() > 0) {
			autoLoginService.removeAutoLogin(token);
		}
		UserUtil.getInstatnce().removeUser(request);
		result.put("serverCode", 1);
		return result;
	}

	@RequestMapping("exit")
	public ModelAndView exit(HttpServletRequest request) {
		UserUtil.getInstatnce().removeUser(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/common/login");
		return mav;
	}

	/**
	 * @Description(功能描述) : 手机app登录
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年10月31日 上午10:58:07
	 * @param session
	 *            : 会员当前登录session
	 * @param userName
	 *            : 会员名
	 * @param password
	 *            : 密码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("login")
	public Map<String, Object> login(HttpServletRequest request,
			String userName, String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (BaseUtil.isNotEmpty(userName) && BaseUtil.isNotEmpty(password)) {
			Map<String, Object> map = mobMemberService.login(userName,
					MD5Util.MD5(password));
			if (map.get("vo") != null) {
				UserUtil.getInstatnce().setUser(request,
						(MobMemberVo) map.get("vo"));
				result.put(ServiceCodeType.ServiceCode,
						ServiceCodeType.Success.getIndex());
				result.put("row", map.get("vo")); // 用户信息
				result.put("token", map.get("token")); // token信息
			} else if (map.get("result") != null) { // 匹配失败信息,7代表用户名不存在，8代表密码错误
				result.put(ServiceCodeType.ServiceCode, map.get("result"));
			} else {
				result.put(ServiceCodeType.ServiceCode,
						ServiceCodeType.DataErr.getIndex());
			}
		} else {
			result.put(ServiceCodeType.ServiceCode,
					ServiceCodeType.DataErr.getIndex());
		}
		return result;
	}

	/**
	 * @Description(功能描述) : 手机浏览器登录
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年10月31日 上午10:58:07
	 * @param session
	 *            : 会员当前登录session
	 * @param userName
	 *            : 会员名
	 * @param password
	 *            : 密码
	 * @param code
	 *            : 验证码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("wapLogin")
	public Map<String, Object> wapLogin(HttpServletRequest request,
			String userName, String password, String code) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (!BaseUtil.isEmpty(userName) && !BaseUtil.isEmpty(password)
				&& !BaseUtil.isEmpty(code)) {
			// 校验验证码
			if (code.equals(request.getSession().getAttribute("verification"))) {
				// 再校验用户名和密码
				Map<String, Object> map = mobMemberService.login(userName,
						MD5Util.MD5(password));
				// 登录成功，清理session中内存
				if (map.get("vo") != null) {
					UserUtil.getInstatnce().setUser(request,
							(MobMemberVo) map.get("vo"));
					request.getSession().removeAttribute("verification");
					result.put("serverCode", 1);
					result.put("row", map.get("vo"));
				}
				// 匹配失败信息,7代表用户名不存在，8代表密码错误
				else if (map.get("result") != null) {
					result.put("serverCode", map.get("result"));
				} else {
					result.put("serverCode", 0);
				}
			} else {
				// 9代表验证码错误
				result.put("serverCode", 9);
			}
		}
		return result;
	}

	/**
	 * 手机登录
	 * 
	 * @param session
	 *            当前获取验证码session
	 * @param phone
	 *            手机号码
	 * @param code
	 *            验证码
	 * @return
	 */
	@RequestMapping("phoneLogin")
	@ResponseBody
	public Map<String, Object> phoneLogin(HttpServletRequest request,
			String phone, String code) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 手机号码和验证码匹配
		if (request.getSession().getAttribute("loginPhone") != null
				&& request.getSession().getAttribute("loginPhone")
						.equals(phone)
				&& request.getSession().getAttribute("phoneSmsCode") != null
				&& request.getSession().getAttribute("phoneSmsCode")
						.equals(code)) {
			MobMemberVo vo = mobMemberService.phoneLogin(phone);
			// 登录成功
			if (vo != null) {
				UserUtil.getInstatnce().setUser(request, vo);
				// 清除session信息，节省内存
				request.getSession().removeAttribute("loginPhone");
				request.getSession().removeAttribute("phoneSmsCode");
				result.put("serverCode", "1");
				result.put("row", vo);
			} else {
				result.put("serverCode", 0);
			}
		}
		// 手机号码和验证码不匹配
		else {
			result.put("serverCode", "9");
		}
		return result;
	}

	/**
	 * 根据本地存储信息中用户名判断是否已经登录
	 * 
	 * @param session
	 *            当前session
	 * @param userName
	 *            会员名
	 * @return
	 */
	@RequestMapping("checkIsLogin")
	@ResponseBody
	public Map<String, Object> checkIsLogin(HttpServletRequest request,
			String userName) {
		Map<String, Object> result = new HashMap<String, Object>();
		MobMemberVo vo = UserUtil.getInstatnce().getUser(request);
		if (vo != null) {
			if (vo.getUserName().equals(userName)) {
				result.put("serverCode", 1);
			} else {
				result.put("serverCode", 0);
			}
		} else {
			result.put("serverCode", 1);
		}
		return result;
	}

	/**
	 * 免输入直接登录
	 * 
	 * @param session
	 *            会员登录session
	 * @param userName
	 *            会员名
	 * @param password
	 *            会员密码
	 * @return
	 */
	@RequestMapping("directLogin")
	@ResponseBody
	public Map<String, Object> directLogin(HttpServletRequest request,
			String userName, String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		MobMemberVo vo = UserUtil.getInstatnce().getUser(request);
		// 选取session中的信息
		if (vo != null) {
			result.put("serverCode", 1);
		} else if (!BaseUtil.isEmpty(userName) && !BaseUtil.isEmpty(password)) {
			try {
				Map<String, Object> map = mobMemberService.login(userName,
						password);
				// 登录成功，清理session中内存
				if (map.get("vo") != null) {
					UserUtil.getInstatnce().setUser(request,
							(MobMemberVo) map.get("vo"));
					result.put("serverCode", 1);
				}
				// 匹配失败
				else {
					result.put("serverCode", 0);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.warn(e);
				result.put("serverCode", 0);
			}
		}
		return result;
	}

	/**
	 * 会员退出登录
	 * 
	 * @param session
	 *            会员登录session
	 * @return
	 */

	/**
	 * 会员修改密码
	 * 
	 * @param session
	 *            用户session
	 * @param pwd
	 *            原始密码
	 * @param new_pwd
	 *            新密码
	 * @param valid_pwd
	 *            确认密码
	 * @return
	 */
	@RequestMapping("loginPwdEdit")
	@ResponseBody
	public Map<String, Object> loginPwdEdit(HttpServletRequest request,
			String pwd, String new_pwd, String valid_pwd) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (!BaseUtil.isEmpty(pwd) && !BaseUtil.isEmpty(valid_pwd)
					&& !BaseUtil.isEmpty(new_pwd) && valid_pwd.equals(new_pwd)) {
				MobMemberVo vo = UserUtil.getInstatnce().getUser(request);
				// 会员已登录
				if (vo != null) {
					boolean success = mobMemberService.loginEdit(
							vo.getUserName(), MD5Util.MD5(pwd),
							MD5Util.MD5(new_pwd));
					if (success) {
						result.put("serverCode", 1);
					} else {
						result.put("serverCode", 0);
					}
				} else {
					result.put("serverCode", 0);
				}
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
	 * 修改密码
	 * 
	 * @param phone
	 *            注册手机号码
	 * @param code
	 *            手机验证码
	 * @param password
	 *            密码
	 * @return
	 */
	@RequestMapping("loginPwdEdit4m")
	@ResponseBody
	public Map<String, Object> loginPwdEdit4m(HttpServletRequest request,
			String phone, String code, String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		String vcode = UserUtil.getInstatnce().getUserCode(phone); // c-------------------------
		// 手机号码和验证码均与session中匹配
		if (code != null && vcode != null && code.equals(vcode)) {
			try {
				// 注册用户
				Member user = webMemberService.getMember(phone);
				if (user == null) {
					result.put("serviceCode", 9);
					return result;
				}
				user.setPassword(MD5Util.MD5(password));
				webMemberService.updateMember(user);
				result.put("serviceCode", 0);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				logger.warn(e);
				result.put("serviceCode", 8);
				return result;
			}
		}
		// 手机号码与验证码不匹配
		else {
			result.put("serviceCode", 9);
		}
		return result;
	}

	

	/**
	 * 获取登录会员余额
	 * 
	 * @param session
	 *            会员登录session
	 * @return
	 */
	@RequestMapping("getAvailableBalance")
	@ResponseBody
	public Map<String, Object> getAvailableBalance(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		MobMemberVo vo = UserUtil.getInstatnce().getUser(request);
		if (vo != null) {
			String balance = mobMemberService.getAvailableBalanceByUserId(vo
					.getUserId());
			if (balance != null) {
				result.put("serverCode", 1);
				result.put("row", balance);
			} else {
				result.put("serverCode", 0);
			}
		} else {
			result.put("serverCode", 0);
		}
		return result;
	}

	

	/**
	 * 验证已绑定手机号所有权。（通常在修改绑定手机前进行）
	 * 
	 * @author ckw
	 * @author sol
	 * @param session
	 *            会员session
	 * @param phone
	 *            手机号码
	 * @param validCode
	 *            短信验证码
	 * @return serverCode 0: 失败, 1: 成功, 2: 短信验证码错误
	 */
	@RequestMapping("mobValidatePhone")
	@ResponseBody
	public Map<String, Object> mobValidatePhone(HttpServletRequest request,
			String phone, String validCode) {
		Map<String, Object> result = new HashMap<String, Object>();

		// # 错误码 [0]: 参数错误
		if (BaseUtil.isEmpty(phone) || BaseUtil.isEmpty(validCode)) {
			result.put("serverCode", 0);
			return result;
		}
		// # 错误码 [2]: 短信验证码错误
		if (!validCode
				.equals(request.getSession().getAttribute("phoneSmsCode"))) {
			result.put("serverCode", 2);
			return result;
		}

		// # 成功 [1]
		result.put("serverCode", 1);
		request.getSession().setAttribute("isBoundPhoneValidated", 1); // `已验证旧手机号`标识置入session，供其他步骤读写
		request.getSession().removeAttribute("phoneSmsCode");
		return result;
	}

	/**
	 * 绑定手机号码
	 * 
	 * @author ckw
	 * @author sol
	 * @param session
	 *            会员session
	 * @param phone
	 *            手机号码
	 * @param validCode
	 *            短信验证码
	 * @return serverCode 0: 失败, 1: 成功, 2: 短信验证码错误, 3: 错误，已绑定手机号码
	 */
	@RequestMapping("mobBindPhone")
	@ResponseBody
	public Map<String, Object> mobBindPhone(HttpServletRequest request,
			String phone, String validCode) {
		Map<String, Object> result = new HashMap<String, Object>();
		MobMemberVo member = UserUtil.getInstatnce().getUser(request);

		// # 错误码 [0]: 参数错误
		if (member == null || BaseUtil.isEmpty(phone)
				|| BaseUtil.isEmpty(validCode)) {
			result.put("serverCode", 0);
			return result;
		}
		// # 错误码 [3]: 已绑定手机号码
		// # 注: 在已手机号码绑定的情况下，出于安全考虑，不允许在未经验证旧手机的情形下绑定其他手机号码。
		// # 这种情况应该走 mobValidatePhone + mobRebindPhone 流程。
		// #
		if (!BaseUtil.isEmpty(member.getPhoneMob())) {
			result.put("serverCode", 3);
			return result;
		}
		// # 错误码 [2]: 短信验证码错误
		if (!validCode
				.equals(request.getSession().getAttribute("phoneSmsCode"))) {
			result.put("serverCode", 2);
			return result;
		}

		// # 成功 [1]
		mobMemberService.updateMobile(member.getUserId(), phone);
		result.put("serverCode", 1);
		request.getSession().removeAttribute("phoneSmsCode"); // 短信验证码用毕销毁
		return result;
	}

	/**
	 * 更换绑定手机号码
	 * 
	 * @author sol
	 * @param session
	 *            会员session
	 * @param phone
	 *            新手机号
	 * @param validCode
	 *            短信验证码
	 * @return serverCode 0: 失败，1: 成功，2: 短信验证码错误，3: 未验证旧手机
	 */
	@RequestMapping("mobRebindPhone")
	@ResponseBody
	public Map<String, Object> mobRebindPhone(HttpServletRequest request,
			String phone, String validCode) {
		Map<String, Object> result = new HashMap<String, Object>();
		MobMemberVo member = UserUtil.getInstatnce().getUser(request);

		// # 错误码 [0]: 参数错误
		if (member == null || BaseUtil.isEmpty(phone)
				|| BaseUtil.isEmpty(validCode)) {
			result.put("serverCode", 0);
			return result;
		}
		// # 错误码 [3]: 未验证旧手机
		if (request.getSession().getAttribute("isBoundPhoneValidated") == null
				|| !request.getSession().getAttribute("isBoundPhoneValidated")
						.equals(1)) {
			result.put("serverCode", 3);
			return result;
		}
		// # 错误码 [2]: 短信验证码错误
		if (!validCode
				.equals(request.getSession().getAttribute("phoneSmsCode"))) {
			result.put("serverCode", 2);
			return result;
		}

		// # 成功 [1]
		mobMemberService.updateMobile(member.getUserId(), phone);
		result.put("serverCode", 1);
		request.getSession().removeAttribute("phoneSmsCode");
		request.getSession().removeAttribute("isBoundPhoneValidated");
		return result;
	}

	/**
	 * 判断用户是否存在
	 * 
	 * @param userName
	 *            用户名
	 * @param validCode
	 *            图片验证码
	 * @return serverCode:0表示失败，1表示成功，2表示图片验证码不正确，3表示用户不存在
	 */
	@RequestMapping("mobUserExist")
	@ResponseBody
	public Map<String, Object> MobUserExist(HttpServletRequest request,
			String userName, String validCode) {
		Map<String, Object> result = new HashMap<String, Object>();
		int serverCode = 0;
		if (!BaseUtil.isEmpty(userName) && !BaseUtil.isEmpty(validCode)) {
			if (validCode.equals(request.getSession().getAttribute(
					"verification"))) {
				MobMemberVo vo = mobMemberService.findcheckMember(userName);
				if (vo != null) {
					request.getSession().setAttribute("userRecord", vo);// 用户信息
					serverCode = 1;
				} else {
					serverCode = 3;
				}
				request.getSession().removeAttribute("verification");
			} else {
				serverCode = 2;
			}
		}
		result.put("serverCode", serverCode);
		return result;
	}

	/**
	 * 重新输入用户密码
	 * 
	 * @author ckw
	 * @author sol
	 * @param pwd
	 *            用户密码
	 * @param pwdValid
	 *            用户第二次输入的密码
	 * @return serverCode:0表示失败，1表示成功，2表示两次输入的密码不匹配，3表示用户不存在
	 */
	@RequestMapping("resetUserPwd")
	@ResponseBody
	public Map<String, Object> ResetUserPwd(HttpServletRequest request,
			String pwd, String pwdValid) {
		Map<String, Object> result = new HashMap<String, Object>();
		int serverCode = 0;
		if (!BaseUtil.isEmpty(pwd) && !BaseUtil.isEmpty(pwdValid)) {
			if (pwd.equals(pwdValid)) {
				if (request.getSession().getAttribute("userRecord") != null
						&& request.getSession().getAttribute(
								"isBoundPhoneValidated") != null
						&& request.getSession()
								.getAttribute("isBoundPhoneValidated")
								.equals(1)) {
					MobMemberVo vo = (MobMemberVo) request.getSession()
							.getAttribute("userRecord");
					serverCode = mobMemberService.loginEdit(vo.getUserName(),
							vo.getPassword(), MD5Util.MD5(pwd)) ? 1 : 0;
					request.getSession().removeAttribute("userRecord");
					request.getSession().removeAttribute(
							"isBoundPhoneValidated");
				}
			} else {
				serverCode = 2;
			}
		}
		result.put("serverCode", serverCode);
		return result;
	}

	public String getServerCode() {
		return serverCode;
	}

	public void setServerCode(String serverCode) {
		this.serverCode = serverCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	/**
	 * 获取用户支付账户变量并追加到视图。
	 * 
	 * @param mav
	 *            目标视图
	 * @throws FrameworkException
	 */
	@RequestMapping("myBalanceInfo")
	@ResponseBody
	public Map<String, Object> myBalanceInfo(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			MobMemberVo vo = UserUtil.getInstatnce().getUser(request); // 获取当前登陆的用户
			if (BaseUtil.isEmpty(vo)) { // 判断用户是否为空
				result.put("serviceCode", "9");// 未登陆
				return result; // 如果为空，就直接return，就返回了、！！
			}
			WebMemberDepositVo memberDeposit = webMemberService
					.getMemberDeposit(vo.getUserId()); // 传人用户ID，获得账户信息

			result.put("memberDeposit", memberDeposit);
			List<MemberPayRecordVo> list = webMemberService
					.showMemberPayRecord(); // 资金变动信息, 手机网页上，不知道现在是否需要
			result.put("MemberPayRecord", list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
			result.put("meg", "获取用户信息失败");
			result.put("serviceCode", "8");
		}
		return result;
	}

	/**
	 * 获取用户支付账户变量并追加到视图。
	 * 
	 * @param mav
	 *            目标视图
	 * @throws FrameworkException
	 */
	@RequestMapping("myBalance")
	private ModelAndView showMemberDeposit(HttpServletRequest request) {
		// 未登录，进入登录界面，通过登录后，进行返回跳转
		if (UserUtil.isUserLogin(request) == false)
			return UserUtil.toUserLogin(request, UserUtil.Login_URL_PRF
					+ "/member/myBalance");
		ModelAndView mav = new ModelAndView();
		Map<String, Object> result = myBalanceInfo(request);
		mav.addAllObjects(result);
		mav.setViewName("mobile/member/wallet");
		return mav;
	}

	/**
	 * 
	 * 买家红包 没弄完
	 * 
	 * */
	@ResponseBody
	@RequestMapping("/redPack")
	public Object userId(HttpServletRequest request, int userId) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		try {
			MobMemberVo vo = UserUtil.getInstatnce().getUser(request); // 新的会员信息获取方式！！！！
			if (vo == null) {
				jsonMap.put("result", false);
				jsonMap.put("meg", "没有可用的红包，请先领取");

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

	/**
	 * 我的二维码
	 * 
	 * @param newG
	 *            为1则重新生成
	 * @return
	 */
	@RequestMapping("myQrcode")
	@ResponseBody
	public Map<String, Object> myQrcode(HttpServletRequest request,
			HttpServletResponse response, String newG) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			MobMemberVo vo = UserUtil.getInstatnce().getUser(request);
			if (vo == null) {
				result.put("serviceCode", "9");// 未登陆
				return result;
			}
			Member userInfo = webMemberService.getMember(vo.getUserId());
			if (userInfo == null) {
				result.put("serviceCode", "8");// 数据有误
				return result;
			}
//			if (BaseUtil.isNotEmpty(userInfo.getMyQrCode())
//					&& !"1".equalsIgnoreCase(newG)) {
//				result.put("serviceCode", "0"); // 成功
//				result.put(
//						"url",
//						MallJstlFunction.PictureServerChange(
//								userInfo.getMyQrCode(), 0, null, null));
//				return result;
//			}

			String code = Integer.toString(vo.getUserId());
			String url = CommonConstant.MOBILE_URL + "/qruser/"
					+ (new String(Base64.encode(code.getBytes())));

			Date date = DateUtil.getGMTDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
			String dateStr = sdf.format(date);
			String uploadPath = "/" + dateStr;
			if (!new File(uploadPath).isDirectory()) {
				new File(uploadPath).mkdirs();
			}
			String fileName = Integer.toString(vo.getUserId())
					+ System.currentTimeMillis() + "my.jpg";
			OutputStream os = new FileOutputStream(uploadPath + "/" + fileName);
			QRcodeUtil.encodeMyCode(
					url,
					BaseUtil.isEmpty(vo.getPortrait()) ? null : new File(vo
							.getPortrait()), os);

			//userInfo.setMyQrCode(dateStr + "/" + fileName);
			//vo.setMyQrCode(userInfo.getMyQrCode());
			webMemberService.updateMember(userInfo);// 修改用户信息

			result.put("serviceCode", "0"); // 成功
//			result.put("url", MallJstlFunction.PictureServerChange(
//					userInfo.getMyQrCode(), 0, null, null));
			return result;
		} catch (Exception e) {
			logger.warn(e);
			e.printStackTrace();
			result.put("serviceCode", "8");// 数据有误
		}
		return result;
	}

	/**
	 * 推广二维码(微信公众号二维码)
	 * 
	 * @return
	 */
	@RequestMapping("myQrSpread")
	@ResponseBody
	public Map<String, Object> myQrSpread(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			MobMemberVo vo = UserUtil.getInstatnce().getUser(request);
			if (vo == null) {
				result.put("serviceCode", "9");// 未登陆
				return result;
			}
			Member userInfo = webMemberService.getMember(vo.getUserId());
			if (userInfo == null) {
				result.put("serviceCode", "8");// 数据有误
				return result;
			}
//			if (BaseUtil.isNotEmpty(userInfo.getWxQrCode())) {
//				result.put("serviceCode", "0"); // 成功
//				result.put(
//						"url",
//						MallJstlFunction.PictureServerChange(
//								userInfo.getWxQrCode(), 0, null, null));
//				return result;
//			}
			Date date = DateUtil.getGMTDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
			String dateStr = sdf.format(date);
			String uploadPath =  "/" + dateStr;
			if (!new File(uploadPath).isDirectory()) {
				new File(uploadPath).mkdirs();
			}
			String fileName = Integer.toString(vo.getUserId())
					+ System.currentTimeMillis() + ".jpg";

			// 微信二维码
			String accessToken = WechatConfig.getWxPlatAccessToken(
					Configure.getAppID4mp(), Configure.getAppSecret4mp());
			String ticket = WechatConfig.wxQrTicket(accessToken, Integer
					.toString(vo.getUserId() < WechatConfig.WxStartPos ? vo
							.getUserId()
							: (vo.getUserId() - WechatConfig.WxStartPos)));// 由于微信对外提供红包只能10万个，需要降低用户id号
			WechatConfig.saveQrCodeImg(URLEncoder.encode(ticket, "UTF-8"),
					fileName, uploadPath);

			String newFileName = uploadPath + "/to" + fileName;
			// 合成需要
			NarrowImage narrowImage = new NarrowImage();
			OutputStream os = new FileOutputStream(newFileName);
			QRcodeUtil.encodeSpread(
					narrowImage.zoomImage(uploadPath + "/" + fileName, 0.512f),
					vo.getRealName(), os);

//			userInfo.setWxQrCode(dateStr + "/to" + fileName);
//			vo.setWxQrCode(userInfo.getWxQrCode());
//			webMemberService.updateMember(userInfo);// 修改用户信息
//
//			result.put("serviceCode", "0"); // 成功
//			result.put("url", MallJstlFunction.PictureServerChange(
//					userInfo.getWxQrCode(), 0, null, null));
			return result;
		} catch (Exception e) {
			logger.warn(e);
			e.printStackTrace();
			result.put("serviceCode", "8");// 数据有误
		}
		return result;
	}

	

	/***********************************/
	/**
	 * 添加描述图片(头像)
	 * 
	 * @param imageFile
	 *            图片文件
	 * @param parentId
	 *            父节点ID
	 * @param request
	 *            请求
	 * @return
	 */
	@RequestMapping("/uUportrait")
	@ResponseBody
	public Map<String, Object> uUportrait(@RequestParam MultipartFile[] upfile,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		MobMemberVo vo = UserUtil.getInstatnce().getUser(request);
		// 未登录
		if (vo == null) {
			result.put("serviceCode", "9");// 未登陆
			return result;
		} else {
			Member userInfo = webMemberService.getMember(vo.getUserId());
			if (userInfo == null) {
				result.put("serviceCode", "8"); // 数据错误
				return result;
			}
			Date date = DateUtil.getGMTDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
			String dateStr = sdf.format(date);
			try {
				String uploadPath = "/" + dateStr;
				if (!new File(uploadPath).isDirectory()) {
					new File(uploadPath).mkdirs();
				}
				for (MultipartFile image : upfile) {
					String originalFileName = image.getOriginalFilename();
					String picExt = originalFileName.substring(originalFileName
							.lastIndexOf("."));
					// 更换图片名称，确保一个文件夹中文件名唯一
					String fileName = String.valueOf(Calendar.getInstance()
							.getTimeInMillis()) + picExt;
					File uploadFile = new File(uploadPath + "/" + fileName);
					FileUtils.copyInputStreamToFile(image.getInputStream(),
							uploadFile);
					userInfo.setPortrait(dateStr + "/" + fileName);
					webMemberService.updateMember(userInfo);// 修改用户信息
					result.put("serviceCode", "0"); // 成功
					result.put(
							"url",
							MallJstlFunction.PictureServerChange(dateStr + "/"
									+ fileName, 0, null, null));
					return result;
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.warn(e);
				result.put("serviceCode", "8");
				return result;
			}
			return result;
		}
	}

	// 判断支付密码
	@RequestMapping("/isPatPass")
	@ResponseBody
	public Map<String, Object> isPatPass(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			MobMemberVo vo = UserUtil.getInstatnce().getUser(request); // 新的会员信息获取方式！！！！
			if (vo == null) {
				result.put("serviceCode", "9"); //
				return result;
			}
			MemberDeposit dvo = webMemberDepositService
					.getMemberDepositById(vo.getUserId());
			if (dvo.getPayPass() != null && dvo.getPayPass().length() > 0) {
				result.put("serviceCode", "0");//
				result.put("type", "true");
				return result;
			} else {
				result.put("type", "false");
				result.put("serviceCode", "0");//
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("serviceCode", "8");
		}
		return result;
	}

	// 设置支付密码
	@RequestMapping("/setPayPass")
	@ResponseBody
	// code验证码 paywprd支付密码
	public Map<String, Object> setPayPass(HttpServletRequest request,
			String code, String payword) {
		Map<String, Object> result = new HashMap<String, Object>();

		MobMemberVo vo = UserUtil.getInstatnce().getUser(request);
		if (vo == null) {
			result.put("serviceCode", 9);
			return result;
		}
		String loginUserKey = vo.getUserId() + "_" + vo.getUserName();
		String vcode = UserUtil.getInstatnce().getUserCode(loginUserKey); // 获取用户的验证码
		// 手机号码和验证码均与session中匹配
		if (code != null && vcode != null && code.equals(vcode)) {
			try {
				int userId = vo.getUserId();
				String salt = VerificationUtil.getInstance().getCheckCodeImage(
						"0123456789", 4);
				String payPass = MD5Util.MD5(MD5Util.MD5(payword) + salt);
				webMemberService.updatePaymentPassword2(userId, payPass, salt);

				result.put("serviceCode", 0);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				logger.warn(e);
				result.put("serviceCode", 8);
				return result;
			}
		} else {
			result.put("serviceCode", 8);
		}
		return result;
	}

	// 修改支付密码
	@RequestMapping("/updataPayPass")
	@ResponseBody
	public Map<String, Object> updataPayPass(HttpServletRequest request,
			String oldPatWord, String newPayWord) {
		Map<String, Object> result = new HashMap<String, Object>();

		MobMemberVo vo = UserUtil.getInstatnce().getUser(request);
		if (vo == null) {
			result.put("serviceCode", 9);
			return result;
		}
		if (oldPatWord != null && newPayWord != null) {
			try {
				int userId = vo.getUserId();
				String oldSalt = webMemberService.getPaymentSalt(userId);

				if (MD5Util.MD5(MD5Util.MD5(oldPatWord) + oldSalt).equals(
						webMemberService.getPaymentPassword(userId))) {
					String salt = VerificationUtil.getInstance()
							.getCheckCodeImage("0123456789", 4);
					String payPass = MD5Util
							.MD5(MD5Util.MD5(newPayWord) + salt);
					webMemberService.updatePaymentPassword2(userId, payPass,
							salt);
					result.put("serviceCode", 0);
					return result;
				} else {
					result.put("serviceCode", 8);
					result.put("meg", "旧的支付密码错误");
					return result;
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.warn(e);
				result.put("serviceCode", 8);
				return result;
			}
		} else {
			result.put("serviceCode", 8);
		}
		return result;
	}

	@RequestMapping("/confirmPayPass")
	@ResponseBody
	public Map<String, Object> confirmPayPass(HttpServletRequest request,
			String payPass) {
		Map<String, Object> result = new HashMap<String, Object>();

		MobMemberVo user = UserUtil.getInstatnce().getUser(request);
		if (user == null) {
			result.put("serviceCode", 9);
			return result;
		}

		if (payPass != null) {
			try {
			MemberDeposit depositVo = webMemberDepositService
						.getMemberDepositById(user.getUserId());
				if (MD5Util.MD5(MD5Util.MD5(payPass) + depositVo.getSalt())
						.equals(depositVo.getPayPass())) {
					user.setOverduePayTime((long) 0);
					user.setConfirmCount(0);
					result.put("serviceCode", 0);
					result.put("meg", "支付密码正确");
					return result;

				} else { // 输错
					int confirmCount = user.getConfirmCount();
					long nowdate = System.currentTimeMillis();

					if (nowdate < user.getOverduePayTime()) {
						user.setConfirmCount(++confirmCount);
					} else {
						user.setOverduePayTime(System.currentTimeMillis() + 2 * 3600000);
						user.setConfirmCount(1);
					}

					result.put("serviceCode", 8);
					result.put("meg", Integer.toString(user.getReduceCount()));
					return result;
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.put("serviceCode", "8");
			}
			return result;
		}
		return result;
	}

	// 设置支付密码
	@RequestMapping("/forgetPayPass")
	@ResponseBody
	// code验证码 paywprd新支付密码
	public Map<String, Object> forgetPayPass(HttpServletRequest request,
			String code, String newPayword) {
		Map<String, Object> result = new HashMap<String, Object>();

		MobMemberVo vo = UserUtil.getInstatnce().getUser(request);
		if (vo == null) {
			result.put("serviceCode", 9);
			return result;
		}
		String loginUserKey = vo.getUserId() + "_" + vo.getUserName();
		String vcode = UserUtil.getInstatnce().getUserCode(loginUserKey); // 获取用户的验证码
		// 手机号码和验证码均与session中匹配
		if (code != null && vcode != null && code.equals(vcode)) {
			try {
				int userId = vo.getUserId();
				String salt = VerificationUtil.getInstance().getCheckCodeImage(
						"0123456789", 4);
				String payPass = MD5Util.MD5(MD5Util.MD5(newPayword) + salt);
				webMemberService.updatePaymentPassword2(userId, payPass, salt);

				result.put("serviceCode", 0);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				logger.warn(e);
				result.put("serviceCode", 8);
				return result;
			}
		} else {
			result.put("serviceCode", 8);
		}
		return result;
	}

}
