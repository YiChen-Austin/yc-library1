package com.mall.web.mall.common.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.mall.web.common.wordfilter.WordFilterUtil;
import com.mall.web.pay.notice.PayNoticUtil;
import com.unionpay.acp.sdk.SDKConfig;

public class SystemParamServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7222930141398351171L;
	private static Logger logger = Logger.getLogger(SystemParamServlet.class);

	/**
	 * 加载系统参数
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			super.init();
			SDKConfig.getConfig().loadPropertiesFromSrc();
			WordFilterUtil.getInstance().loadWordDictionary();
			PayNoticUtil.getInstance().startNoticeServer();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("系统加载参数发生异常......");
			throw new ServletException("系统加载参数发生异常");
		}

	}
}
