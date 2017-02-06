package com.mall.web.mall.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.cloopen.rest.sdk.CCPRestSDK;

/**
 * @Description(描述) : 短信发送
 * @author(作者) : wangliyou
 * @date (开发日期) : 2015年12月26日 下午4:58:04
 */
public class HttpSendSms {

	public static Map<String, Object> postSend(String phone, String authCode) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount("8a48b55151d2f2480151d3566ebd01d1",
				"4f86548d93b14672af5e17993a55fa70");// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId("aaf98f8951d38e890151d6d541770502");// 初始化应用ID
		jsonMap = restAPI.sendTemplateSMS(phone, "59537", new String[] {
				authCode, "2" });// 13500386878,13883217101,

		System.out.println("SDKTestSendTemplateSMS result=" + jsonMap + ","
				+ phone + "," + authCode);
		if ("000000".equals(jsonMap.get("statusCode"))) {
			// 正常返回输出data包体信息（map）
			HashMap<String, Object> data = (HashMap<String, Object>) jsonMap
					.get("data");
			Set<String> keySet = data.keySet();
			for (String key : keySet) {
				Object object = data.get(key);
			}
			jsonMap.put("result", true);
		} else {
			// 异常返回输出错误码和错误信息
			System.out.println("错误码=" + jsonMap.get("statusCode") + " 错误信息= "
					+ jsonMap.get("statusMsg"));
			jsonMap.put("result", false);
			jsonMap.put("meg", "发送失败，请联系系统管理员");
		}
		return jsonMap;
	}

	public static boolean postSend4b(String phone, String authCode) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount("8a48b55151d2f2480151d3566ebd01d1",
				"4f86548d93b14672af5e17993a55fa70");// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId("aaf98f8951d38e890151d6d541770502");// 初始化应用ID
		jsonMap = restAPI.sendTemplateSMS(phone, "59537", new String[] {
				authCode, "2" });// 13500386878,13883217101,

		System.out.println("SDKTestSendTemplateSMS result=" + jsonMap + ","
				+ phone + "," + authCode);
		if ("000000".equals(jsonMap.get("statusCode"))) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println(postSend("15922666016", "234556"));
	}
}
