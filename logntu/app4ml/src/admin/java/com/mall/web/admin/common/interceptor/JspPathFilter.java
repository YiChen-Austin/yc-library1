/**
 * 
 */
package com.mall.web.admin.common.interceptor;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.mall.web.admin.common.utils.SessionUtils;

/**
 * 功能说明:过滤未登录用户直接通过走jsp页面访问资源
 * 
 * @作者 赵欢欢
 * 
 *     创建日期2010-6-9
 */

public class JspPathFilter implements Filter {
	@SuppressWarnings("unused")
	private FilterConfig filterConfig = null;

	public void init(FilterConfig config) throws ServletException {
		this.filterConfig = config;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException,
			IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getServletPath();
		String igpath = path.substring(path.lastIndexOf("/") + 1);
		//如果用户未登录，通过在IE地址栏走login.jsp或者register.jsp的页面可以直接访问资源，否则就进行拦截
		if (igpath.equalsIgnoreCase("login.jsp")
				|| igpath.equalsIgnoreCase("register.jsp")) {
			chain.doFilter(request, response);
		} else {			
			//如果用户已经登录，则用户可以在同一个IE浏览器通过url来访问资源，否则直接进入登录页面
			if (SessionUtils.getUser(req)!= null) {
				chain.doFilter(request, response);
			} else {
				String url = "<script language='javascript'>window.top.location.href='"
						+ req.getContextPath() + "/admin/index'</script>";
				Writer writer = response.getWriter();
				writer.write(url);
				writer.flush();
				writer.close();
			}
		}
	}

	public void destroy() {
		this.filterConfig = null;
	}
}
