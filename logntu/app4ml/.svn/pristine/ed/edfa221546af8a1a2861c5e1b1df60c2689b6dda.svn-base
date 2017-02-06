package com.mall.web.mall.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;

/**
 * @Description(描述) : 短信发送(老版本发送验证码，已弃用！)
 * @author(作者) : wangliyou
 * @date (开发日期) : 2015年10月17日 下午4:09:16
 */
public class OldHttpSendSms {
	// 以下为所需的参数，测试时请修改,中文请先转为16进制再发送
	static String strReg = "101100-WEB-HUAX-888876"; // 注册号（由华兴软通提供）
	static String strPwd = "DMXTMLMW"; // 密码（由华兴软通提供）
	static String strSourceAdd = ""; // 子通道号，可为空（预留参数一般为空）
	static String strSmsUrl = "http://www.stongnet.com/sdkhttp/sendsms.aspx";
	static String strSmsParam = "reg=" + strReg + "&pwd=" + strPwd
			+ "&sourceadd=" + strSourceAdd;

//	public static void main(String[] args) {
//		System.out.println(postSend("15025388727", "4541"));;
//	}
//	/**
//	 * @Description(功能描述) : 短信发送
//	 * @author(作者) : wangliyou
//	 * @date (开发日期) : 2015年10月17日 下午4:07:48
//	 * @param phone  : 手机号码
//	 * @param authCode : 验证码
//	 * @return
//	 */
//	public static String postSend(String phone, String authCode) {
//		URL url = null;
//		HttpURLConnection connection = null;
//		try {
//			url = new URL(strSmsUrl);
//			connection = (HttpURLConnection) url.openConnection();
//			connection.setDoOutput(true);
//			connection.setDoInput(true);
//			connection.setRequestMethod("POST");
//			connection.setUseCaches(false);
//			connection.connect();
//			// POST方法时使用
//			DataOutputStream out = new DataOutputStream(
//					connection.getOutputStream());
//			strSmsParam = strSmsParam
//					+ "&phone="
//					+ phone
//					+ "&content="
//					+ OldHttpSendSms.paraTo16("【全球狗】你收到的验证码为：" + authCode
//							+ ",如需帮助请联系客服");
//			out.writeBytes(strSmsParam);
//			out.flush();
//			out.close();
//
//			BufferedReader reader = new BufferedReader(new InputStreamReader(
//					connection.getInputStream(), "utf-8"));
//			StringBuffer buffer = new StringBuffer();
//			String line = "";
//			while ((line = reader.readLine()) != null) {
//				buffer.append(line);
//			}
//			reader.close();
//			return buffer.toString();
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		} finally {
//			if (connection != null) {
//				connection.disconnect();
//			}
//		}
//	}
//
//	/**
//	 * @Description(功能描述) : 短信发送
//	 * @author(作者) : wangliyou
//	 * @date (开发日期) : 2015年10月17日 下午4:07:48
//	 * @param phone
//	 *            : 手机号码
//	 * @param authCode
//	 *            : 验证码
//	 * @return
//	 */
//	public static String postSendSms(String phone, String sms) {
//		URL url = null;
//		HttpURLConnection connection = null;
//		try {
//			url = new URL(strSmsUrl);
//			connection = (HttpURLConnection) url.openConnection();
//			connection.setDoOutput(true);
//			connection.setDoInput(true);
//			connection.setRequestMethod("POST");
//			connection.setUseCaches(false);
//			connection.connect();
//			// POST方法时使用
//			DataOutputStream out = new DataOutputStream(
//					connection.getOutputStream());
//			strSmsParam = strSmsParam + "&phone=" + phone + "&content="
//					+ OldHttpSendSms.paraTo16(sms);
//			out.writeBytes(strSmsParam);
//			out.flush();
//			out.close();
//
//			BufferedReader reader = new BufferedReader(new InputStreamReader(
//					connection.getInputStream(), "utf-8"));
//			StringBuffer buffer = new StringBuffer();
//			String line = "";
//			while ((line = reader.readLine()) != null) {
//				buffer.append(line);
//			}
//			reader.close();
//			return buffer.toString();
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		} finally {
//			if (connection != null) {
//				connection.disconnect();
//			}
//		}
//	}
//
//	/**
//	 * 转为16进制方法
//	 * 
//	 * @param str
//	 * @return
//	 * @throws UnsupportedEncodingException
//	 */
//	public static String paraTo16(String str) {
//		try {
//			String hs = "";
//			byte[] byStr = str.getBytes("UTF-8");
//			for (int i = 0; i < byStr.length; i++) {
//				String temp = "";
//				temp = (Integer.toHexString(byStr[i] & 0xFF));
//				if (temp.length() == 1)
//					temp = "%0" + temp;
//				else
//					temp = "%" + temp;
//				hs = hs + temp;
//			}
//			return hs.toUpperCase();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		return "";
//	}
//
//	public static String getSend(String strUrl, String param) {
//		URL url = null;
//		HttpURLConnection connection = null;
//
//		try {
//			url = new URL(strUrl + "?" + param);
//			connection = (HttpURLConnection) url.openConnection();
//			connection.setDoOutput(true);
//			connection.setDoInput(true);
//			connection.setRequestMethod("GET");
//			connection.setUseCaches(false);
//			connection.connect();
//
//			BufferedReader reader = new BufferedReader(new InputStreamReader(
//					connection.getInputStream(), "utf-8"));
//			StringBuffer buffer = new StringBuffer();
//			String line = "";
//			while ((line = reader.readLine()) != null) {
//				buffer.append(line);
//			}
//
//			reader.close();
//			return buffer.toString();
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		} finally {
//			if (connection != null) {
//				connection.disconnect();
//			}
//		}
//	}
}
