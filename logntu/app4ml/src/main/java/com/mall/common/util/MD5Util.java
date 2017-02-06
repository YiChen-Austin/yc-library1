package com.mall.common.util;

import java.security.MessageDigest;

public class MD5Util {
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		return MD5(hexDigits, s);
	}

	public final static String MD5(char hexDigits[], String s) {
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	public final static String mallMD5(String src) throws Exception {
		String encode = "131380105053ce36ee83df3518296292";
		// byte[] btInput = (src + encode).getBytes();
		// // 获得MD5摘要算法的 MessageDigest 对象
		// MessageDigest mdInst = MessageDigest.getInstance("MD5");
		// // 使用指定的字节更新摘要
		// mdInst.update(btInput);
		// // 获得密文
		// byte[] md = mdInst.digest();
		return mallMD5(src, encode);
	}

	public final static String mallMD5(String src, String encode)
			throws Exception {
		byte[] btInput = (src + encode).getBytes();
		// 获得MD5摘要算法的 MessageDigest 对象
		MessageDigest mdInst = MessageDigest.getInstance("MD5");
		// 使用指定的字节更新摘要
		mdInst.update(btInput);
		// 获得密文
		byte[] md = mdInst.digest();
		return byteArr2HexStr(md);
	}
	public final static String mallMD5(String usr,String pwd, String encode)
			throws Exception {
		byte[] btInput = (usr +":"+pwd+":"+ encode).getBytes();
		// 获得MD5摘要算法的 MessageDigest 对象
		MessageDigest mdInst = MessageDigest.getInstance("MD5");
		// 使用指定的字节更新摘要
		mdInst.update(btInput);
		// 获得密文
		byte[] md = mdInst.digest();
		return byteArr2HexStr(md);
	}
	public static void main(String[] args) throws Exception {
		// System.out
		// .println(MD5Util
		// .MD5("2012122120121221201212212012122120121221201212212012122120121221"));
		// System.out.println(MD5Util.MD5("加密"));
		System.out.println(MD5Util.MD5("wzbbtxc01"));
		System.out.println(MD5Util.MD5("wzbbzst02"));
		System.out.println(MD5Util.MD5("wzdztwd03"));
		System.out.println(MD5Util.MD5("wzycdnm04"));
		System.out.println(MD5Util.MD5("wzycxjlt5"));
		System.out.println(MD5Util.MD5("wzrctwd06"));
		System.out.println(MD5Util.MD5("wzrcxhd07"));
		System.out.println(MD5Util.MD5("wzrcwc008"));
		System.out.println(MD5Util.MD5("wzyzdwd09"));
		System.out.println(MD5Util.MD5("wzyzyld10"));
		System.out.println(MD5Util.mallMD5("1464934216823,0.01|0.00"));
	}
}
