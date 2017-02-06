package com.mall.web.mall.third.wechat.contorller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.common.util.MD5Util;
import com.mall.web.mall.third.qq.service.MemberBoundService;
import com.mall.web.mall.third.wechat.config.WechatConfig;
import com.mall.web.mall.domain.MemberBound;
import com.mall.web.mall.domain.Member;
import com.mall.web.mall.member.service.WebMemberService;
import com.tencent.common.Configure;


/**
  * @Description(描述)	: 微信登录
  * @author(作者)		: wangliyou
  * @date (开发日期)		: 2015年11月3日 上午9:48:35
  */
@Controller
@RequestMapping("/wechat")
public class WechatLoginController {
	//用户信息
	@Resource(name="webMemberService")
	private WebMemberService webMemberService;
	//绑定
	@Resource(name="memberBoundService")
	private MemberBoundService memberBoundService;
	
	/**
	 * @Description(功能描述)		: 微信登录入口
	 * @author(作者)				: wangliyou
	 * @date (开发日期)			: 2015年10月20日 下午5:37:23 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response)   {
		StringBuilder url=new StringBuilder();
		url.append(WechatConfig.GetCodeUrl);
		url.append("appid="+Configure.getAppID4openWeb());
		url.append("&redirect_uri="+URLEncoder.encode(WechatConfig.RedirectUri));
		url.append("&response_type="+WechatConfig.ResponseType);
		url.append("&scope="+WechatConfig.ScopeLogin);
		url.append("#wechat_redirect");
		return "redirect:"+url.toString();
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
		StringBuilder url=new StringBuilder();
		try {
			if(BaseUtil.isNotEmpty(request.getParameter("code"))){
				String code=request.getParameter("code");
				url.append(WechatConfig.GetAccessTokenUrl);
				url.append("appid="+Configure.getAppID4openWeb());
				url.append("&secret="+Configure.getAppSec4openWeb());
				url.append("&code="+code);
				url.append("&grant_type="+WechatConfig.GrantTypeCode);
				String result=WechatConfig.sendGet(url.toString());
				JSONObject obj = JSONObject.fromObject(result);  
				if(!obj.containsKey("errcode")){	//验证成功
					String openID=obj.getString("openid");
					MemberBound boundId = memberBoundService.findMemberBound(openID,1); // 判断是否绑定过
					if (BaseUtil.isNotEmpty(boundId)) {
						//boolean casresult=CasProxyLoginUtils.loginByUsrPwd(request, response, "wangzi", "test123");
						//System.out.println("casresult="+casresult);
						mav.setViewName("redirect:/shopCart/shopCart");
						  
					} else {
						//boolean casresult=CasProxyLoginUtils.loginByUsrPwd(request, response, "wangzi", "test123");
						//System.out.println("casresult="+casresult);
						mav.setViewName("redirect:/shopCart/shopCart");
						//mav.addObject("openID", openID);
						//mav.setViewName("mall/connect/wechatBound");
					}
				}else{												//流产
					mav.setViewName("404");
				}
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
					Member user=webMemberService.getMember(member.getUserName());
					if(BaseUtil.isEmpty(user)){						//不存在此用户
						member.setPassword(MD5Util.MD5(member.getPassword()));
						int userId =webMemberService.saveMember(member);
						MemberBound bound = new MemberBound();
						bound.setBoundTime(DateUtil.getCurrentTime());
						bound.setChannel(1);
						bound.setOpenId(openID);
						bound.setUserId(userId);
						memberBoundService.saveMemberBound(bound);
						jsonMap.put("result", true);
					}else{
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
