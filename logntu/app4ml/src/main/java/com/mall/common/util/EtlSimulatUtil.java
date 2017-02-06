package com.mall.common.util;

import org.apache.commons.httpclient.methods.GetMethod;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EtlSimulatUtil {
	public static String Url = "http://www.dianping.com";
	public static String Host = "www.dianping.com";
	public static String SimulatorType = "tp_link_000";
	public static String userName = "admin";
	public static String password = "admin";

	private static GetMethod getFirefoxMethod(long rand, int seq, String host,
			String url) {
		GetMethod getMethod = new GetMethod(url);
		getMethod.setRequestHeader("Host", host);
		getMethod.setRequestHeader("Connection", "keep-alive");
		getMethod.setRequestHeader("Cache-Control", "max-age=0");
		getMethod
				.setRequestHeader("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		getMethod
				.setRequestHeader("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:31.0) Gecko/20100101 Firefox/31.0");
		getMethod.setRequestHeader("Accept-Encoding", "gzip, deflate");
		getMethod.setRequestHeader("Accept-Language",
				"zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
//		getMethod.setRequestHeader("Cookie", "JSESSIONID="
//				+ EtlDataUtil.LocalMac + "79C3A6B25E0CFF7" + seq + "01" + rand);
		return getMethod;
	}

	private static GetMethod getGoogleMethod(long rand, int seq, String host,
			String url) {
		GetMethod getMethod = new GetMethod(url);
		getMethod.setRequestHeader("Host", host);
		getMethod.setRequestHeader("Connection", "keep-alive");
		getMethod.setRequestHeader("Cache-Control", "max-age=0");
		getMethod
				.setRequestHeader("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		getMethod
				.setRequestHeader(
						"User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.124 Safari/537.36");
		getMethod.setRequestHeader("Accept-Encoding", "gzip,deflate,sdch");
		getMethod
				.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
//		getMethod.setRequestHeader("Cookie", "JSESSIONID="
//				+ EtlDataUtil.LocalMac + "69C3A6B25E0CFF" + seq + "02" + rand);
		return getMethod;
	}

	private static GetMethod get360Method(long rand, int seq, String host,
			String url) {
		GetMethod getMethod = new GetMethod(url);
		getMethod.setRequestHeader("Host", host);
		getMethod.setRequestHeader("Connection", "keep-alive");
		getMethod
				.setRequestHeader("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		getMethod
				.setRequestHeader(
						"User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
		getMethod.setRequestHeader("Accept-Encoding", "gzip,deflate,sdch");
		getMethod.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8");
//		getMethod.setRequestHeader("Cookie", "JSESSIONID="
//				+ EtlDataUtil.LocalMac + "59C3A6B25E0CFF" + seq + "03" + rand);
		return getMethod;
	}

	private static GetMethod getIe9Method(long rand, int seq, String host,
			String url) {
		GetMethod getMethod = new GetMethod(url);
		getMethod.setRequestHeader("Host", host);
		getMethod.setRequestHeader("Connection", "Keep-Alive");
		getMethod
				.setRequestHeader(
						"Accept",
						"application/x-ms-application, image/jpeg, application/xaml+xml, image/gif, image/pjpeg, application/x-ms-xbap, */*");
		getMethod
				.setRequestHeader(
						"User-Agent",
						"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; Win64; x64; Trident/5.0; .NET CLR 2.0.50727; SLCC2; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; InfoPath.3)");
		getMethod.setRequestHeader("UA-CPU", "AMD64");
		getMethod.setRequestHeader("Accept-Encoding", "gzip, deflate");
		getMethod.setRequestHeader("Accept-Language", "zh-CN");
//		getMethod.setRequestHeader("Cookie", "JSESSIONID="
//				+ EtlDataUtil.LocalMac + "49C3A6B25E0CFF" + seq + "04" + rand);
		return getMethod;
	}

	private static GetMethod getIe11Method(long rand, int seq, String host,
			String url) {
		GetMethod getMethod = new GetMethod(url);
		getMethod.setRequestHeader("Host", host);
		getMethod.setRequestHeader("Connection", "Keep-Alive");
		getMethod.setRequestHeader("Accept",
				"text/html, application/xhtml+xml, */*");
		getMethod
				.setRequestHeader("User-Agent",
						"Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko");
		getMethod.setRequestHeader("Accept-Encoding", "gzip, deflate");
		getMethod.setRequestHeader("Accept-Language",
				"zh-Hans-CN,zh-Hans;q=0.5");
//		getMethod.setRequestHeader("Cookie", "JSESSIONID="
//				+ EtlDataUtil.LocalMac + "39C3A6B25E0CF" + seq + "05" + rand);
		return getMethod;
	}

	public static GetMethod getMethod(int type, long rand, int seq, String url) {
		switch (type) {
		case 0:
			return getFirefoxMethod(rand, seq, Host, Url + url);
		case 1:
			return getGoogleMethod(rand, seq, Host, Url + url);
		case 2:
			return get360Method(rand, seq, Host, Url + url);
		case 3:
			return getIe9Method(rand, seq, Host, Url + url);
		case 4:
			return getIe11Method(rand, seq, Host, Url + url);
		default:
			return getFirefoxMethod(rand, seq, Host, Url + url);
		}
	}

	public static GetMethod getMethodPic(int type, long rand, int seq,
			String url) {
		String host = url.substring(7, url.indexOf("/", 8));
		switch (type) {
		case 0:
			return getFirefoxMethod(rand, seq, host, url);
		case 1:
			return getGoogleMethod(rand, seq, host, url);
		case 2:
			return get360Method(rand, seq, host, url);
		case 3:
			return getIe9Method(rand, seq, host, url);
		case 4:
			return getIe11Method(rand, seq, host, url);
		default:
			return getFirefoxMethod(rand, seq, host, url);
		}
	}

	public static GetMethod getMethodComm(int type, long rand, int seq,
			String url) {
		String host = url.substring(7, url.indexOf("/", 8));
		switch (type) {
		case 0:
			return getFirefoxMethod(rand, seq, host, url);
		case 1:
			return getGoogleMethod(rand, seq, host, url);
		case 2:
			return get360Method(rand, seq, host, url);
		case 3:
			return getIe9Method(rand, seq, host, url);
		case 4:
			return getIe11Method(rand, seq, host, url);
		default:
			return getFirefoxMethod(rand, seq, host, url);
		}
	}

	/************************************/
	public static String jsBase64Encode(String psstrs) {
		double iLen = psstrs.length();
		String map1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
		double oDataLen = (iLen * 4 + 2) / 3;
		String out = "";
		int ip = 0;
		int op = 0;
		while (ip < iLen) {
			char xx = psstrs.charAt(ip++);
			int yy = ip < iLen ? psstrs.codePointAt(ip++) : 0;
			int zz = ip < iLen ? psstrs.codePointAt(ip++) : 0;
			int aa = xx >>> 2;
			int bb = ((xx & 3) << 4) | (yy >>> 4);
			int cc = ((yy & 0xf) << 2) | (zz >>> 6);
			int dd = zz & 0x3F;
			out += map1.charAt(aa);
			op++;
			out += map1.charAt(bb);
			op++;
			out += op < oDataLen ? map1.charAt(cc) : '=';
			op++;
			out += op < oDataLen ? map1.charAt(dd) : '=';
			op++;
		}
		return out;
	}


	public static void main(String[] para) {
		System.out.println(jsBase64Encode("q112358"));
		String s = new BASE64Encoder().encode("q112358".getBytes());
		System.out.println(s);
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b = null;
		String result = null;
		try {
			b = decoder.decodeBuffer("cTExMjM1OA==");
			result = new String(b, "utf-8");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
