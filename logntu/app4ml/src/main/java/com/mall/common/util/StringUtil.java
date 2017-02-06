package com.mall.common.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 字符串处理。
 * 
 * @author 印鲜刚
 * @author sol
 * @since 2010-6-13
 */
public class StringUtil {
	/**
	 * 过滤html标签
	 * 
	 * @author caokw
	 * @param inputString
	 *            html字符串
	 */
	public static String FilterHtmlTags(String inputString) {
		String htmlStr = inputString;
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		try {
			// 不能引错包
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>}

			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>}

			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签
			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签
			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签
			textStr = htmlStr;
		} catch (Exception e) {
			return "";
		}
		return textStr;// 返回文本字符串
	}

	/**
	 * <p>
	 * 局部替换文本为掩码字符。通常用于为电话加星号，也可用于其他文本加其他掩码。
	 * </p>
	 * <p>
	 * 该重载版本中，固定使用 * 作为掩码。
	 * </p>
	 * <p>
	 * 要使用其他掩码字符，参见重载
	 * {@code maskText(String text, int start, int count, String mask)}
	 * </p>
	 * 
	 * @author sol
	 * @param text
	 *            文本或数字。
	 * @param start
	 *            替换区间起始索引。特殊地，如果为负数，表示从右往左数。
	 * @param count
	 *            替换区间长度。特殊地，如果为负数，表示占据整个字符串长度。
	 * @return 替换后的字符串。
	 */
	public static String maskText(String text, int start, int count) {
		return maskText(text, start, count, "*");
	}

	/**
	 * 局部替换文本为掩码字符。通常用于为电话加星号，也可用于其他文本加其他掩码。
	 * 
	 * @example String text1 = StringUtil.maskText('123456789', 3, 4); // text1
	 *          = '123****89' String text2 = StringUtil.maskText('123456789', 6,
	 *          2); // text2 = '1234567**'
	 * 
	 * @author sol
	 * @param text
	 *            文本或数字。
	 * @param start
	 *            替换区间起始索引。特殊地，如果为负数，表示从右往左数。
	 * @param count
	 *            替换区间长度。特殊地，如果为负数，表示占据整个字符串长度。
	 * @param mask
	 *            掩码字符。通常为"*"。
	 * @return 替换后的字符串。
	 */
	public static String maskText(String text, int start, int count, String mask) {
		if (start < 0) {
			start += text.length();
		}
		if (count < 0) {
			count = text.length();
		}

		// # WARN java.lang.String.split() gives weird behavior: unexpected
		// leading blank component
		// # WARN org.apache.commons.lang.StringUtils.split() doesn't work with
		// 'invisible' separator at all
		// # Leaves me no option but to roll my own.
		// #
		String[] chars = explode(text);

		for (int i = 0; i < chars.length; ++i) {
			// DBG System.out.println(chars[i]);
			if (i >= start && i < start + count && i < chars.length) {
				chars[i] = mask;
			}
		}

		return StringUtils.join(chars);
	}

	/**
	 * 爆破文本为“字符”数组。
	 * 
	 * @example "12345" => ["1", "2", "3", "4", "5"]
	 * 
	 * @author sol
	 * @param text
	 *            要爆破的文本。
	 * @return 爆破后的“字符”数组。
	 */
	public static String[] explode(String text) {
		String[] res = new String[text.length()];
		for (int i = 0; i < text.length(); ++i) {
			res[i] = text.substring(i, i + 1);
		}
		// DBG System.out.printf("%d\n", chars.length);
		return res;
	}

	/*
	 * public static void main(String[] value) { String stre =
	 * "字符串处理1、字符串处理2，字符串处理3，字符串处理4,字符串处理5,字符串处理6;字符串处理7；字符串处理8|字符串处理9";
	 * String[] vString = stre.split("、|,|，|;|；|\\|"); for (String v : vString)
	 * { System.out.println(v); } }
	 */

	/**
	 * 把原字符串第一个字符转为大写，用于java反射机制获取拼接的getter方法
	 * 
	 * @param oldStr
	 * @return
	 */
	public static String firstCharUpper(String oldStr) {
		String firstChar = oldStr.substring(0, 1);
		return oldStr.replaceFirst(firstChar, firstChar.toUpperCase());
	}

	/**
	 * @Description(功能描述) : 价格转字符串（单位元-元）
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年9月12日 下午2:25:59
	 * @return
	 */
	public static String yunPrice2yunString(double price) {
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		currency.setGroupingUsed(false);
		currency.setMaximumFractionDigits(2);
		String sum_amount = currency.format(price).substring(1);
		return sum_amount;
	}

	/**
	 * @Description(功能描述) : 价格转字符串（单位元-分）
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年9月12日 下午2:25:59
	 * @return
	 */
	public static String yunPrice2fenString(double price) {
		return Long.toString((long) (price * 100));
	}

	/**
	 * @Description(功能描述) : 价格转字符串（单位分-分）
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年9月12日 下午2:25:59
	 * @return
	 */
	public static String fenPrice2YunString(long price) {
		return yunPrice2yunString(((double) price) / 100);
	}

	/**
	 * @Description(功能描述) : 价格转字符串（单位分-分）
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年9月12日 下午2:25:59
	 * @return
	 */
	public static String fenPrice2fenString(long price) {
		return Long.toString(price);
	}

	/**
	 * @Description(功能描述) : 通用价格转换
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年9月12日 下午2:25:59
	 * @return
	 */
	public static String price2RMB(double price) {
		DecimalFormat df = new DecimalFormat("#########0.00");
		return df.format(price);
	}
	
	public static void main(String[] args){
		System.out.println(price2RMB(0.009999999999990905));
	}
}
