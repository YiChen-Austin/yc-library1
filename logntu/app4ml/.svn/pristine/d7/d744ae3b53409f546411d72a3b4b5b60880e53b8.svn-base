package com.mall.common.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mall.common.constant.CommonConstant;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DESUtil;

public class CookieUtil {
	@SuppressWarnings("unchecked")
	public static boolean loadClient(HttpServletRequest request) {
		return false;
//		String cookieValue = null;
//		Cookie[] cookies = request.getCookies();
//		for (int i = 0; cookies!=null&&i < cookies.length; i++) {
//			Cookie cookie = cookies[i];
//			if (cookie.getName().equalsIgnoreCase(
//					CommonConstant.SYSTEM_COOKIE_TOKEN)) {
//				cookieValue = cookie.getValue(); // 得到cookie的用户名
//				break;
//			}
//		}
//		if (BaseUtil.isEmpty(cookieValue))
//			return false;
//		ApplicationContext context = WebApplicationContextUtils
//				.getWebApplicationContext(request.getSession()
//						.getServletContext());
//		if (BaseUtil.isEmpty(context))
//			return false;
//		AutoLoginService autoLoginService = (AutoLoginService) context
//				.getBean("autoLoginService");
//		ClientService cientService = (ClientService) context
//				.getBean("clientService");
//		ClientAction clientAction = (ClientAction) context
//				.getBean("clientAction");
//		List<MemberAutoLogin> list = null;
//		try {
//			list = autoLoginService.findAutoLogins(cookieValue);
//			if (list == null || list.size() <= 0)
//				return false;
//			String clientId = list.get(0).getUserId();
//			Client client = cientService.findClientById(clientId);
//			if (BaseUtil.isEmpty(client))
//				return false;
//			SysUserLoginBean sysUserLoginBean = new SysUserLoginBean();
//			sysUserLoginBean.setRealName(clientAction.doRealName(client
//					.getRealName()));
//			sysUserLoginBean.setUserName(client.getLoginName());
//			sysUserLoginBean.setPassword(DESUtil.getInstance().decrypt(
//					client.getPassword()));
//			// 将登录信息放入session
//			request.getSession().setAttribute(CommonConstant.SESSION_USER,
//					sysUserLoginBean);
//			Map<String, String> users = (Map<String, String>) request
//					.getSession().getServletContext()
//					.getAttribute(CommonConstant.SERVLETCONTEXT_ONLINE_USER);
//			if (users == null) {
//				users = new HashMap<String, String>();
//			}
//			users.put(sysUserLoginBean.getUserName(), request.getSession()
//					.getId());
//			request.getSession()
//					.getServletContext()
//					.setAttribute(CommonConstant.SERVLETCONTEXT_ONLINE_USER,
//							users);
//			sysUserLoginBean.setOrganizationId(null);
//			return true;
//		} catch (Exception e) {e.printStackTrace();
//			return false;
//		}
	}
}
