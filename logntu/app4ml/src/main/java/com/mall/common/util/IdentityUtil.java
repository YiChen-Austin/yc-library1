package com.mall.common.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Random;

/**
 * 身份证帮助类
 * 
 * @author ventrue
 * @since 2016/10/13
 */
public class IdentityUtil {
	// 位权值数组
	//public static byte[] Wi = new byte[17];
	// 身份证前部分字符数
	public static final byte fPart = 6;
	// 身份证算法求模关键值
	public static final byte fMod = 11;
	// 旧身份证长度
	public static final byte oldIDLen = 15;
	// 新身份证长度
	public static final byte newIDLen = 18;
	// 新身份证年份标志
	public static final String yearFlag = "19";
	// 校验码串
	public static final String CheckCode = "10X98765432";
	// 最小的行政区划码
	public static final int minCode = 150000;
	// 最大的行政区划码
	public static final int maxCode = 700000;
	// 旧身份证号码
	// public String oldIDCard="";
	// 新身份证号码
	// public String newIDCard="";
	// 地区及编码

	// public String Area[][2] =
	public static byte[] getWiBuffer() {
		byte[] Wi = new byte[17];
		for (int i = 0; i < Wi.length; i++) {
			int k = (int) Math.pow(2, (Wi.length - i));
			Wi[i] = (byte) (k % fMod);
		}
		return Wi;
	}

	// 获取新身份证的最后一位:检验位
	public static String getCheckFlag(String idCard) {
		byte[] Wi= getWiBuffer();
		int sum = 0;
		// 进行加权求和
		for (int i = 0; i < 17; i++) {
			sum += Integer.parseInt(idCard.substring(i, i + 1)) * Wi[i];
		}
		// 取模运算，得到模值
		byte iCode = (byte) (sum % fMod);
		return CheckCode.substring(iCode, iCode + 1);
	}

	// 判断串长度的合法性
	public static boolean checkLength(final String idCard, boolean newIDFlag) {
		boolean right = (idCard.length() == oldIDLen) || (idCard.length() == newIDLen);
		newIDFlag = false;
		if (right) {
			newIDFlag = (idCard.length() == newIDLen);
		}
		return right;
	}

	// 获取时间串
	public static String getIDDate(final String idCard, boolean newIDFlag) {
		String dateStr = "";
		if (newIDFlag)
			dateStr = idCard.substring(fPart, fPart + 8);
		else
			dateStr = yearFlag + idCard.substring(fPart, fPart + 6);
		return dateStr;
	}

