package com.mall.web.mall.common.webinit;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.IntrospectorCleanupListener;
import org.springframework.web.util.Log4jConfigListener;

import com.mall.web.mall.common.servlet.SystemParamServlet;


public class MallWebInitializer /*implements WebApplicationInitializer*/ {

	//@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		FilterRegistration.Dynamic filterRegistration = null;
		ServletRegistration.Dynamic dynamic = null;
		// display-name
		servletContext.setAttribute("display-name", "Mall System");
		// contextConfigLocation
		servletContext
				.setInitParameter(
						"contextConfigLocation",
						"classpath:mall/spring/applicationContext.xml,classpath:mall/spring/spring-hibernate.xml,classpath:mall/spring/spring-cas.xml,classpath:mall/spring/spring-elasticsearch.xml,classpath:mall/spring/spring-quartz.xml");
		servletContext.addListener(ContextLoaderListener.class);

		// Log4jConfigListener
		servletContext.setInitParameter("log4jConfigLocation",
				"classpath:mall/properties/log4j.properties");
		servletContext.addListener(Log4jConfigListener.class);
		// <!-- spring security start-->
		// <!-- spring security 过滤器授权代理，会产生一个过滤器链，来处理security中的过滤器
		// 命名一定要是springSecurityFilterChain -->
		DelegatingFilterProxy springSecurityFilterChain = new DelegatingFilterProxy();
		filterRegistration = servletContext.addFilter(
				"springSecurityFilterChain", springSecurityFilterChain);
		filterRegistration.addMappingForUrlPatterns(EnumSet.of(
				DispatcherType.REQUEST, DispatcherType.FORWARD,
				DispatcherType.INCLUDE), false, "/");
		// <!-- 此监听器会在session创建和销毁的时候通知Spring Security如果要限制用户登录个数，这里就要配置.-->
		servletContext.addListener(HttpSessionEventPublisher.class);
		// <!-- spring security end -->

		// MallServlet
		DispatcherServlet mallServlet = new DispatcherServlet();
		mallServlet
				.setContextConfigLocation("classpath:mall/servlet/mall-servlet.xml");
		dynamic = servletContext.addServlet("mall", mallServlet);
		dynamic.setLoadOnStartup(2);
		dynamic.addMapping("/");

		// SystemParamServlet
		SystemParamServlet systemParamServlet = new SystemParamServlet();
		dynamic = servletContext.addServlet("systemParamServlet",
				systemParamServlet);
		dynamic.setLoadOnStartup(9);

		// CharacterEncodingFilter
		CharacterEncodingFilter encoding = new CharacterEncodingFilter();
		encoding.setEncoding("UTF-8");
		encoding.setForceEncoding(true);
		filterRegistration = servletContext.addFilter("encoding", encoding);
		filterRegistration.addMappingForUrlPatterns(EnumSet.of(
				DispatcherType.REQUEST, DispatcherType.FORWARD,
				DispatcherType.INCLUDE), false, "/");

		// OpenSessionInViewFilter
		OpenSessionInViewFilter hibernateSessionInViewFilter = new OpenSessionInViewFilter();
		filterRegistration = servletContext.addFilter("hibernateFilter",
				hibernateSessionInViewFilter);
		filterRegistration.addMappingForUrlPatterns(EnumSet.of(
				DispatcherType.REQUEST, DispatcherType.FORWARD,
				DispatcherType.INCLUDE), false, "/");

		servletContext.addListener(IntrospectorCleanupListener.class);
	}

}