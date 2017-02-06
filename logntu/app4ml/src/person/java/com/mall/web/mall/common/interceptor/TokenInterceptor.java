package com.mall.web.mall.common.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mall.web.mall.common.annotation.Token;


/**
 * 
 * @功能：令牌拦截
 * @作者：ventrue
 * @创建日期：2015-05-20
 * 
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			Token annotation = method.getAnnotation(Token.class);
			String methodStr = request.getMethod();
			if (annotation != null) {
				if (annotation.check() == false) {
					return true;
				}
				// get方式，不做重复判断,尽管生成token
				if ("get".equalsIgnoreCase(methodStr)) {
					boolean needSaveSession = annotation.create();
					if (needSaveSession) {
						request.getSession(false).setAttribute("token",
								UUID.randomUUID().toString());
					}
				}// post方式，先判断,后生成
				else if ("post".equalsIgnoreCase(methodStr)) {
					if (isRepeatSubmit(request)) {
						return false;
					}
					boolean needSaveSession = annotation.create();
					if (needSaveSession) {
						request.getSession(false).setAttribute("token",
								UUID.randomUUID().toString());
					}
				}
				// boolean needRemoveSession = annotation.remove();
				// if (needRemoveSession) {
				// if (isRepeatSubmit(request)) {
				// return false;
				// }
				// request.getSession(false).removeAttribute("token");
				// }
			}
			return true;
		} else {
			return super.preHandle(request, response, handler);
		}
	}

	private boolean isRepeatSubmit(HttpServletRequest request) {
		String serverToken = (String) request.getSession(false).getAttribute(
				"token");
		if (serverToken == null) {
			return true;
		}
		String clinetToken = request.getParameter("token");
		if (clinetToken == null) {
			return true;
		}
		if (!serverToken.equals(clinetToken)) {
			return true;
		}
		return false;
	}
}
