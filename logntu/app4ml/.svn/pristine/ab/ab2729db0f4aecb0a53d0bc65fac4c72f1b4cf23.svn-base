package com.mall.common.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;

/**
 * NetUtil
 * @author sol
 */
public class NetUtil
{
	private static Logger logger = Logger.getLogger(NetUtil.class);
	
	/**
	 * 获取远程 HTML 片段，并返回其文本形式。
	 * 
	 * @param url		远程地址。
	 * @param charset	文本编码。特殊地，若 charset == null 或 ""，则使用默认值 "utf-8"。
	 * @return	远程地址响应的文本形式
	 */
	public static String getRemoteDom(String url, String charset)
	{
		if (charset == null || charset.isEmpty()) {
			charset = "utf-8";
		}
		
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(url);
		method.addRequestHeader("Content-Type", "text/html; charset=" + charset.toUpperCase());
		try {
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("getRemoteDom(" + url + ") failed: " + method.getStatusLine());
				return "";
			}
			byte[] respBody = method.getResponseBody();
			return new String(respBody, charset);
		}
		catch (Exception e) {
			logger.warn(e);
			e.printStackTrace();
		}
		return "";
	}	
}
