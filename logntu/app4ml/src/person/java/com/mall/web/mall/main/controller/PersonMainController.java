package com.mall.web.mall.main.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.exception.FrameworkException;
import com.mall.common.mail.Mail4Reg;
import com.mall.common.mail.Mail4RegUtil;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.VerificationUtil;
import com.mall.web.admin.common.utils.BaseAction;
import com.mall.web.mall.common.annotation.MemberAuth;
import com.mall.web.mall.common.utils.CkSessionUtils;
import com.mall.web.mall.common.utils.HttpSendSms;
import com.mall.web.mall.common.utils.MallEnum;
import com.mall.web.mall.distribut.service.MemberDistService;
import com.mall.web.mall.distribut.vo.MemberDistRelationVo;
import com.mall.web.mall.domain.Member;
import com.mall.web.mall.member.service.FeedBackService;
import com.mall.web.mall.member.service.WebMemberDepositService;
import com.mall.web.mall.member.service.WebMemberService;
import com.mall.web.mall.member.vo.FeedBackVo;
import com.mall.web.mall.member.vo.WebMemberVo;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/member/")
public class PersonMainController extends BaseAction {
	private static Logger logger = Logger.getLogger(PersonMainController.class);
	@Resource(name = "webMemberService")
	private WebMemberService webMemberService;// 会员信息处理
	@Resource(name = "memberDistService")
	private MemberDistService memberDistService;// 多级会员关系处理
	@Autowired
	protected WebMemberDepositService webMemberDepositService;// 會員賬戶信息
	
	@Autowired
	private FeedBackService feedBackService;//意见反馈

	/**
	 * 个人中心首页
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("main")
	@MemberAuth(verifyLogin = true)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, String service) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/main");
		return mav;
	}

	/**
	 * 注册导航
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("reg")
	@MemberAuth(verifyLogin = false)
	public ModelAndView reg(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/register/reg");
		return mav;
	}

	/**
	 * 个人中心（邮件注册）
	 * 
	 * @param request
	 * @param response
	 * @param suser
	 *            推广用户
	 * @return
	 */
	@RequestMapping("reg4Email")
	@MemberAuth(verifyLogin = false)
	public ModelAndView reg4Email(HttpServletRequest request, HttpServletResponse response, WebMemberVo memberVo,
			String code/* , String suser */) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> result = new HashMap<String, Object>();
		mav.setViewName("person/register/reg");
		result.put("regType", "email");

