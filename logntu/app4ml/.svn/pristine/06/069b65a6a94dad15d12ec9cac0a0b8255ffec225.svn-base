package com.mall.web.admin.common.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mall.web.admin.common.utils.BaseAction;
import com.mall.web.admin.security.service.SysAuthorizeService;
import com.mall.web.admin.security.service.SysResourceService;

/**
 * @功能说明：在应用服务器启动时初始注入资源
 * @作者： 印鲜刚
 * @创建日期： 2010-4-30
 */
public class PriAnnotationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(PriAnnotationServlet.class);
	private ApplicationContext context;

	/**
	 * 加载系统参数
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			context = WebApplicationContextUtils
					.getWebApplicationContext(config.getServletContext());
			String[] dNames = context.getBeanNamesForType(BaseAction.class);
			for (String name : dNames) {
				Object bean = context.getBean(name);
				if (!(bean instanceof BaseAction)) {
					continue;
				}
				doResourceClass(((BaseAction) bean).getClass());
			}
		} catch (Exception e) {
			logger.info("初始数据注入系统发生异常......");
			throw new ServletException("初始数据注入系统发生异常");
		}
	}

	private void doResourceClass(Class<? extends BaseAction> _class) {
		// 1、（放置于变量上的）处理承载页面及组件
		Field[] fields = _class.getDeclaredFields();
		for (Field field : fields) {
			// 处理组件承载页面
			ResourcePage resourcePage = field.getAnnotation(ResourcePage.class);
			if (resourcePage != null) {
				doResourcePage(resourcePage);
			}
			// 处理组件
			ResourceButton resourceButton = field
					.getAnnotation(ResourceButton.class);
			if (resourceButton != null) {
				doResourceButton(resourceButton);
			}
		}
		// 2、（放置于方法上的）处理承载action链接
		String url = "";
		String tUrl = "";
		RequestMapping requestMapping = _class
				.getAnnotation(RequestMapping.class);
		if (requestMapping != null && requestMapping.value().length > 0) {
			url = requestMapping.value()[0];
		}
		Method[] methods = _class.getDeclaredMethods();
		for (Method method : methods) {
			Auth auth = method.getAnnotation(Auth.class);
			if (auth != null && auth.verifyURL() == false)
				continue;
			ResourceUrl resource = method.getAnnotation(ResourceUrl.class);
			if (resource == null)
				continue;
			requestMapping = method.getAnnotation(RequestMapping.class);
			tUrl = url;
			if (requestMapping != null && requestMapping.value().length > 0)
				tUrl += requestMapping.value()[0];
			doResourceUrl(resource, tUrl);
		}
	}

	private void doResourcePage(ResourcePage resource) {
		SysResourceService resourceService = (SysResourceService) context
				.getBean("sysResourceService");
		try {
			resourceService.initResoucePage(resource.menuPid(),
					resource.menuUrl(), resource.description(),
					resource.jspUrl());
		} catch (Exception e) {
			logger.warn("doResourcePage", e);
		}
	}

	private void doResourceButton(ResourceButton resource) {
		SysResourceService resourceService = (SysResourceService) context
				.getBean("sysResourceService");
		try {
			resourceService.initResouceButton(resource.resourceId(),
					resource.resourceName(), resource.visiles(),
					resource.jspUrl());
		} catch (Exception e) {
			logger.warn("doResourceButton", e);
		}
	}

	private void doResourceUrl(ResourceUrl resource, String url) {
		SysAuthorizeService sysAuthorizeService = (SysAuthorizeService) context
				.getBean("sysAuthorizeService");
		try {
			sysAuthorizeService.initAuthorizeUrl(resource.resourceKey(), url,
					resource.description(), resource.remark());
		} catch (Exception e) {
			logger.warn("doResourceButton", e);
		}
	}
}
