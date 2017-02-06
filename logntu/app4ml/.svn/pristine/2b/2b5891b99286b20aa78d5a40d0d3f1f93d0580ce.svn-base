package com.mall.common.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;


/**
 * 请求URL解析IP和城市
 * @author ty
 *
 */
public class UrlUtil {
	
	/**
	 * 获取真实IP
	 * @param request
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest request){
		/*Enumeration<String> en =  request.getHeaderNames();
		while(en.hasMoreElements()) {
			System.out.println(en.nextElement() + "\n");
		}*/
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
	
	/**
	 * 获取城市
	 * @param content
	 * @param encodingStr
	 * @return
	 *//*
	public static String getAddress(String content, String encodingStr) {
		String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
		String returnStr = UrlUtil.getResult(urlStr, content, encodingStr);
		if(returnStr != null) {
			System.out.println(returnStr);
			JSONObject json = JSONObject.fromObject(returnStr);
			String code = json.getString("code");
			if(code.equals("0")) {
				String data = json.getString("data");
				JSONObject jsonData = JSONObject.fromObject(data);
				//String country = jsonData.getString("country");
				//String area = jsonData.getString("area");
				//String region = jsonData.getString("region");
				String city = jsonData.getString("city");
				//String county = jsonData.getString("county");
				if(city.indexOf("市") > -1) {
					city = city.substring(0, city.indexOf("市"));
				}
				return city;
			} 
		}
		return null;
	}
	
	*//**
	 * 通过网络连接获取请求结果
	 * @param urlStr
	 * @param content
	 * @param encodingStr
	 * @return
	 *//*
	private static String getResult(String urlStr, String content, String encodingStr) {
		URL url = null;
		HttpURLConnection connection = null;
		try{
			url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(3000);
			connection.setReadTimeout(3000);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.connect();
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(content);
			out.flush();
			out.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), encodingStr));
			StringBuffer buffer = new StringBuffer();
			String line = null;
			while((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(connection != null) {
				connection.disconnect();
			}
		}
		return null;
	}*/

}
