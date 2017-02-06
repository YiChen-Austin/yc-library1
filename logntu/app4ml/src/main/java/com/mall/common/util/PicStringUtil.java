package com.mall.common.util;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.lang.RandomStringUtils;



public class PicStringUtil {

	/**
	 * 判断是否是空字符串 null和"" 都返回 true
	 *
	 * @author caokw
	 * @param s
	 *            字符串
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (s != null && !s.equals("")) {
			return false;
		}
		return true;
	}

	/**
	 * 随即生成指定位数的含验证码字符串
	 *
	 * @author caokw
	 * @param bit
	 *            指定生成验证码位数
	 * @return String
	 */
	public static String random(int bit) {
		if (bit == 0)
			bit = 6; // 默认6位
		// 因为o和0,l和1很难区分,所以,去掉大小写的o和l
		String str = "";
		str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";// 初始化种子
		return RandomStringUtils.random(bit, str);// 返回6位的字符串
	}

	public static String randomLocal() {
		String str = "";
		str = "1234";// 初始化种子
		return RandomStringUtils.random(1, str);
	}



	/**
	 * 将字符串转换为int类型
	 * 
	 * @author caokw
	 * @param s
	 *            字符串
	 * @return
	 */
	public static int toInt(String s) {
		if (s != null && !"".equals(s.trim())) {
			try {
				return Integer.parseInt(s);
			} catch (Exception e) {
				return 0;
			}
		}
		return 0;
	}

	/**
	 * 判断是否与给定字符串样式匹配
	 *
	 * @param str
	 *            字符串
	 * @param pattern
	 *            正则表达式样式
	 * @return 是否匹配是true,否false
	 */
	public static boolean isMatch(String str, String pattern) {
		Pattern pattern_hand = Pattern.compile(pattern);
		Matcher matcher_hand = pattern_hand.matcher(str);
		boolean b = matcher_hand.matches();
		return b;
	}

	/**
	 * 获取当前查找而获得的与组匹配的所有子串内容
	 *
	 * @param str
	 *            字符串
	 * @param pattern
	 *            正则表达式样式
	 * @return 当前查找而获得的与组匹配的所有子串内容List
	 */
	public static List<String> patternMatch(String str, String pattern) {
		Pattern pattern_hand = Pattern.compile(pattern);
		Matcher matcher_hand = pattern_hand.matcher(str);
		List<String> list = new ArrayList<String>();
		if (matcher_hand.find()) {
			int gc = matcher_hand.groupCount();
			for (int i = 0; i <= gc; i++) {
				list.add(matcher_hand.group(i));
			}
		}
		return list;
	}

	/**
	 * 获取指定日期到1970年1月1日的天数(16进制)
	 * 
	 * @param date
	 *            日期 格式2014-07-18
	 * @return
	 */
	public static String dayInterval(String date) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getDefault());
		c.setTime(parseDate(date, "yyyy-MM-dd"));
		long day = c.getTimeInMillis() / 86400000;// 十进制天数
		return Long.toHexString(day);
	}

	/**
	 * 根据天数(16进制)获取1970年1月1日到指定日期路径
	 * 
	 * @param days
	 *            (16进制)天数
	 * @return
	 */
	public static String dateFormatPathByDay(String days) {
		try {
			int day = Integer.parseInt(days, 16);// 天数
			TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
			Calendar c = Calendar.getInstance();
			c.setTimeZone(TimeZone.getDefault());
			 c.setTimeInMillis(day*86400000L);
			
			return String.format("/%1$s/%2$s/%3$s/",
					c.get(Calendar.YEAR),
					String.format("%02d", c.get(Calendar.MONTH) + 1),
					String.format("%02d", c.get(Calendar.DATE)));
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String dateFormatPathBySecond(String seconds) {
		try {
			int second = Integer.parseInt(seconds, 16);// 秒数
			TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
			Calendar c = Calendar.getInstance();
			c.setTimeZone(TimeZone.getDefault());
			 c.setTimeInMillis(second*1000L);
			return String.format("/%1$s/%2$s/%3$s/%4$s/%5$s/%6$s/",
					c.get(Calendar.YEAR),
					String.format("%02d", c.get(Calendar.MONTH) + 1),
					String.format("%02d", c.get(Calendar.DATE)),
					String.format("%02d", c.get(Calendar.HOUR_OF_DAY)),
					String.format("%02d", c.get(Calendar.MINUTE)),
					String.format("%02d", c.get(Calendar.SECOND))
					);
			
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 日期格式转换
	 * 
	 * @param dateValue
	 *            日期字符串参数
	 * @param strFormat
	 *            格式
	 * @return
	 */
	public static Date parseDate(String dateValue, String strFormat) {
		if (dateValue == null)
			return null;
		if (strFormat == null)
			strFormat = "yyyy-MM-dd";
		SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);
		Date newDate = null;
		try {
			newDate = dateFormat.parse(dateValue);
		} catch (ParseException pe) {
			newDate = null;
		}
		return newDate;
	}

}
