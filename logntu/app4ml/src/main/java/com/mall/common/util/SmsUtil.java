package com.mall.common.util;
import org.apache.log4j.Logger;

import com.mall.web.mall.common.utils.OldHttpSendSms;

public class SmsUtil {
	private static Logger logger = Logger.getLogger(SmsUtil.class);

//	public final static boolean send(String phone, int type,String validCode) {
//		try {
//			String content = "";
//			switch (type) {
//			case 1:
//				content = String.format("【全球狗】验证码为:%s(全球购客服绝不会索取此验证码,切勿告知他人)请在页面中输入以完成验证.", validCode);
//				break;
//				//手机注册帐号
//			case 2:
//				content = String.format("【全球狗】注册验证码为:%s(全球购客服绝不会索取此验证码,切勿告知他人)请在页面中输入以完成验证.", validCode);
//				break;
//				//手机登录
//			case 3:
//				content = String.format("【全球狗】登录验证码为:%s(全球购客服绝不会索取此验证码,切勿告知他人)请在页面中输入以完成验证.", validCode);
//				break;
//			default:
//				content = String.format("【全球狗】验证码为:%s(全球购客服绝不会索取此验证码,切勿告知他人)请在页面中输入以完成验证.", validCode);
//				break;
//			}
//			OldHttpSendSms.postSendSms(phone, content);
//			return true;
//		} catch (Exception e) {
//			logger.warn(e);
//		}
//		return false;
//	}
}
