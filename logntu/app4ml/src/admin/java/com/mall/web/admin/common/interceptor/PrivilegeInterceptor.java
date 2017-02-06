package com.mall.web.admin.common.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mall.web.admin.common.annotation.Auth;
import com.mall.web.admin.common.utils.BaseAction;
import com.mall.web.admin.common.utils.HtmlUtil;
import com.mall.web.admin.common.utils.SessionUtils;
import com.mall.web.admin.security.service.SysAuthorizeService;
import com.mall.web.admin.security.vo.SysMngUserLoginBean;

/**
 * 
 * @功能：权限认证拦截器
 * @作者：印鲜刚
 * @创建日期：2010-04-20
 * 
 */
public class PrivilegeInterceptor extends HandlerInterceptorAdapter {

	private static Logger log = Logger.getLogger(PrivilegeInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// String referer = request.getHeader("Referer");
		String accept = request.getHeader("Accept");
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
		Auth auth = method.getMethod().getAnnotation(Auth.class);
		// 验证登陆超时问题 auth = null，默认验证
		if (auth == null || auth.verifyLogin()) {
			SysMngUserLoginBean user = SessionUtils.getUser(request);
			if (user == null) {
				// 当客服端请求为text/html的时候直接返回登录界面
				if (!accept.startsWith("application/json")) {
					response.setStatus(HttpServletResponse.SC_GATEWAY_TIMEOUT);
					response.sendRedirect(baseUri + "/admin/index");
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
		// 验证URL权限
		if (auth == null || auth.verifyURL()) {
			// 判断是否超级管理员
//			if (!SessionUtils.isAdmin(request)) {
//				String menuUrl = StringUtils.remove(request.getRequestURI(),
//						request.getContextPath());
//
//				SysMngUserLoginBean user = SessionUtils.getUser(request);
//				ApplicationContext context = WebApplicationContextUtils
//						.getWebApplicationContext(request.getServletContext());
//				SysAuthorizeService sysAuthorizeService = (SysAuthorizeService) context
//						.getBean("sysAuthorizeService");
//				
//				// 判断用户是否已被授予该action权限
//				if (sysAuthorizeService.getUrlCount(user.getId(),
//						StringUtils.trim(menuUrl)) <= 0) {
//					// 日志记录
//					String userName = SessionUtils.getUser(request)
//							.getUserName();
//					String msg = "URL权限验证不通过:[url=" + menuUrl + "][用户 ="
//							+ userName + "]";
//					log.error(msg);
//
//					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//					Map<String, Object> result = new HashMap<String, Object>();
//					result.put(BaseAction.SUCCESS, false);
//					result.put(BaseAction.MSG, "没有权限访问,请联系管理员.");
//					HtmlUtil.writerJson(response, result);
//					return false;
//				}
//			}
		}
		return super.preHandle(request, response, handler);
	}
}