	// 判断时间合法性
	public static boolean checkDate(final String dateSource) {
		String dateStr = dateSource.substring(0, 4) + "-" + dateSource.substring(4, 6) + "-"
				+ dateSource.substring(6, 8);
		//System.out.println(dateStr);
		DateFormat df = DateFormat.getDateInstance();
		df.setLenient(false);
		try {
			Date date = df.parse(dateStr);
			return (date != null);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	// 旧身份证转换成新身份证号码
	public static String getNewIDCard(final String oldIDCard) {
		// 初始化方法
		//IdentityUtil.setWiBuffer();
		if (!checkIDCard(oldIDCard)) {
			return oldIDCard;
		}
		String newIDCard = oldIDCard.substring(0, fPart);
		newIDCard += yearFlag;
		newIDCard += oldIDCard.substring(fPart, oldIDCard.length());
		String ch = getCheckFlag(newIDCard);
		newIDCard += ch;
		return newIDCard;
	}

	// 新身份证转换成旧身份证号码
	public static String getOldIDCard(final String newIDCard) {
		// 初始化方法
		//IdentityUtil.setWiBuffer();
		if (!checkIDCard(newIDCard)) {
			return newIDCard;
		}
		String oldIDCard = newIDCard.substring(0, fPart)
				+ newIDCard.substring(fPart + yearFlag.length(), newIDCard.length() - 1);
		return oldIDCard;
	}

	// 判断身份证号码的合法性
	public static boolean checkIDCard(final String idCard) {
		// 初始化方法
		//IdentityUtil.setWiBuffer();
		Boolean isNew = false;
		// String message = "";
		if (!checkLength(idCard, isNew)) {
			// message = "ID长度异常";
			return false;
		}
		isNew=(idCard.length() == newIDLen)?true:false;
		String idDate = getIDDate(idCard, isNew);
		if (!checkDate(idDate)) {
			// message = "ID时间异常";
			return false;
		}
		if (isNew) {
			String checkFlag = getCheckFlag(idCard);
			String theFlag = idCard.substring(idCard.length() - 1, idCard.length());
			if (!checkFlag.equals(theFlag)) {
				// message = "新身份证校验位异常";
				return false;
			}
		}
		return true;
	}

	// 获取一个随机的"伪"身份证号码
	public static String getRandomIDCard(final boolean idNewID) {
		// 初始化方法
		//IdentityUtil.setWiBuffer();
		Random ran = new Random();
		String idCard = getAddressCode(ran) + getRandomDate(ran, idNewID) + getIDOrder(ran);
		if (idNewID) {
			String ch = getCheckFlag(idCard);
			idCard += ch;
		}
		return idCard;
	}

	// 产生随机的地区编码
	public static String getAddressCode(Random ran) {
		if (ran == null) {
			return "";
		} else {
			int addrCode = minCode + ran.nextInt(maxCode - minCode);
			return Integer.toString(addrCode);
		}
	}

	// 产生随机的出生日期
	public static String getRandomDate(Random ran, boolean idNewID) {
		// TODO Auto-generated method stub
		if (ran == null) {
			return "";
		}
		int year = 0;
		if (idNewID) {
			year = 1900 + ran.nextInt(2007 - 1900);
		} else {
			year = 1 + ran.nextInt(99);
		}
		int month = 1 + ran.nextInt(12);
		int day = 0;
		if (month == 2) {
			day = 1 + ran.nextInt(28);
		} else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			day = 1 + ran.nextInt(31);
		} else {
			day = 1 + ran.nextInt(30);
		}
		NumberFormat nf = NumberFormat.getIntegerInstance();
		nf.setMaximumIntegerDigits(2);
		nf.setMinimumIntegerDigits(2);
		String dateStr = Integer.toString(year) + nf.format(month) + nf.format(day);
		return dateStr;
	}

	// 产生随机的序列号
	public static String getIDOrder(Random ran) {
		// TODO Auto-generated method stub
		NumberFormat nf = NumberFormat.getIntegerInstance();
		nf.setMaximumIntegerDigits(3);
		nf.setMinimumIntegerDigits(3);
		if (ran == null) {
			return "";
		} else {
			int order = 1 + ran.nextInt(999);
			return nf.format(order);
		}
	}
    //获取姓名
	public static String getGender(String idCard){
		if(checkIDCard(idCard)==false)
			return null;
		String sign=idCard.substring((idCard.length() == newIDLen)?16:13, idCard.length()-1);
		int iSign=Integer.parseInt(sign);
		return iSign%2==0?"女":"男";
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String randomID = IdentityUtil.getRandomIDCard(true);
		System.out.println("随机身份证：" + randomID);

		String oldID = "";
		String newID = IdentityUtil.getNewIDCard(oldID);
		System.out.println("旧身份证：" + oldID);
		System.out.println("新身份证：" + newID);
		String oldCard = IdentityUtil.getOldIDCard(newID);
		System.out.println("旧身份证：" + oldCard);
		System.out.println("getCheckFlag：" + IdentityUtil.getCheckFlag("400103198110107026"));
		System.out.println("checkIDCard：" + IdentityUtil.checkIDCard("321111199206041403"));
		System.out.println("checkLength：" + IdentityUtil.checkLength("200103198010107012", true));
		System.out.println("getIDDate：" + IdentityUtil.getIDDate("400103198110107016", true));
		System.out.println("getGender：" + IdentityUtil.getGender("400103198110107024"));
		// String dateSource = "2000-9-30";
		// if (id.checkDate(dateSource))
		// System.out.println("正确时间串：" + dateSource);
		// else
		// System.out.println("错误时间串：" + dateSource);

	}
}