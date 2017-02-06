package com.mall.web.qr.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mall.common.util.QRcodeUtil;

/**
 * @category 二维码在线生成器
 * 
 */
@Controller
public class QrAction {

	/************************************/
	/**
	 * 生产店铺二维码
	 * 
	 * @throws Exception
	 * 
	 */
	@RequestMapping("/findStoreQr.action")
	public String findStoreQr(String storeId, HttpServletResponse response) {
		try {
			QRcodeUtil.encode("http://m.qqgo.cc/store/store.html?storeId="
					+ storeId, 200, 200, response.getOutputStream());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
