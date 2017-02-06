package com.mall.web.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mall.common.util.EtlSimulatUtil;


public class IpSearchUtil {
	public static String[] searchIp138(String ip) {
		// 构造HttpClient的实例
		GetMethod getMethod = null;
		HttpClient httpClient = new HttpClient();
		try {
			// 创建GET方法的实例
			getMethod = EtlSimulatUtil.getMethodComm(
					(int) (System.currentTimeMillis() % 5),
					System.currentTimeMillis(),
					(int) (System.currentTimeMillis() % 100),
					"http://www.ip138.com/ips1388.asp?ip=" + ip + "&action=2");
			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != 200)
				return null;
			byte[] responseBody = getMethod.getResponseBody();
			Header hd = getMethod.getResponseHeader("Content-Encoding");
			String gzip = (hd == null ? "" : hd.getValue());
			ByteArrayInputStream in = new ByteArrayInputStream(responseBody);

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buffer = new byte[256];
			int n;
			if ("gzip".equalsIgnoreCase(gzip)) {
				GZIPInputStream gunzip = new GZIPInputStream(in);
				while ((n = gunzip.read(buffer)) >= 0) {
					out.write(buffer, 0, n);
				}
			} else {
				while ((n = in.read(buffer)) >= 0) {
					out.write(buffer, 0, n);
				}
			}
			String result = new String(out.toByteArray(), "gbk");
			// 解析内容
			Document doc = Jsoup.parse(result);
			if (doc == null)
				return null;
			Elements body = doc.getElementsByTag("BODY");
			if (body == null || body.size() <= 0)
				return null;
			Elements tables = body.get(0).getElementsByTag("table");
			if (tables == null || tables.size() <= 2)
				return null;
			Element table = tables.get(2);
			String html = table.html();

			int pos = html.indexOf("本站主数据：") + 6;
			if (pos < 0)
				return null;
			int next = html.indexOf("</li>", pos);
			if (next < 0)
				return null;
			String value = html.substring(pos, next);
			pos = value.indexOf(" ");
			if (next < 0)
				return null;
			value = value.substring(0, pos);
			String[] v = value.split("(省|自治区|市|地区|州|县)");
			return v;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// 释放连接
			if (getMethod != null) {
				getMethod.releaseConnection();
			}
		}
	}

	public static void main(String[] args) throws UnknownHostException,
			SocketException {
		String[] v = searchIp138("124.31.217.164");
		for (String s : v)
			System.out.println(s);
	}
}
