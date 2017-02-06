package com.mall.web.pay.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mall.common.util.QRcodeUtil;
import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * @category 二维码在线生成器
 * 
 */
@Controller("payAction")
public class QrAction {
	private Logger logger = Logger.getLogger(this.getClass());

	/************************************/
	/**
	 * 生产微信二维码
	 * 
	 * @throws Exception
	 * 
	 */
	@RequestMapping("/findPayQr")
	public String findStoreQr(HttpServletResponse response,String code) {
		try {
			QRcodeUtil.encode(new String(Base64.decode(code)), 298, 298,
					response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
		}
		return null;
	}
}