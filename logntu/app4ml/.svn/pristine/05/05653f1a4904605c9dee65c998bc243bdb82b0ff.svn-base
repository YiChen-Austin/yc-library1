package com.mall.web.admin.common.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.mall.web.admin.common.utils.SystemResourceUtil;


public class SystemParamServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3357027362570762993L;
	private static Logger logger = Logger.getLogger(SystemParamServlet.class);

	/**
	 * 加载系统参数
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			super.init();			
			SystemResourceUtil.getInstance().loadSysDictionary();
			SystemResourceUtil.getInstance().loadBusinessDictionary();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("系统加载参数发生异常......");
			throw new ServletException("系统加载参数发生异常");
		}

	}

}
