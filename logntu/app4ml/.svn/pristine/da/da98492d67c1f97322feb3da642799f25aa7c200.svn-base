package com.mall.web.mall.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mall.common.constant.CommonConstant;
import com.mall.common.util.BaseUtil;
import com.mall.web.common.util.CookieUtils;
import com.mall.web.mall.common.annotation.MemberAuth;
import com.mall.web.mall.common.utils.CkSessionUtils;
import com.mall.web.mall.domain.MemberAutoLogin;
import com.mall.web.mall.member.service.AutoLoginService;
import com.mall.web.mall.member.service.WebMemberService;
import com.mall.web.mall.member.vo.WebMemberVo;


/**
 * 
 * @功能：权限认证拦截器
 * @作者：印鲜刚
 * @创建日期：2010-04-20
 * 
 */
public class PrivilegeInterceptor4ckb extends HandlerInterceptorAdapter {

	//private static Logger log = Logger.getLogger(PrivilegeInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// String referer = request.getHeader("Referer");
		String accept = request.getHeader("Accept");
		accept = accept == null ? "" : accept;
		String baseUri = request.getContextPath();
		String path = request.getServletPath();
		// 防盗链,用户直接在浏览器地址里面输入action
		// if (referer == null) {
		// response.setStatus(HttpServletResponse.SC_GATEWAY_TIMEOUT);
		// response.sendRedirect(baseUri + "/admin/index");
		// return false;
		// }
		if (handler instanceof org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler) {
			return super.preHandle(request, response, handler);
		}
		HandlerMethod method = (HandlerMethod) handler;
		MemberAuth auth = method.getMethod().getAnnotation(MemberAuth.class);
		
		WebMemberVo user = CkSessionUtils.getUser(request);			
		// 通过cookie，获取自动登陆信息
		if (user == null) {
			String cookie = CookieUtils.getCookieValue(request,
					CommonConstant.COOKIE_AUTH);
			if (!BaseUtil.isEmpty(cookie)) {
				ApplicationContext context = WebApplicationContextUtils
						.getWebApplicationContext(request
								.getServletContext());
				AutoLoginService autoLoginService = (AutoLoginService) context
						.getBean("autoLoginService");
				WebMemberService memberService = (WebMemberService) context
						.getBean("webMemberService");
				MemberAutoLogin auto = autoLoginService
						.findAutoLogins(cookie);
				if (!BaseUtil.isEmpty(auto)) {
					user = memberService.getUserInfo(Integer.parseInt(auto
							.getUserId()));
					if (user != null)
						CkSessionUtils.setUser(request, user);
				}

			}
		}
		/**
		 * 一、登陆判断
		 */
		// 验证登陆超时问题 auth = null，默认验证
		if (auth == null || auth.verifyLogin()) {			
			if (user == null) {
				// 当客服端请求为text/html的时候直接返回登录界面
				if (!accept.startsWith("application/json")) {
					response.setStatus(HttpServletResponse.SC_GATEWAY_TIMEOUT);
					response.sendRedirect(baseUri + "/member/login");
					return false;
				}
				// 当客服端请求其他类型的时候(json),设置响应头，并设置响应状态为401(未授权)
				response.setStatus(401);
				response.addHeader("Error-Json", "{reason:'notLogin',content:'"
						+ path + "'}");

				// response.setStatus(HttpServletResponse.SC_GATEWAY_TIMEOUT);
				// Map<String, Object> result = new HashMap<String, Object>();
				// result.put(BaseAction.SUCCESS, false);
				// result.put(BaseAction.LOGOUT_FLAG, true);// 登录标记 true 退出
				// result.put(BaseAction.MSG, "登录超时.");
				// HtmlUtil.writerJson(response, result);
				return false;
			}
		}
		return super.preHandle(request, response, handler);
	}
}
