package com.mall.common.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求参数安全解析。
 * @author sol
 */
public class ReqParams
{
	public static Integer getInt(HttpServletRequest request, String paramName)
	{
		String p = request.getParameter(paramName);
		return p != null ? Integer.parseInt(p) : null;
	}
	
	public static Long getLong(HttpServletRequest request, String paramName)
	{
		String p = request.getParameter(paramName);
		return p != null ? Long.parseLong(p) : null;
	}
	
	public static Boolean getBool(HttpServletRequest request, String paramName)
	{
		String p = request.getParameter(paramName);
		return "1".equals(p) || "true".equals(p);
	}
	
	public static String getString(HttpServletRequest request, String paramName)
	{
		return request.getParameter(paramName);
	}
	
	public static String[] getStringList(HttpServletRequest request, String paramName)
	{
		String[] values = request.getParameterValues(paramName);
		return values;
	}
}