		// 判断是否已经登录
		if (!BaseUtil.isEmpty(CkSessionUtils.getUser(request))) {
			mav.setViewName("redirect:/member/index");
			return mav;
		}
		if (BaseUtil.isEmpty(memberVo) || BaseUtil.isEmpty(memberVo.getEmail())) {
			result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.DataErr.getIndex());
			mav.addAllObjects(result);
			return mav;
		}
		// 验证码是否正确
		String vcode = CkSessionUtils.getValidateCode(request);
		CkSessionUtils.removeValidateCode(request);
		if (BaseUtil.isEmpty(vcode) || BaseUtil.isEmpty(code) || !vcode.equalsIgnoreCase(code)) {
			result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.DataErr.getIndex());
			result.put("msg", "验证码错误");
			mav.addAllObjects(result);
			return mav;
		}
		// 注册处理
		try {
			// 记录推荐码
			if (BaseUtil.isNotEmpty(CkSessionUtils.getUserSpread(request)))
				memberVo.setPhoneTel(Integer.toString(CkSessionUtils.getUserSpread(request)));
			Member member = webMemberService.regist4Em(memberVo);
			// 注册失败
			if (BaseUtil.isEmpty(member)) {
				result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.OtherErr.getIndex());
				result.put("msg", "注册失败");
				mav.addAllObjects(result);
				return mav;
			} // 成功注册(跳转邮箱验证页面)
			else {
				// 发送邮件
				String baseUri = request.getRequestURL().toString();
				baseUri = baseUri.substring(0, baseUri.indexOf(request.getRequestURI()));
				baseUri += request.getContextPath();
				webMemberService.sendRegEmail(member, baseUri);

				mav.setViewName("person/register/emailSucc");
				result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.Success.getIndex());
				result.put("msg", "注册成功");
				String mail = memberVo.getEmail();
				result.put("mailUrl", "http://mail." + mail.substring(mail.indexOf("@") + 1));// 前往邮箱地址
				mav.addAllObjects(result);
				return mav;
			}
		} catch (Exception e) {
			logger.warn(e);
			e.printStackTrace();
			result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.OtherErr.getIndex());
			result.put("msg", "注册失败");
			mav.addAllObjects(result);
			return mav;
		}
	}

	/**
	 * 个人中心首页
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("confirmEmail")
	@MemberAuth(verifyLogin = false)
	public ModelAndView confirmEmail(HttpServletRequest request, HttpServletResponse response, String contextId,
			String checkCode) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int userId = webMemberService.getRegValidId(contextId);
			if (userId <= 0) {
				result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.OtherErr.getIndex());
				result.put("msg", "激活失败");
			} // 进行激活处理
			else if (webMemberService.activateRegEm(userId, checkCode)) {
				result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.Success.getIndex());
				result.put("msg", "激活成功");
			} else {
				result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.OtherErr.getIndex());
				result.put("msg", "激活失败");
			}
		} catch (Exception e) {
			logger.warn(e);
			e.printStackTrace();
			result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.OtherErr.getIndex());
			result.put("msg", "激活失败");
			return mav;
		}
		mav.setViewName("person/register/confirmEmail");
		mav.addAllObjects(result);
		return mav;
	}

	/**
	 * 个人中心（短信注册）
	 * 
	 * @param request
	 * @param response
	 * @param suser
	 *            推广用户
	 * @return
	 */
	@RequestMapping("reg4Sm")
	@MemberAuth(verifyLogin = false)
	public ModelAndView reg4Sm(HttpServletRequest request, HttpServletResponse response, WebMemberVo memberVo,
			String code/* , String suser */) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> result = new HashMap<String, Object>();
		mav.setViewName("person/register/reg");
		result.put("regType", "sms");

		// 判断是否已经登录
		if (!BaseUtil.isEmpty(CkSessionUtils.getUser(request))) {
			mav.setViewName("redirect:/member/index");
			return mav;
		}
		if (BaseUtil.isEmpty(memberVo) || BaseUtil.isEmpty(memberVo.getPhoneMob())) {
			result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.DataErr.getIndex());
			mav.addAllObjects(result);
			return mav;
		}
		// 短信验证码是否正确
		String vcode = CkSessionUtils.getSmValidateCode(request);
		CkSessionUtils.removeSmValidateCode(request);
		if (BaseUtil.isEmpty(vcode) || BaseUtil.isEmpty(code) || !vcode.equalsIgnoreCase(code)) {
			result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.DataErr.getIndex());
			result.put("msg", "验证码错误");
			mav.addAllObjects(result);
			return mav;
		}
		// 注册处理
		try {
			Member member = webMemberService.regist4Sm(memberVo);
			// 注册失败
			if (BaseUtil.isEmpty(member)) {
				result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.OtherErr.getIndex());
				result.put("msg", "注册失败");
				mav.addAllObjects(result);
				return mav;
			} // 成功注册(跳转邮箱验证页面)
			else {
				// 建立会员关系（多级推广）
				Integer spreadUser = CkSessionUtils.getUserSpread(request);
				if (BaseUtil.isNotEmpty(spreadUser) && BaseUtil.isNotEmpty(webMemberService.getMember(spreadUser))) {
					memberDistService.doMemberDist(member.getUserId(), spreadUser, null, null);
				}
				// 对新用户进行开户
				webMemberDepositService.openNewAccount(member.getUserId(), member.getPhoneMob());
				mav.setViewName("redirect:/member/index");
				result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.Success.getIndex());
				result.put("msg", "注册成功");
				mav.addAllObjects(result);
				return mav;
			}
		} catch (Exception e) {
			logger.warn(e);
			e.printStackTrace();
			result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.OtherErr.getIndex());
			result.put("msg", "注册失败");
			mav.addAllObjects(result);
			return mav;
		}
	}
	
	/**
	 * 个人中心（登陆）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("login")
	@MemberAuth(verifyLogin = false)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, WebMemberVo memberVo,
			String service) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> result = new HashMap<String, Object>();
		// 判断是否已经登录
		if (!BaseUtil.isEmpty(CkSessionUtils.getUser(request))) {
			if (!BaseUtil.isEmpty(service)) {
				mav.setViewName("redirect:" + service);
				return mav;
			}
			mav.setViewName("redirect:/member/index");
			return mav;
		}
		// get方式提取数据
		if ("get".equalsIgnoreCase(request.getMethod())) {
			mav.setViewName("person/login/login");
			result.put("service", service);
			mav.addObject(result);
			return mav;
		}
		if (BaseUtil.isEmpty(memberVo) || BaseUtil.isEmpty(memberVo.getUserName())
				|| BaseUtil.isEmpty(memberVo.getPassword())) {
			mav.setViewName("person/login/login");
			mav.addObject("service", service);
			return mav;
		}
		try {
			WebMemberVo user = webMemberService.login(memberVo);
			// 登录失败
			if (user == null) {
				mav.setViewName("person/login/login");
				result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.DataErr.getIndex());
				result.put("msg", "用户名或密码错误");
				mav.addObject(result);
				return mav;
			}
			CkSessionUtils.setUser(request, user);
			webMemberService.updateLogin(user.getUserId(), getIpAddr(request));
			// 是否十天内自动登录
			// if ("1".equalsIgnoreCase(memberVo.getAutoLogin())) {
			// String tk = autoLoginService.saveAutoLogin(
			// Integer.toString(user.getId()), user.getUserName(),
			// request.getRemoteAddr());
			// CookieUtils.setCookie(request, response, "localhost",
			// CommonConstant.COOKIE_AUTH, tk, 15 * 24 * 60 * 60,
			// "UTF-8");
			// // mav.setViewName("redirect:/member/index");
			// // return mav;
			// }
		} catch (Exception e) {
			logger.warn(e);
			e.printStackTrace();
		}
		if (BaseUtil.isEmpty(service)) {
			service = "/member/index";
		}
		mav.setViewName("redirect:" + service);
		return mav;
	}

	/**
	 * 个人中心（登陆）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("login4ajax")
	@MemberAuth(verifyLogin = false)
	@ResponseBody
	public ModelAndView login4ajax(HttpServletRequest request, HttpServletResponse response, WebMemberVo memberVo,
			String service) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> result = new HashMap<String, Object>();
		// 判断是否已经登录
		if (!BaseUtil.isEmpty(CkSessionUtils.getUser(request))) {
			if (!BaseUtil.isEmpty(service)) {
				mav.setViewName("redirect:" + service);
				return mav;
			}
			mav.setViewName("redirect:/member/index");
			return mav;
		}
		// get方式提取数据
		if ("get".equalsIgnoreCase(request.getMethod())) {
			mav.setViewName("person/login/login");
			result.put("service", service);
			mav.addObject(result);
			return mav;
		}
		if (BaseUtil.isEmpty(memberVo) || BaseUtil.isEmpty(memberVo.getUserName())
				|| BaseUtil.isEmpty(memberVo.getPassword())) {
			mav.setViewName("person/login/login");
			mav.addObject("service", service);
			return mav;
		}
		try {
			WebMemberVo user = webMemberService.login(memberVo);
			// 登录失败
			if (user == null) {
				mav.setViewName("person/login/login");
				result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.DataErr.getIndex());
				result.put("msg", "用户名或密码错误");
				mav.addObject(result);
				return mav;
			}
			CkSessionUtils.setUser(request, user);
			webMemberService.updateLogin(user.getUserId(), getIpAddr(request));
			// 是否十天内自动登录
			// if ("1".equalsIgnoreCase(memberVo.getAutoLogin())) {
			// String tk = autoLoginService.saveAutoLogin(
			// Integer.toString(user.getId()), user.getUserName(),
			// request.getRemoteAddr());
			// CookieUtils.setCookie(request, response, "localhost",
			// CommonConstant.COOKIE_AUTH, tk, 15 * 24 * 60 * 60,
			// "UTF-8");
			// // mav.setViewName("redirect:/member/index");
			// // return mav;
			// }
		} catch (Exception e) {
			logger.warn(e);
			e.printStackTrace();
		}
		if (BaseUtil.isEmpty(service)) {
			service = "/member/index";
		}
		mav.setViewName("redirect:" + service);
		return mav;
	}

	/**
	 * 登陆用户修改密码
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("updatePw")
	@ResponseBody
	@MemberAuth(verifyLogin = true)
	public Object updatePw(HttpServletRequest request, String oldPw, String newPw) {
		Map<String, Object> result = new HashMap<String, Object>();
		WebMemberVo member = CkSessionUtils.getUser(request);
		if (webMemberService.updatePw(member, oldPw, newPw)) {
			result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.Success.getIndex());
			result.put("msg", "修改成功");
		} else {
			result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.DataErr.getIndex());
			result.put("msg", "修改失败");
		}
		;
		return result;
	}
	/**
	 * 导航修改手机号
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("update_phone")
	@MemberAuth(verifyLogin = true)
	public ModelAndView update_phone(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/info/update_phone");
		return mav;
	}
	/**
	 * 导航修改手机号
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("new_phone")
	@MemberAuth(verifyLogin = true)
	public ModelAndView new_phone(HttpServletRequest request, HttpServletResponse response,String token) {
		ModelAndView mav = new ModelAndView();
		Object oldToken =  request.getSession().getAttribute("phone_valid_token");
		if(oldToken!=null&&token!=null&&oldToken.toString().equals(token))
			mav.setViewName("person/info/new_phone");
		else
			mav.setViewName("redirect:/member/update_phone");
		return mav;
	}
	

	
	/**
	 * 导航修改手机成功
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("phone_suc")
	@MemberAuth(verifyLogin = true)
	public ModelAndView phone_suc(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/info/phone_suc");
		return mav;
	}
	/**
	 * 登陆用户修改手机号
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("updatePhone")
	@ResponseBody
	@MemberAuth(verifyLogin = true)
	public Object updatePhone(HttpServletRequest request, String phone) {
		Map<String, Object> result = new HashMap<String, Object>();
		WebMemberVo member = CkSessionUtils.getUser(request);
		int id = member.getUserId();
		try {
			if (webMemberService.updatePhone(id, phone)) {
				result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.Success.getIndex());
				result.put("msg", "修改成功");
			} else {
				result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.DataErr.getIndex());
				result.put("msg", "修改失败");
			}
		} catch (FrameworkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return result;
	}
	/**
	 * 导航修改邮箱号
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("update_email")
	@MemberAuth(verifyLogin = true)
	public ModelAndView update_email(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/info/update_email");
		return mav;
	}
	/**
	 * 导航确认邮箱号
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("new_email")
	@MemberAuth(verifyLogin = true)
	public ModelAndView new_email(HttpServletRequest request, HttpServletResponse response,String token) {
		ModelAndView mav = new ModelAndView();
		Object oldToken =  request.getSession().getAttribute("phone_valid_token");
		if(oldToken!=null&&token!=null&&oldToken.toString().equals(token))
			mav.setViewName("person/info/new_email");
		else
			mav.setViewName("redirect:/member/update_email");
		return mav;
	}
	/**
	 * 登陆用户确认邮箱号
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("updateEmail")
	@ResponseBody
	@MemberAuth(verifyLogin = true)
	public Object updateEmail(HttpServletRequest request, String email) {
		Map<String, Object> result = new HashMap<String, Object>();
		WebMemberVo member = CkSessionUtils.getUser(request);
		int id = member.getUserId();
		try {
			if (webMemberService.updateEmail(id, email)) {
				result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.Success.getIndex());
				result.put("msg", "修改成功");
			} else {
				result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.DataErr.getIndex());
				result.put("msg", "修改失败");
			}
		} catch (FrameworkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return result;
	}
	/**
	 * 导航修改邮箱成功
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("email_suc")
	@MemberAuth(verifyLogin = true)
	public ModelAndView email_suc(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/info/email_suc");
		return mav;
	}

	/**
	 * 用户协议
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("agreement")
	@MemberAuth(verifyLogin = false)
	public ModelAndView agreement(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/agreement/agreement");
		return mav;
	}
	/**
	 * 邮箱认证成功
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("emailrzcg")
	@MemberAuth(verifyLogin = false)
	public ModelAndView emailrzcg(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/register/emailrzcg");
		return mav;
	}
	
	/**
	 * 导航邮件发送
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("emailSucc")
	@MemberAuth(verifyLogin = false)
	public ModelAndView emailSucc(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/register/emailSucc");
		return mav;
	}

	/**
	 * 404
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("err404")
	@MemberAuth(verifyLogin = false)
	public ModelAndView error(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/error/404");
		return mav;
	}
	
	
	
	

	/**
	 * 导航到找回密码
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("retrieve")
	@MemberAuth(verifyLogin = false)
	public ModelAndView retrieve(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/login/retrieve");
		return mav;
	}

	/**
	 * 验证验证码，导航修改密码
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("confirmCode")
	@ResponseBody
	@MemberAuth(verifyLogin = false)
	public Object confirmPwCode(HttpServletRequest request, HttpServletResponse response, String userName,
			String code) {
		Map<String, Object> result = new HashMap<String, Object>();
		String vcode = CkSessionUtils.getSmValidateCode(request);
		CkSessionUtils.removeSmValidateCode(request);
		if (BaseUtil.isEmpty(vcode) || BaseUtil.isEmpty(code) || !vcode.equalsIgnoreCase(code)) {
			result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.DataErr.getIndex());
			result.put("msg", "失败");
		} else {
			WebMemberVo user = webMemberService.getUserInfo(userName);
			if (BaseUtil.isNotEmpty(user)) {
				result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.Success.getIndex());
				result.put("msg", "成功");
				String token = UUID.randomUUID().toString().replaceAll("-", "");
				result.put("token", token);
				request.getSession().setAttribute("phone_valid_token", token);
				CkSessionUtils.setUserFindPw(request, user);
			}else{
				result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.DataErr.getIndex());
				result.put("msg", "失败");
			}
		}
		return result;
	}
	
	
	
	/**
	 * 导航修改密码
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "updatePwd",method=RequestMethod.GET)
	@MemberAuth(verifyLogin = false)
	public String upDatePw(HttpServletRequest request, HttpServletResponse response) {
		WebMemberVo user=CkSessionUtils.getUserFindPw(request);
		if(BaseUtil.isEmpty(user)){
			return "redirect:/member/retrieve";
		}
		return "person/login/upDatePw";
	}
	
	/**
	 * 确认修改密码
	 */
	@RequestMapping(value = "updatePwd",method=RequestMethod.POST)
	@MemberAuth(verifyLogin = false)
	@ResponseBody
	public Object confirmUpdatePwd(HttpServletRequest request, HttpServletResponse response) {
		WebMemberVo user=CkSessionUtils.getUserFindPw(request);
		boolean result = webMemberService.updatePw(user, request.getParameter("newPw"));
		Map<String,String> returnVal = new HashMap<String,String>();
		if(result) {
			returnVal.put("msg", "修改成功");
			CkSessionUtils.removeUserFindPw(request);
		}else
			returnVal.put("mgs", "修改失败");
		return returnVal;
	}
	
	/**
	 * 修改成功
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("success")
	@MemberAuth(verifyLogin = false)
	public ModelAndView success(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/login/success");
		return mav;
	}

	/**
	 * 判断用户是否登陆过，如果已登陆则直接到主页面，未登陆则进入登陆界面
	 * 
	 * @return
	 * @throws FrameworkException
	 * @throws Exception
	 */
	@RequestMapping(value = { "/index", "/index/*", "/index/*/*" })
	@MemberAuth(verifyLogin = false)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		// 用户登陆信息
		WebMemberVo user = CkSessionUtils.getUser(request);
		// 判断用户是否登陆,登陆则跳转主页面
		if (!BaseUtil.isEmpty(user)) {
			mav.setViewName("redirect:/member/main");
		}
		// 如果未登陆，则跳转登陆页面
		else {
			mav.setViewName("person/login/login");
		}
		return mav;
	}

	/***************************/
	/**
	 * 图片验证码生成接口
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("vcode")
	@MemberAuth(verifyLogin = false)
	public void verification(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String validateCode = VerificationUtil.getInstance().getCheckCodeImage(4);
		CkSessionUtils.setValidateCode(request, validateCode);
		VerificationUtil.getInstance().writeCheckCodeImage(validateCode, response.getOutputStream());

	}

	/**
	 * 短信验证码发送
	 * 
	 * @param phone
	 *            手机号码
	 */
	@RequestMapping("smsSend")
	@ResponseBody
	public Map<String, Object> smsSend(HttpServletRequest request, String phone, String code) {
		Map<String, Object> result = new HashMap<String, Object>();
		String vcode = CkSessionUtils.getValidateCode(request);
		CkSessionUtils.removeValidateCode(request);
		// # 错误码: 图片验证码错误
		if (BaseUtil.isEmpty(vcode) || BaseUtil.isEmpty(phone) || BaseUtil.isEmpty(code) || !code.equals(vcode)) {
			result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.DataErr.getIndex());
			return result;
		}
		try {
			String validCode = VerificationUtil.getInstance().getCheckCodeImage("0123456789", 6);// 验证码
			CkSessionUtils.setSmValidateCode(request, validCode);
			HttpSendSms.postSend(phone, validCode);
			result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.Success.getIndex());
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
			result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.SysErr.getIndex());
		}
		return result;
	}

	/**
	 * 邮件验证码发送
	 * 
	 * @param email
	 *            手机号码
	 */
	@RequestMapping("mailSend")
	@ResponseBody
	public Map<String, Object> mailSend(HttpServletRequest request, String email, String code) {
		Map<String, Object> result = new HashMap<String, Object>();
		String vcode = CkSessionUtils.getValidateCode(request);
		CkSessionUtils.removeValidateCode(request);
		// # 错误码: 图片验证码错误
		if (BaseUtil.isEmpty(vcode) || BaseUtil.isEmpty(email) || !BaseUtil.isEmail(email) || BaseUtil.isEmpty(code)
				|| !code.equals(vcode)) {
			result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.DataErr.getIndex());
			return result;
		}
		try {
			String validCode = VerificationUtil.getInstance().getCheckCodeImage("0123456789", 6);// 验证码
			CkSessionUtils.setSmValidateCode(request, validCode);
			Mail4Reg mail = new Mail4Reg();
			mail.setSubject("邮箱找回密码验证码");
			mail.setReceiver(email);
			mail.setMessage4Find(validCode);
			Mail4RegUtil.send(mail);
			result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.Success.getIndex());
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
			result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.SysErr.getIndex());
		}
		return result;
	}

	/**
	 * 用户退出
	 * 
	 * @return
	 * @throws FrameworkException
	 * @throws Exception
	 */
	@RequestMapping("exit")
	@MemberAuth(verifyLogin = false)
	public String exitLogin(HttpServletRequest request, HttpServletResponse response)
			throws FrameworkException, Exception {
		logger.debug("LoginAction exitLogin()");
		// 登陆用户信息
		CkSessionUtils.removeUser(request);

		/*
		 * String cookie = CookieUtils.getCookieValue(request,
		 * CommonConstant.COOKIE_AUTH); if (!BaseUtil.isEmpty(cookie)) {
		 * autoLoginService.removeAutoLogin(cookie);
		 * CookieUtils.setCookie(request, response, "localhost",
		 * CommonConstant.COOKIE_AUTH, "", 0, "UTF-8"); }
		 */
		return "redirect:/";
	}

	/**
	 * 推广码获取
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("spread")
	@MemberAuth(verifyLogin = false)
	public ModelAndView spread(HttpServletRequest request, HttpServletResponse response, String code) throws Exception {
		Integer spread = 0;
		// 保存推广码
		if (BaseUtil.isNotEmpty(code) && (spread = WebMemberService.decodeSpread(code)) > 0) {
			CkSessionUtils.setUserSpread(request, spread);
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/member/index");
		return mav;
	}
	/**
	 * 我要反馈
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="feedBack",method=RequestMethod.POST)
	@MemberAuth(verifyLogin = true)
	public ModelAndView feedback(HttpServletRequest request,FeedBackVo feedBackVo,MultipartFile file) throws Exception{
		WebMemberVo user = CkSessionUtils.getUser(request.getSession());
		if(file!=null&&!file.isEmpty()){
			String path = request.getServletContext().getRealPath("/upload/feedback");
			String lpath = "/upload/"+saveFile(path, Integer.toString(user.getUserId()), file);
			feedBackVo.setFeedBackimg(lpath);
		}
		feedBackVo.setUserId(user.getUserId());
		feedBackVo.setFeedBackdate(new Date());
		feedBackService.save(feedBackVo);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/member/feedbackSucc");
		return mav;
	}
	/**
	 * 反馈成功
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("feedbackSucc")
	@MemberAuth(verifyLogin = true)
	public ModelAndView feedbackSucc(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/service/feedbackSucc");
		return mav;
	}
	
	/**
	 * 分销查询上级
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("findSuperior")
	@MemberAuth(verifyLogin = true)
	public @ResponseBody String findSuperior(HttpServletRequest request, HttpServletResponse response){
		WebMemberVo user = CkSessionUtils.getUser(request.getSession());
		 List<MemberDistRelationVo> list = memberDistService.findSuperior(user.getUserId());
		 response.setContentType("application/json;charset=UTF-8");
		 return JSONArray.fromObject(list).toString();
	}
	/**
	 * 分销查询一级
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("findOneDist")
	@MemberAuth(verifyLogin = true)
	public @ResponseBody String findOneDist(HttpServletRequest request){
		WebMemberVo user = CkSessionUtils.getUser(request.getSession());
		List<MemberDistRelationVo> list = memberDistService.findOneDist(user.getUserId());
		return JSONArray.fromObject(list).toString();
	}
	/**
	 * 分销查询二级
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("findTwoDist")
	@MemberAuth(verifyLogin = true)
	public @ResponseBody String findTwoDist(HttpServletRequest request){
		WebMemberVo user = CkSessionUtils.getUser(request.getSession());
		List<MemberDistRelationVo> list = memberDistService.findTwoDist(user.getUserId());
		return JSONArray.fromObject(list).toString();
	}
	/**
	 * 分销查询三级
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("findThreeDist")
	@MemberAuth(verifyLogin = true)
	public @ResponseBody String findThreeDist(HttpServletRequest request){
		WebMemberVo user = CkSessionUtils.getUser(request.getSession());
		List<MemberDistRelationVo> list = memberDistService.findThreeDist(user.getUserId());
		return JSONArray.fromObject(list).toString();
	}
	
	/**
	 * 导航银行卡绑定成功
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("bank_suc")
	@MemberAuth(verifyLogin = true)
	public ModelAndView bank_suc(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/spread/bank_suc");
		return mav;
	}
	
}