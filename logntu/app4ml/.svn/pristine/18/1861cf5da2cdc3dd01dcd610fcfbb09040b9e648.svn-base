package com.mall.common.geo.coordinate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class Gps2BaiduUtil {
	private final static String ak = "KZCI5e2zTNf6abf1CF0GNd1i";
	private final static String baiduUrl = "http://api.map.baidu.com/geoconv/v1/?from=1&to=5&ak="
			+ ak;

	public static String gps2baiduGet(String[] xys) {
		String coords = "";
		for (String xy : xys) {
			if (coords.length() <= 0)
				coords = xy;
			else {
				coords += ";" + xy;
			}
		}
		// 构造HttpClient的实例
		GetMethod getMethod = null;
		try {
			HttpClient httpClient = new HttpClient();
			// 创建GET方法的实例
			getMethod = new GetMethod(baiduUrl + "&coords=" + coords);
			// 使用系统提供的默认的恢复策略
			// getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
			// new DefaultHttpMethodRetryHandler());

			HttpMethodParams params = new HttpMethodParams();
			params.setContentCharset("utf8");
			params.setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());
			getMethod.setParams(params);

			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);

			if (statusCode == HttpStatus.SC_OK) {
				// 读取内容
				byte[] responseBody = getMethod.getResponseBody();
				String result = new String(responseBody);
				if(result.indexOf("result\":[")<0){
					System.out.println(">>>>>>>>>>>>>>>:"+coords+"\n"+result);
					return "-1";
				}
				// System.out.println(result);
				// 处理内容
				return result.substring(result.indexOf("result\":[") + 8,
						result.indexOf("]}") + 1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 释放连接
			if (getMethod != null)
				getMethod.releaseConnection();
		}
		return "-1";
	}

	public static boolean sendSmByPost(String msdn, String msg)
			throws UnsupportedEncodingException {
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		// 设置 Http 连接超时为5秒
		httpClient.getHttpConnectionManager().getParams()
				.setConnectionTimeout(5000);
		// 创建post方法的实例
		PostMethod postMethod = new PostMethod(
				"http://localhost/sendsms.aspx?phone=" + msdn + "&msg="
						+ URLEncoder.encode(msg, "UTF-8"));

		// 处理中文乱码 dml@2013.1.7
		postMethod.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

		// 设置请求超时为 5 秒
		postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		// 使用系统提供的默认的恢复策略
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());

		try {
			// 执行getMethod
			int statusCode = httpClient.executeMethod(postMethod);

			if (statusCode == HttpStatus.SC_OK) {
				// 读取内容
				byte[] responseBody = postMethod.getResponseBody();
				String result = new String(responseBody);
				System.out.println(result);
				// 处理内容
				// return result.substring(result.indexOf("results:[") + 9,
				// result.indexOf("]}"));
			}
			// 处理内容
		} catch (Exception e) {
			// 发生网络异常
			return false;
		} finally {
			// 释放连接
			postMethod.releaseConnection();
		}
		return true;
	}

	public static void callOrderPost(String orderCode)
			throws UnsupportedEncodingException {
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		// 设置 Http 连接超时为5秒
		httpClient.getHttpConnectionManager().getParams()
				.setConnectionTimeout(5000);
		// 创建post方法的实例
		PostMethod postMethod = new PostMethod(
				"http://localhost/SiteApi/OrderConfirmation?OrderCode="
						+ orderCode);

		// 处理中文乱码 dml@2013.1.7
		postMethod.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

		// 设置请求超时为 5 秒
		postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		// 使用系统提供的默认的恢复策略
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());

		try {
			// 执行getMethod
			int statusCode = httpClient.executeMethod(postMethod);

			if (statusCode == HttpStatus.SC_OK) {
				// 读取内容
				byte[] responseBody = postMethod.getResponseBody();
				String result = new String(responseBody);
				System.out.println(result);
				// 处理内容
				// return result.substring(result.indexOf("results:[") + 9,
				// result.indexOf("]}"));
			}
			// 处理内容
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			e.printStackTrace();
		} finally {
			// 释放连接
			postMethod.releaseConnection();
		}
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		JsonParseUtil<CoordinateVo> jsonUtil = new JsonParseUtil<CoordinateVo>();
		String json = gps2baiduGet(new String[] {
				"106.526986,29.585983",
				"106.548744194444,29.6654968055556",
				"106.548828111111,29.6656207777778" });
		List<CoordinateVo> list = jsonUtil.getList(json, CoordinateVo.class);
		for (CoordinateVo vo : list) {
			System.out.println(vo.getX() + "," + vo.getY());
		}
	}

}
