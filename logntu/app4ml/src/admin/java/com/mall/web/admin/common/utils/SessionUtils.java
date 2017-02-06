package com.mall.web.admin.common.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.mall.common.constant.CommonConstant;
import com.mall.web.admin.security.vo.SysMngUserLoginBean;

/**
 * 
 * Cookie 工具类
 * 
 */
public final class SessionUtils {

	public static final Logger logger = Logger.getLogger(SessionUtils.class);

	public static final String SESSION_USER = "session_mng_user";

	public static final String SESSION_VALIDATECODE = "session_validatecode";// 验证码

	public static final String SESSION_ACCESS_URLS = "session_access_urls"; // 系统能够访问的URL

	public static final String SESSION_MENUBTN_MAP = "session_menubtn_map"; // 系统菜单按钮

	/**
	 * 设置session的值
	 * 
	 * @param request
	 * @param key
	 * @param value
	 */
	public static void setAttr(HttpServletRequest request, String key,
			Object value) {
		request.getSession(true).setAttribute(key, value);
	}

	/**
	 * 获取session的值
	 * 
	 * @param request
	 * @param key
	 * @param value
	 */
	public static Object getAttr(HttpServletRequest request, String key) {
		return request.getSession(true).getAttribute(key);
	}

	/**
	 * 删除Session值
	 * 
	 * @param request
	 * @param key
	 */
	public static void removeAttr(HttpServletRequest request, String key) {
		request.getSession(true).removeAttribute(key);
	}

	/**
	 * 设置用户在线信息
	 * 
	 * @param request
	 * @param userName
	 * @param sessionId
	 */
	@SuppressWarnings("unchecked")
	public static void setOnlineUser(HttpServletRequest request,
			String userName, String sessionId) {
		Map<String, String> online = (Map<String, String>) request
				.getServletContext().getAttribute(
						CommonConstant.SERVLETCONTEXT_ONLINE_USER);
		if (online == null)
			online = new HashMap<String, String>();
		online.put(userName, sessionId);
	}

	/**
	 * 返回用户在线信息
	 * 
	 * @param request
	 * @param userName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getOnlineUser(HttpServletRequest request,
			String userName) {
		Map<String, String> online = (Map<String, String>) request
				.getServletContext().getAttribute(
						CommonConstant.SERVLETCONTEXT_ONLINE_USER);
		if (online == null)
			return "";
		return online.get(userName);
	}

	/**
	 * 删除用户在线信息
	 * 
	 * @param request
	 * @param userName
	 */
	@SuppressWarnings("unchecked")
	public static void removeOnlineUser(HttpServletRequest request,
			String userName) {
		Map<String, String> online = (Map<String, String>) request
				.getServletContext().getAttribute(
						CommonConstant.SERVLETCONTEXT_ONLINE_USER);
		if (online == null)
			return;
		online.remove(userName);
	}

	/*********************************************************/
	/**
	 * 设置用户信息 到session
	 * 
	 * @param request
	 * @param user
	 */
	public static void setUser(HttpServletRequest request,
			SysMngUserLoginBean user) {
		request.getSession(true).setAttribute(SESSION_USER, user);
	}

	/**
	 * 从session中获取用户信息
	 * 
	 * @param request
	 * @return SysUser
	 */
	public static SysMngUserLoginBean getUser(HttpServletRequest request) {
		return (SysMngUserLoginBean) request.getSession(true).getAttribute(
				SESSION_USER);
	}

	public static SysMngUserLoginBean getUser(HttpSession session) {
		return (SysMngUserLoginBean) session.getAttribute(SESSION_USER);
	}

	/**
	 * 从session中获取用户信息
	 * 
	 * @param request
	 * @return SysUser
	 */
	public static void removeUser(HttpServletRequest request) {
		removeAttr(request, SESSION_USER);
	}

	/*********************************************************/
	/**
	 * 设置验证码 到session
	 * 
	 * @param request
	 * @param user
	 */
	public static void setValidateCode(HttpServletRequest request,
			String validateCode) {
		request.getSession(true).setAttribute(SESSION_VALIDATECODE,
				validateCode);
	}

	/**
	 * 从session中获取验证码
	 * 
	 * @param request
	 * @return SysUser
	 */
	public static String getValidateCode(HttpServletRequest request) {
		return (String) request.getSession(true).getAttribute(
				SESSION_VALIDATECODE);
	}

	/**
	 * 从session中获删除验证码
	 * 
	 * @param request
	 * @return SysUser
	 */
	public static void removeValidateCode(HttpServletRequest request) {
		removeAttr(request, SESSION_VALIDATECODE);
	}

	/**
	 * 判断当前登录用户是否超级管理员
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAdmin(HttpServletRequest request) { // 判断登录用户是否超级管理员
		SysMngUserLoginBean user = getUser(request);
		if ("admin".equalsIgnoreCase(user.getUserName())) {
			return true;
		}
		return false;
	}

	public static boolean isAdmin(HttpSession session) { // 判断登录用户是否超级管理员
		SysMngUserLoginBean user = getUser(session);
		if ("admin".equalsIgnoreCase(user.getUserName())) {
			return true;
		}
		return false;
	}

	/**
	 * 判断当前登录用户是否超级管理员
	 * 
	 * @param request
	 * @return
	 */
	public static void setAccessUrl(HttpServletRequest request,
			List<String> accessUrls) { // 判断登录用户是否超级管理员
		setAttr(request, SESSION_ACCESS_URLS, accessUrls);
	}

	/**
	 * 判断URL是否可访问
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAccessUrl(HttpServletRequest request, String url) {
		List<String> accessUrls = (List) getAttr(request, SESSION_ACCESS_URLS);
		if (accessUrls == null || accessUrls.isEmpty()
				|| !accessUrls.contains(url)) {
			return false;
		}
		return true;
	}

	/**
	 * 设置菜单按钮
	 * 
	 * @param request
	 * @param btnMap
	 */
	public static void setMemuBtnMap(HttpServletRequest request,
			Map<String, List> btnMap) { // 判断登录用户是否超级管理员
		setAttr(request, SESSION_MENUBTN_MAP, btnMap);
	}

	/**
	 * 获取菜单按钮
	 * 
	 * @param request
	 * @param btnMap
	 */
	public static List<String> getMemuBtnListVal(HttpServletRequest request,
			String menuUri) { // 判断登录用户是否超级管理员
		Map btnMap = (Map) getAttr(request, SESSION_MENUBTN_MAP);
		if (btnMap == null || btnMap.isEmpty()) {
			return null;
		}
		return (List<String>) btnMap.get(menuUri);
	}

	// private static final String SESSION_ACCESS_URLS = "session_access_urls";
	// //系统能够访问的URL
	//
	//
	// private static final String SESSION_MENUBTN_MAP = "session_menubtn_map";
	// //系统菜单按钮

}