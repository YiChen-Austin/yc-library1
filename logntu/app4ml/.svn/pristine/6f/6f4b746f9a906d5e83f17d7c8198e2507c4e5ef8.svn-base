package com.mall.web.mobile.third.util.alipay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import com.mall.common.util.DateUtil;

public class Alipay4appConf {
	// 商户PID
	// public static final String PARTNER = "2088811092094084";
	// // 商户收款账号
	// public static final String SELLER = "yctwlt@qq.com";
	// 商户私钥，pkcs8格式
	public static final String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALX/laShfKKQaf+NCb8pShbUNne4XewxsQfiMSKs7zGmbMweHg3iUDzIt7I2g/t1hq051MSz8ysC2BZAykh30DyMLr9GBx5oaiJwDueVbYc9H8Si7kUxsZheFY38iKxeeiZ2FvGHGVVWqm2yu8MbZVeQzowPmC+LDMWLImEeltfTAgMBAAECgYBrRLgLb47JNfrzv0oGXKypZeywhX7+Lo+cCOpS+MCGcAPiDsJCBqiqGteCd3U06aE2SkaopTNmn2eDPi67pbqaZkU0f3sDEopOyqijtsIVFdT26/oCD0/LFqyX+ItceWvVyKXag/SMA3bTROwqOIDAkpUponXFgDQPBI9nRsfaaQJBAOXtgWllkjGlfU1/1cXPgtrNDsXff/YQ91YS2fAkfw1RvtqXJubSdlxKmkw6gcDcAdVUAg7XMz8H6wcN6JJaY8cCQQDKosDgSTnj5vJdhwPDxMaVU/HbHcZbg2Yuxt9pQORju42xQMUG8V63Vp2G+Ff7az7fNY8JJD0zOntT7bAa1ROVAkEAyBtNTrosYvLhO6qvFQpFm0Ftwv6B8ljPueSMMjjBLolfbgSwwaCXjDkNUpx0wrF3Ev+bDulx20B75tbO+1iZjwJBAL0JlmvmhEYTRplwAlgY87WNaXyjy0/GRoZu6y8S5b4Q0z/AD87JUDYzbKPkfJfFVhL/sR+zGhD3huQmQnrVuv0CQQDdoWZjgE7aNfpMNOEwJ6ogTjbaGi3BqPS0nFGJ/DNGPQzowQWAq1JFPXTyZWy3Ibn0KKdN7Ixqo+gEluclY6Yp";
	// 支付宝公钥
	public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1/5WkoXyikGn/jQm/KUoW1DZ3uF3sMbEH4jEirO8xpmzMHh4N4lA8yLeyNoP7dYatOdTEs/MrAtgWQMpId9A8jC6/RgceaGoicA7nlW2HPR/Eou5FMbGYXhWN/IisXnomdhbxhxlVVqptsrvDG2VXkM6MD5gviwzFiyJhHpbX0wIDAQAB";
	public static final int SDK_PAY_FLAG = 1;

	public static final int SDK_CHECK_FLAG = 2;

	/**
	 * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
	 * 
	 */
	public static String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss",
				Locale.getDefault());
		Date date = DateUtil.getGMTDate();
		String key = format.format(date);

		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);
		return key;
	}

	/**
	 * sign the order info. 对订单信息进行签名
	 * 
	 * @param content
	 *            待签名订单信息
	 */
	public static String sign(String content) {
		return SignUtils.sign(content, RSA_PRIVATE);
	}

	/**
	 * get the sign type we use. 获取签名方式
	 * 
	 */
	public static String getSignType() {
		return "sign_type=\"RSA\"";
	}

}
